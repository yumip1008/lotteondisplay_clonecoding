package com.plateer.lotteonDisplay.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plateer.lotteonDisplay.dto.cart.CartDetailDto;
import com.plateer.lotteonDisplay.dto.cart.CartDto;
import com.plateer.lotteonDisplay.dto.product.ProductDetailDto;
import com.plateer.lotteonDisplay.dto.product.ProductDetailRequest;
import com.plateer.lotteonDisplay.exception.InvalidProductInfoException;
import com.plateer.lotteonDisplay.exception.IrtrNoNullException;
import com.plateer.lotteonDisplay.model.cart.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class ObjectMapperUtil {

    private final ObjectMapper mapper = new ObjectMapper();

    public Mono<Cart> convertCart(Mono<ProductDetailDto> product){
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

    public CartDetailDto convertCartDetailDto(ProductDetailDto pdto, Cart cart){
        CartDetailDto cartDetailDto = mapper.convertValue(pdto, CartDetailDto.class);
        cartDetailDto.setCartSn(cart.getCartSn());
        cartDetailDto.setMbNo(cart.getMbNo());
        cartDetailDto.setOdQty(cart.getOdQty());
        return cartDetailDto;
    }


    public MultiValueMap<String, String> convertMultiValueMap(Object object){
        Map<String, String> map = mapper.convertValue(object, new TypeReference<Map<String, String>>() {});
        MultiValueMap<String,String> multiValueMap = new LinkedMultiValueMap<>();

        map.entrySet().forEach(e -> multiValueMap.add(e.getKey(), e.getValue()));
        return multiValueMap;
    }
}
