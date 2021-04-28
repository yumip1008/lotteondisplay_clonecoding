package com.plateer.lotteonDisplay.controller;

import com.plateer.lotteonDisplay.model.cart.Cart;
import com.plateer.lotteonDisplay.dto.cart.CartDetailDto;
import com.plateer.lotteonDisplay.dto.cart.CartDto;
import com.plateer.lotteonDisplay.service.cart.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ICartService cartService;

    @PostMapping
    public Mono<Cart> createCart(@RequestBody CartDto cartDto){
        return cartService.create(cartDto);
    }

    @GetMapping
    public Mono<Map<String, Collection<CartDetailDto>>> getAllCartsByGrouping(@RequestParam String mbNo){
        return cartService.getCartsAllByGrouping(mbNo);
    }

    @PutMapping
    public Mono<Cart> updateCart(@RequestBody CartDto cartDto){
        return cartService.updateCart(cartDto);
    }

    @DeleteMapping
    public Mono<Void> deleteCart(@RequestParam String cartSn){
        return cartService.deleteCart(cartSn);
    }
}
