package com.plateer.lotteonDisplay.dto.product;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailRequest {

    private String spdNo;       //상품번호

    private String sitmNo;      //단품번호
}
