package cn.hyqup.common.web.exception;

import cn.hyqup.common.core.result.Result;
import cn.hyqup.common.core.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/2/6
 * @description: 参数校验异常处理类
 * 参考：https://juejin.cn/post/6844904003684302861
 */
@Slf4j
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
                                                         WebRequest request) {
        logger.error("参数绑定失败", ex);
        if (ex.hasErrors()) {
            List<Map<String, String>> list = new ArrayList<>();
            for (ObjectError objectError : ex.getAllErrors()) {
                Map<String, String> map = new HashMap<>();
                if (objectError instanceof FieldError) {
                    FieldError fieldError = (FieldError) objectError;
                    map.put("field", fieldError.getField());
                    map.put("message", fieldError.getDefaultMessage());
                } else {
                    map.put("field", objectError.getObjectName());
                    map.put("message", objectError.getDefaultMessage());
                }
                list.add(map);
            }
            return new ResponseEntity<>(Result.builder().build().failure(ResultCode.PARMERROR,list),HttpStatus.OK);
        }
        return super.handleBindException(ex, headers, status, request);
    }
}
