package com.example.springclouddemouser.common.exception;

import com.example.springclouddemouser.common.constants.Constants;

/**
 * @Description TODO
 * @Author hnuya
 * @Date 2019/9/24
 **/
public class MamaBuyException extends RuntimeException {

    private int statusCode = Constants.RESP_STATUS_INTERNAL_ERROR;

    public MamaBuyException(int statusCode,String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public MamaBuyException(String message){
        super(message);
    }

    public int getStatusCode() {
        return statusCode;
    }
}
