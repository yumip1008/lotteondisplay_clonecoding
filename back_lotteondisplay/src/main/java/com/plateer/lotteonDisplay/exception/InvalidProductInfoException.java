package com.plateer.lotteonDisplay.exception;

public class InvalidProductInfoException extends RuntimeException{

    public InvalidProductInfoException(){
        super("단품 가격정보가 올바르지 않습니다!");
    }
}
