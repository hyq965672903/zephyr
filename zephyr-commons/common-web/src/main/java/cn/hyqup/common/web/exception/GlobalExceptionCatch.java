package cn.hyqup.common.web.exception;


import cn.hyqup.common.core.exception.CustomException;
import cn.hyqup.common.core.result.IResultCode;
import cn.hyqup.common.core.result.Result;
import cn.hyqup.common.core.result.ResultCode;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
     * 业务异常
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
        return Result.fail(iResultCode);
    }

    /**
     * 服务端异常
     * 捕获可预知系统异常和不可预知系统异常
     * ps:从EXCEPTIONS中找异常类型所对应的错误代码，如果找到了将错误代码响应给用户，如果找不到给用户响应99999异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Result exception(Exception exception) {
        log.error("catch exception:{}", exception.getMessage());
        exception.printStackTrace();
        if (EXCEPTIONS == null) {
            EXCEPTIONS = builder.build();
        }
        IResultCode IResultCode = EXCEPTIONS.get(exception.getClass());
        if (IResultCode != null) {
            return Result.fail(IResultCode);
        } else {
            return Result.fail(ResultCode.SERVER_ERROR);
        }
    }
    static {
        /**
         * 定义系统可预知异常
         */
        builder.put(NullPointerException.class, ResultCode.NULLPOINT);
    }
}
