package cn.hyqup.common.web.response.handler;


import cn.hyqup.common.core.result.Result;
import cn.hyqup.common.web.response.annotation.ResponseResult;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Copyright © 2020灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2020/3/7
 * @description:响应体增强器，通过拦截器设置的是否存在响应注解标识来对数据做是否包装
 */
@Slf4j
@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice {
    //标识和ResponseResultInterceptor 中设置的一样
    public static final String RESPONSE_RESULT_ANN = "RESPONSE_RESULT_ANN";
    public static final String NO_NEED_FORMAT = "no_need_format";

    /**
     * true，才会执行beforeBodyWrite方法
     *
     * @param returnType
     * @param converterType
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        ResponseResult responseResultAnn = (ResponseResult) request.getAttribute(RESPONSE_RESULT_ANN);
        return responseResultAnn != null;
    }

    /**
     * json 转化数据
     *
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = sra.getRequest();
        String need = (String) httpServletRequest.getAttribute(NO_NEED_FORMAT);
        if (StringUtils.isBlank(need)) {
//            当body为String类型时 mediaType会变成 plain/text,这个时候转化为resutlt 对象会报对象转换异常
            if (body instanceof String) {
                return JSON.toJSONString(Result.success(body));
            }
            if (body instanceof Result) {
                return body;
            } else {
                return Result.success(body);
            }
        }
        return body;
    }
}
