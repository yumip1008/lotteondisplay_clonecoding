package com.plateer.lotteonDisplay.service.product;

import com.plateer.lotteonDisplay.dto.product.*;
import com.plateer.lotteonDisplay.util.ObjectMapperUtil;
import com.plateer.lotteonDisplay.util.WebClientUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.plateer.lotteonDisplay.constants.ProductConstants.*;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{

    private final WebClientUtil webClientUtil;

    private final ObjectMapperUtil objectMapperUtil;

    @Override
    public Mono<CatePdList> getAllProudcts(ProductDisplayOption productDisplayOption) {
        
        //API 호출 후, ProductListResponse 타입으로 받고, catePdList Mono로 리턴
        return webClientUtil.getByParamsGetRequest(GET_ALL_PRODUCTS,
                objectMapperUtil.convertMultiValueMap(productDisplayOption),
                ProductListResponse.class)
                .map(e -> e.getCatePdList());

    }

    @Override
    public Mono<ProductDetailDto> getProduct(ProductDetailRequest productDetailRequest) {
        
        ///API 호출 후, ProductListResponse 타입으로 받고,ProductDeatilDto Mono로 리턴
        return  webClientUtil.getByFluxBodyPostRequest(GET_Product, 
                productDetailRequest, 
                ProductDetailResponse.class)
                .map(response -> response.getData().get(0));
        
    }

    @Override
    public Flux<ProductDetailDto> getProducts(Flux<ProductDetailRequest> productDetailRequest) {
        
        //API 호출 후, ProductListReponse 타입으로 받고, ProductDetailDto Flux로 리턴
        return webClientUtil.getByFluxBodyPostRequest(GET_Product, 
                productDetailRequest, 
                ProductDetailRequest.class, 
                ProductDetailResponse.class)
                .map(response -> response.getData())
                .flatMapMany(Flux::fromIterable);
    }
}