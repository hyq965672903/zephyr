package cn.hyqup.common.web.exception;

import cn.hyqup.common.core.result.Result;
import cn.hyqup.common.core.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/2/7
 * @description: 重写SpringMVC的统一异常，返回统一格式并增加返回处理
 * ConstraintViolationException相关的异常
 * 参考：
 * https://www.cnblogs.com/fqybzhangji/p/10384347.html
 * https://www.w3cschool.cn/spring_mvc_documentation_linesh_translation/spring_mvc_documentation_linesh_translation-ewm227sd.html
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ValidateExceptionCatch extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(Result.builder().message(ex.getMessage()).build().failure(ResultCode.MISSING_SERVLET_REQUEST_PARAMETER), status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (ex.getBindingResult().hasErrors()) {
            String message = "";
            for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {
                StringBuilder str = new StringBuilder();
                FieldError fieldError = null;
                if (objectError instanceof FieldError) {
                    fieldError = (FieldError) objectError;
                    str.append(fieldError.getField());
                    str.append(":");
                    str.append(fieldError.getDefaultMessage());
                    message = str.toString();
                    break;
                }else {
                    return new ResponseEntity<>(Result.builder().build().failure(ResultCode.UN_KNOW), status);
                }
            }
            return new ResponseEntity<>(Result.builder().build().failureWithMessage(ResultCode.METHOD_ARGUMENT_NOT_VALID,message), status);
        }
        return super.handleExceptionInternal(ex, null, headers, status, request);
    }


    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = "";
        for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {
            StringBuilder str = new StringBuilder();
            FieldError fieldError = (FieldError) objectError;
            str.append(fieldError.getField());
            str.append(":");
            str.append(fieldError.getDefaultMessage());
            message = str.toString();
            break;
        }
        return new ResponseEntity<>(Result.builder().build().failureWithMessage(ResultCode.BIND,message), status);
    }

    /**
     * 处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
     * 这个异常也是属于javax.validation的校验异常，但不属于springMVC默认处理的异常 所以我们这额外处理了
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Result ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        return Result.builder().build().failureWithMessage(ResultCode.PARMERROR,message);
    }

}
