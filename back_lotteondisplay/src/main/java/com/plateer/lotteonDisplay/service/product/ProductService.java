package com.plateer.lotteonDisplay.service.product;

import com.plateer.lotteonDisplay.dto.product.*;
import com.plateer.lotteonDisplay.util.MultiValueMapMapperUtil;
import com.plateer.lotteonDisplay.util.WebClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.plateer.lotteonDisplay.constants.ProductConstants.*;

@Service
public class ProductService implements IProductService{

    @Autowired
    private WebClientUtil webClientUtil;

    @Override
    public Mono<CatePdList> getAllProudcts(ProductDisplayOption productDisplayOption) {

        //WebClient의 queryParams의 paramter에 맞게 Object를 MultiValueMap으로 변환
        MultiValueMap<String,String> multiValueMap = MultiValueMapMapperUtil.convertFromObject(productDisplayOption);

        //API 호출 후, Mono<ProductListResponse> 타입으로 받기
        Mono<ProductListResponse> productList =
                webClientUtil.getByParamsGetRequest(GET_ALL_PRODUCTS, multiValueMap, ProductListResponse.class);

        //필요한 CatePdList 받기
        Mono<CatePdList> catePdList = productList.map(e -> e.getCatePdList());

        return catePdList;
    }

    @Override
    public Mono<ProductDetailDto> getProduct(ProductDetailRequest productDetailRequest) {

        Mono<ProductDetailResponse> response =
                webClientUtil.getByFluxBodyPostRequest(GET_Product, productDetailRequest, ProductDetailResponse.class);

        Mono<ProductDetailDto> productDetailDto = response.map(e -> e.getData().get(0));

        return productDetailDto;
    }

    @Override
    public Flux<ProductDetailDto> getProducts(Flux<ProductDetailRequest> productDetailRequest) {

        Mono<ProductDetailResponse> response =
                webClientUtil.getByFluxBodyPostRequest(GET_Product, productDetailRequest, ProductDetailRequest.class, ProductDetailResponse.class);

        Flux<ProductDetailDto> productDetailDto = response.map(e -> e.getData())
                .flatMapMany(Flux::fromIterable);

        return productDetailDto;
    }
}