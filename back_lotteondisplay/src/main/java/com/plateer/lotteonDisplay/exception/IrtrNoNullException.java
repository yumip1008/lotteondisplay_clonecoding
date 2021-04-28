package com.plateer.lotteonDisplay.exception;

public class IrtrNoNullException extends RuntimeException{

    public IrtrNoNullException(){
        super("하위 거래처 정보가 올바르지 않습니다.");
    }
}
