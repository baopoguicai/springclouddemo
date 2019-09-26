package com.example.springclouddemotrade.common.exception;

import com.example.springclouddemotrade.common.constants.Constants;
import com.example.springclouddemotrade.common.resp.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description TODO
 * @Author hnuya
 * @Date 2019/9/24
 **/
@SuppressWarnings("ALL")
@ControllerAdvice
@ResponseBody
@Slf4j
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    public ApiResult handleException(Exception e){
        log.error(e.getMessage(),e);
        return new ApiResult(Constants.RESP_STATUS_INTERNAL_ERROR,"系统异常，请稍后再试");
    }
    @ExceptionHandler(MamaBuyException.class)
    public ApiResult handleException(MamaBuyException e){
        log.error(e.getMessage(),e);
        return new ApiResult(e.getStatusCode(),e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResult handleIllegalParamException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(),e);
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        String message = "参数不合法";
        if (errors.size() > 0) {
            message = errors.get(0).getDefaultMessage();
        }
        ApiResult result = new ApiResult(Constants.RESP_STATUS_BADREQUEST,message);
        return result;
    }
}
