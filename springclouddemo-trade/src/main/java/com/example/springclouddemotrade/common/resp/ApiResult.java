package com.example.springclouddemotrade.common.resp;

import com.example.springclouddemotrade.common.constants.Constants;
import lombok.Data;

/**
 * @Description TODO
 * @Author hnuya
 * @Date 2019/9/24
 **/
@Data
public class ApiResult<T> {

    private int code = Constants.RESP_STATUS_OK;

    private String message;

    private T data;

    public ApiResult() {
    }

    public ApiResult(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
