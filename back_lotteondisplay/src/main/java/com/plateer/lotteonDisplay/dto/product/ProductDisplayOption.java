package com.plateer.lotteonDisplay.dto.product;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDisplayOption {

    private String pdSortCd;        //정렬기준
    private String pageNo;          //페이지 번호
    private String rowsPerPage;     //한 페이지 당 상품 개수
    private String dshopNo;         //카테고리
}
