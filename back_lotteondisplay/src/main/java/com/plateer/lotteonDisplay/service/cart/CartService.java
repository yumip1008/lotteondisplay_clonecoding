package com.plateer.lotteonDisplay.service.cart;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plateer.lotteonDisplay.dto.cart.CartGroupDto;
import com.plateer.lotteonDisplay.exception.DuplicatedCartException;
import com.plateer.lotteonDisplay.exception.InvalidProductInfoException;
import com.plateer.lotteonDisplay.exception.IrtrNoNullException;
import com.plateer.lotteonDisplay.model.cart.Cart;
import com.plateer.lotteonDisplay.dto.cart.CartDetailDto;
import com.plateer.lotteonDisplay.dto.cart.CartDto;
import com.plateer.lotteonDisplay.dto.product.ProductDetailDto;
import com.plateer.lotteonDisplay.dto.product.ProductDetailRequest;
import com.plateer.lotteonDisplay.repository.cart.ICartRepository;
import com.plateer.lotteonDisplay.service.product.ProductService;
import com.plateer.lotteonDisplay.util.WebClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class CartService implements ICartService {

    @Autowired
    ICartRepository cartRepository;

    @Autowired
    ProductService productService;

    @Autowired
    WebClientUtil webClientUtil;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public Mono<Cart> create(CartDto cartDto) {
        //장바구니에 동일상품이 존재하면 에외 발생, 그렇지 않을 때만 save
        Mono<Cart> cart = existsCart(cartDto).flatMap(flag -> {
            if(flag){
                throw new DuplicatedCartException();
            }else{
                //상품정보 API로부터 ProductDeatilDto를 가져온 후, Cart 만들기
                return makeCartFromProductDetailDto(cartDto);
            }
        });

        return cart.flatMap(c -> {
            //회원번호 저장
            c.setMbNo(cartDto.getMbNo());
            //수량 저장
            c.setOdQty(cartDto.getOdQty());
            return cartRepository.save(c);
        });
    }

    @Override
    public Mono<Boolean> existsCart(CartDto cartDto) {
        return cartRepository.existsCart(cartDto);
    }

    @Override
    public Mono<Cart> updateCart(CartDto cartDto) {
        return cartRepository.findById(cartDto.getCartSn())
                .map((cart) -> {
                    cart.setOdQty(cartDto.getOdQty());
                    return cart;
                })
                .flatMap(cart -> cartRepository.save(cart));
    }

    @Override
    public Mono<Map<String, Collection<CartDetailDto>>> getCartsAllByGrouping(String mbNo){
        cartRepository.findAllByMbNoOrderbyRegDttm(mbNo)
                .groupBy(Cart::getLrtrNo)
                .subscribe(e -> System.out.println(e.toString()));

        //DB에서 해당 회원의 장바구니들 찾기
        Flux<Cart> cartFlux = cartRepository.findAllByMbNoOrderbyRegDttm(mbNo);

        //장바구니들에서 spdNo, sitmNo을 추출해, 상품 정보 API의 request body로 보낼 데이터 형식 만들기
        Flux<ProductDetailRequest> request = cartFlux
                .map(e -> new ProductDetailRequest(e.getSpdNo(), e.getSitmNo()));

        //상품 정보 API를 불러와 CartDetailDto로 받기
        Flux<CartDetailDto> result  = productService.getProducts(request)
                .map(e -> mapper.convertValue(e, CartDetailDto.class))
                .zipWith(cartFlux, (cartDetail, cart) -> {
                    cartDetail.setCartSn(cart.getCartSn());
                    cartDetail.setMbNo(cart.getMbNo());
                    cartDetail.setOdQty(cart.getOdQty());

                    return cartDetail;
                });

        //하위거래처 번호로 그룹핑

        return result.collectMultimap(CartDetailDto::getLrtrNo);
    }

    @Override
    public Mono<Void> deleteCart(String cartSn) {
        return cartRepository.deleteById(cartSn);
    }

    public Mono<Cart> makeCartFromProductDetailDto(CartDto cartDto){

        //ProductDetailDto 가져오기
        Mono<ProductDetailDto> product = productService.getProduct(new ProductDetailRequest(cartDto.getSpdNo(), cartDto.getSitmNo()));

        //ProductDetailDto를 DB에 저장될 Cart 형으로 변환
        Mono<Cart> cart = product.map(e -> {
            if(e.getReturnCode().equals("500")) {
                throw new InvalidProductInfoException();
            }else if(e.getLrtrNo() == null){
                throw new IrtrNoNullException();
            }else{
                return mapper.convertValue(e, Cart.class);
            }
        });
        return cart;
    }

}
