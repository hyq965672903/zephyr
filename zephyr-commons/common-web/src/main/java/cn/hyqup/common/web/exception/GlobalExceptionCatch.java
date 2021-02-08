package cn.hyqup.common.web.exception;


import cn.hyqup.common.core.exception.CustomException;

import cn.hyqup.common.core.result.IResultCode;
import cn.hyqup.common.core.result.Result;
import cn.hyqup.common.core.result.ResultCode;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Copyright © 2020灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2020/3/7
 * @description:控制器增强 参考： https://www.cnblogs.com/fqybzhangji/p/10384347.html
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionCatch {
    /**
     * 定义map，配置异常类型所对应的错误代码
     */
    private static ImmutableMap<Class<? extends Throwable>, IResultCode> EXCEPTIONS;
    /**
     * 定义map的builder对象，去构建ImmutableMap
     */
    protected static ImmutableMap.Builder<Class<? extends Throwable>, IResultCode> builder = ImmutableMap.builder();

    /**
     * 捕获自定义异常
     *
     * @param customException
     * @return
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Result customException(CustomException customException) {
        //记录日志
        log.error("catch exception:{}", customException.getMessage());
        customException.printStackTrace();
        log.error(customException.getStackTrace().toString());
        IResultCode iResultCode = customException.getIResultCode();
        return Result.builder().build().failure(iResultCode);
    }

    /**
     * 捕获可预知系统异常和不可预知系统异常
     * ps:从EXCEPTIONS中找异常类型所对应的错误代码，如果找到了将错误代码响应给用户，如果找不到给用户响应99999异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exception(Exception exception) {
        log.error("catch exception:{}", exception.getMessage());
        exception.printStackTrace();

        if (EXCEPTIONS == null) {
            EXCEPTIONS = builder.build();
        }
        IResultCode IResultCode = EXCEPTIONS.get(exception.getClass());
        if (IResultCode != null) {
            return Result.builder().build().failure(IResultCode);
        } else {
            return Result.builder().build().failure(ResultCode.SERVER_ERROR);
        }
    }

//    @ExceptionHandler(BindException.class)
//    @ResponseBody
//    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        log.error("参数绑定失败", ex);
//        String message = "";
//        for (ObjectError objectError : ex.getAllErrors()) {
//            StringBuilder str = new StringBuilder();
//            FieldError fieldError = (FieldError) objectError;
//            str.append(fieldError.getField());
//            str.append(":");
//            str.append(fieldError.getDefaultMessage());
//            message = str.toString();
//            break;
//        }
//        return new ResponseEntity<>(Result.builder().build().failure(ResultCode.PARMERROR, message), HttpStatus.OK);
//
//    }
//
//    //处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
//    @ExceptionHandler(ConstraintViolationException.class)
//    @ResponseBody
//    public Result ConstraintViolationExceptionHandler(ConstraintViolationException e) {
//        String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
//        return Result.builder().build().failure(ResultCode.PARMERROR, message);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseBody
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        log.error("参数绑定失败", ex);
//        if (ex.getBindingResult().hasErrors()) {
//            String message = "";
//            for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {
//                StringBuilder str = new StringBuilder();
//                FieldError fieldError = (FieldError) objectError;
//                str.append(fieldError.getField());
//                str.append(":");
//                str.append(fieldError.getDefaultMessage());
//                message = str.toString();
//                break;
//            }
//            return new ResponseEntity<>(Result.builder().build().failure(ResultCode.PARMERROR, message), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(Result.builder().build().failure(ResultCode.PARMERROR), HttpStatus.OK);
//    }


    static {
        /**
         * 定义系统可预知异常
         */
        builder.put(NullPointerException.class, ResultCode.NULLPOINT);
    }


    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class, ConstraintViolationException.class})
    @ResponseBody
    public Object handleParamsValidException(Exception e) {
        BindingResult bindingResult = null;
        if (e instanceof BindException) {
            bindingResult = ((BindException) e).getBindingResult();
        } else if (e instanceof MethodArgumentNotValidException){
            bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
        }else {
            ConstraintViolationException e1 = (ConstraintViolationException) e;
            String message = e1.getMessage();
            return message;
        }
        StringBuilder errMsg = getErrorInfo(bindingResult);
        return errMsg;
    }


    private StringBuilder getErrorInfo(BindingResult bindingResult) {
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        StringBuilder errMsg = new StringBuilder();
        for (ObjectError allError : allErrors) {
            FieldError fieldError = (FieldError) allError;
            String field = fieldError.getField();
            String defaultMessage = fieldError.getDefaultMessage();
            errMsg.append(field + ":" + defaultMessage + ";  ");
        }
        return errMsg;
    }
}
