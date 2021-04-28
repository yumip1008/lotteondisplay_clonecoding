package com.plateer.lotteonDisplay.exception;

public class DuplicatedCartException extends RuntimeException{

    public DuplicatedCartException(){
        super("장바구니에 해당 상품이 존재합니다!");
    }

}
