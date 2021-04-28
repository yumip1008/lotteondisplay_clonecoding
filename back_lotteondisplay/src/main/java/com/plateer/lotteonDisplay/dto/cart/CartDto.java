package com.plateer.lotteonDisplay.dto.cart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartDto {

    private String cartSn;
    private String spdNo;
    private String sitmNo;
    private String mbNo;
    private int odQty;
}
