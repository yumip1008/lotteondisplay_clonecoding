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
import com.plateer.lotteonDisplay.util.ObjectMapperUtil;
import com.plateer.lotteonDisplay.util.WebClientUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.lang.reflect.ParameterizedType;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartService implements ICartService {

    private final ICartRepository cartRepository;

    private final ProductService productService;

    private final ObjectMapperUtil objectMapperUtil;


    @Override
    public Mono<Cart> create(CartDto cartDto) {
        //장바구니에 동일상품이 존재하면 에외 발생, 그렇지 않을 때만 save
        Mono<Cart> cart = existsCart(cartDto).flatMap(flag -> {
            if(flag){
                throw new DuplicatedCartException();
            }else{
                //상품정보 API로부터 ProductDeatilDto를 가져온 후, Cart 만들기
                Mono<ProductDetailDto> product = productService.getProduct(new ProductDetailRequest(cartDto.getSpdNo(), cartDto.getSitmNo()));
                return objectMapperUtil.convertCart(product);
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

         return cartRepository.findAllByMbNoOrderbyRegDttm(mbNo)
                .collectMap(cart -> cart.getSitmNo(), cart-> cart)  //Cart로 부터 key가 sitmNo, value가 cart인 map 만들기
                .flatMap(cartMap -> {
                    //CartMap으로 부터 API 호출에 필요한 ProductDetailRequest Flux 만들기
                    Flux<ProductDetailRequest> request =
                            Flux.fromStream(cartMap.values().stream()
                                    .map(cart -> new ProductDetailRequest(cart.getSpdNo(), cart.getSitmNo())));

                    //API호출 후, 받아온 pdto와
                    //cartMap으로 부터 해당 pdto의 sitmNo에 대한 Cart 를 이용해 Front에 넘겨줄 CartDetailDto 만들어 리턴
                    return productService.getProducts(request)
                            .map(pdto-> objectMapperUtil.convertCartDetailDto(pdto, cartMap.get(pdto.getSitmNo())))
                            .collectMultimap(CartDetailDto::getLrtrNo);
                        }
                );

    }

    //장바구니 개수 만큼 API 호출
    public Mono<Map<String, Collection<CartDetailDto>>> getCartsAllByGroup(String mbNo){

        return cartRepository.findAllByMbNoOrderbyRegDttm(mbNo)
                .concatMap(cart ->
                        productService.getProduct(new ProductDetailRequest(cart.getSpdNo(), cart.getSitmNo()))
                                .map(pdto ->objectMapperUtil.convertCartDetailDto(pdto, cart))
                )
                .collectMultimap(CartDetailDto::getLrtrNo);
    }


    @Override
    public Mono<Void> deleteCart(String cartSn) {
        return cartRepository.deleteById(cartSn);
    }

    @Override
    public Mono<Void> deleteAllByIds(List<String> cartSns) {
        return cartRepository.deleteAllByIds(cartSns);
    }


}
