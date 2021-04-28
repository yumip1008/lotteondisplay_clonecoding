package com.plateer.lotteonDisplay.service.cart;

import com.plateer.lotteonDisplay.model.cart.Cart;
import com.plateer.lotteonDisplay.dto.cart.CartDetailDto;

import com.plateer.lotteonDisplay.dto.cart.CartDto;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Map;

public interface ICartService {

    Mono<Cart> create(CartDto cartDto);

    Mono<Boolean> existsCart(CartDto cartDto);

    Mono<Cart> updateCart(CartDto cartDto);

    Mono<Map<String, Collection<CartDetailDto>>> getCartsAllByGrouping(String mbNo);

    Mono<Void> deleteCart(String cartSn);

}
