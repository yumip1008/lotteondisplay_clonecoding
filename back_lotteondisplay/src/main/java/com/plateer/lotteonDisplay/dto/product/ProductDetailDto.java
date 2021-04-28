package com.plateer.lotteonDisplay.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDetailDto {
    
    private String spdNo;           //상품번호

    private String spdNm;           //상품이름

    private String sitmNo;          //단품번호

    private String sitmNm;          //단일상품 이름

    private String trGrpCd;         //거래처그룹코드

    private String trNo;            //거래처번호

    private String lrtrNo;          //하위거래처번호

    private String brdNm;           //브랜드 이름

    private List<ImgJsn> imgJsn;    //이미지

    private ItmByMaxPurPsbJsn itmByMaxPurPsbJsn;    //최대 수량
    
    private ItmByMinPurJsn itmByMinPurJsn;  //최소 수량

    private int slPrc;              //판매가

    private String estmtDlvTxt;     //도착문구
    
    private String returnCode;      //응답 코드
}
