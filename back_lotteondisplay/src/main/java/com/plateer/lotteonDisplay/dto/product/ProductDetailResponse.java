package com.plateer.lotteonDisplay.dto.product;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailResponse {

    List<ProductDetailDto> data;

}
