package com.example.springclouddemouser.common.exception;

import com.example.springclouddemouser.common.constants.Constants;
import com.example.springclouddemouser.common.resp.ApiResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description controller增强器，应用到所有@ResquestMapping注解方法，在其执行之前初始化数据绑定器
 * @Author hnuya
 * @Date 2019/9/24
 **/
@ControllerAdvice
@ResponseBody
public class ExceptionHandleAdvice {

    @ExceptionHandler(Exception.class)
    public ApiResult handleException(Exception e) {
        return new ApiResult(Constants.RESP_STATUS_INTERNAL_ERROR,"系统异常，请稍后重试");
    }

    @ExceptionHandler(MamaBuyException.class)
    public ApiResult handleException(MamaBuyException e) {
        return new ApiResult(e.getStatusCode(),e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResult handleException(MethodArgumentNotValidException e){
        List<ObjectError> errors =  e.getBindingResult().getAllErrors();
        String message = "参数不合法";
        if(errors.size()>0){
            message = errors.get(0).getDefaultMessage();
        }
        ApiResult result = new ApiResult(Constants.RESP_STATUS_BADREQUEST,message);
        return result;
    }
}
