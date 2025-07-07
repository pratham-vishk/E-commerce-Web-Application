package com.ecommerce.api_gateway.exception;

public class BadApiRequestException extends  RuntimeException{
    public BadApiRequestException(String message){
        super(message);
    }
    public BadApiRequestException(){
        super("Bad Request");
    }
}
