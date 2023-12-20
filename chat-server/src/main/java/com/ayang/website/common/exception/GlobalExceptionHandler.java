package com.ayang.website.common.exception;

import com.ayang.website.common.domain.vo.resp.ApiResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author shy
 * @date 2023/12/19
 * @description 全局异常捕获程序
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ApiResult<?> methodArgumentNotValidException(MethodArgumentNotValidException e){
        return ApiResult.fail(null, null);
    }
}
