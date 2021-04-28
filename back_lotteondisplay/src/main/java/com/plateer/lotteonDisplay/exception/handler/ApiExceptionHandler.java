package com.plateer.lotteonDisplay.exception.handler;

import com.plateer.lotteonDisplay.exception.DuplicatedCartException;
import com.plateer.lotteonDisplay.exception.InvalidProductInfoException;
import com.plateer.lotteonDisplay.exception.IrtrNoNullException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicatedCartException.class)
    public String handleException(DuplicatedCartException ex){
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidProductInfoException.class)
    public String handleExcpetion(InvalidProductInfoException ex){
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IrtrNoNullException.class)
    public String handleExcpetion(IrtrNoNullException ex){
        return ex.getMessage();
    }
}
