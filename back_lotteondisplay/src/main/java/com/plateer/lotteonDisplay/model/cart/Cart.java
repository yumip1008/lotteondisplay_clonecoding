package com.plateer.lotteonDisplay.model.cart;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.*;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("om_cart")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cart implements Persistable<String> {

    @Id
    private String cartSn;          //장바구니 순번

    @Column
    private String trNo;            //거래처 번호

    @Column
    private String lrtrNo;          //하위거래처 번호

    @Column
    private String mbNo;            //회원번호

    @Column
    private String spdNo;           //상품번호

    @Column
    private String sitmNo;          //단품번호

    @Column
    private int odQty;              //주문수량

    @Column
    @CreatedDate
    private LocalDateTime regDttm;  //장바구니 생성날짜

    @Column
    @LastModifiedDate
    private LocalDateTime modDttm;  //장바구니 수정날짜

    @Column
    @CreatedBy
    private String createdBy;       //장바구니 생성 주체

    @Column
    @LastModifiedBy
    private String updatedBy;       //장바구니 수정 주체

    @Override
    public String getId() {
        return null;
    }

    @Override
    public boolean isNew() {
        return createdBy == null;
    }
}
