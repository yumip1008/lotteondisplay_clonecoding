package com.plateer.lotteonDisplay.dto.product;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductListDataDto {

    private String spdNo;       //상품번호

    private String spdNm;       //상품이름

    private String sitmNo;      //단품번호

    private String trNo;        //거래처번호

    private String lrtrNo;      //하위거래처번호

    private String brdNm;       //브랜드이름

    private float scrAvgScr;    //평균별점

    private int rvCnt;          //별점 개수

    private int mmSlQty;        // 구매 수량

    private int dcVal;          //할인 %

    private float slPrc;        // 본 가격

    private float onerFvrPrc;   //할인된 가격

    private String imgFullUrl;  //이미지

}
