package com.example.springclouddemostock.common.exception;


import com.example.springclouddemostock.common.constants.Constants;

public class MamaBuyException extends RuntimeException{

    private int statusCode = Constants.RESP_STATUS_INTERNAL_ERROR;

    public MamaBuyException(int statusCode,String message) {
        super(message);
        this.statusCode = statusCode;
    }
    public MamaBuyException(String message) {
        super(message);
    }

    public int getStatusCode() {
        return statusCode;
    }
}
