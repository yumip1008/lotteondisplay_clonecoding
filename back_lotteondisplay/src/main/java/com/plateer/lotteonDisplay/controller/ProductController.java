
package com.plateer.lotteonDisplay.controller;

import com.plateer.lotteonDisplay.dto.product.ProductDisplayOption;
import com.plateer.lotteonDisplay.dto.product.CatePdList;
import com.plateer.lotteonDisplay.dto.product.ProductDetailDto;
import com.plateer.lotteonDisplay.dto.product.ProductDetailRequest;
import com.plateer.lotteonDisplay.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    IProductService productService;

    @PostMapping
    public Mono<CatePdList> getAllProduct(@RequestBody ProductDisplayOption productDisplayOption){
        return productService.getAllProudcts(productDisplayOption);
    }

    @GetMapping
    public Mono<ProductDetailDto> getProduct(@RequestParam ProductDetailRequest productDetailRequest){
        return productService.getProduct(productDetailRequest);
    }
}