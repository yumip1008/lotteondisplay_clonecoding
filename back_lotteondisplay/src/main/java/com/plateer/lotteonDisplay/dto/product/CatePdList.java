package com.plateer.lotteonDisplay.dto.product;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatePdList {
    private int totalCount;                  //총 상품 개수

    private int pageNo;                      //페이지 번호

    private int rowsPerPage;                 //한 페이지 당 상품 개수

    private List<ProductListDataDto> dataList;  //상품정보리스트
}
