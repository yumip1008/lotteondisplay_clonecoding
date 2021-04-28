package com.plateer.lotteonDisplay.service.product;

import com.plateer.lotteonDisplay.dto.product.ProductDisplayOption;
import com.plateer.lotteonDisplay.dto.product.CatePdList;
import com.plateer.lotteonDisplay.dto.product.ProductDetailRequest;
import com.plateer.lotteonDisplay.dto.product.ProductDetailDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductService {

    Mono<CatePdList> getAllProudcts(ProductDisplayOption productDisplayOption);

    Mono<ProductDetailDto> getProduct(ProductDetailRequest productDetailRequest);

    Flux<ProductDetailDto> getProducts(Flux<ProductDetailRequest> productDetailRequest);
}
