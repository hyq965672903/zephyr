package cn.hyqup.common.web.exception;

import cn.hyqup.common.core.result.Result;
import cn.hyqup.common.core.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/2/7
 * @description: https://www.w3cschool.cn/spring_mvc_documentation_linesh_translation/spring_mvc_documentation_linesh_translation-ewm227sd.html
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ValidateExceptionCatch extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info(ex.getMessage());
        log.info(ex.getParameterType());
        log.info(ex.getParameterType());
        return new ResponseEntity<>(Result.builder().build().failure(ResultCode.PARMERROR, ex.getMessage()), HttpStatus.OK);

    }
}
