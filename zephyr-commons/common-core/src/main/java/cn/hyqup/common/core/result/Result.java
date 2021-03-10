package cn.hyqup.common.core.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Copyright © 2020灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2020/3/7
 * @description:
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {
    private Boolean isSuccess;
    private String code;
    private String message;
    private T data;

    /**
     * 下面是重写构造方法
     *
     * @param resultCode
     */
    private Result(IResultCode resultCode) {
        this(resultCode.isSuccess(), resultCode.code(), resultCode.message(), null);
    }

    private Result(IResultCode resultCode, String message) {
        this(resultCode.isSuccess(), resultCode.code(), message, null);
    }

    private Result(IResultCode resultCode, T data) {
        this(resultCode.isSuccess(), resultCode.code(), resultCode.message(), data);
    }

    private Result(IResultCode resultCode, String message, T data) {
        this(resultCode.isSuccess(), resultCode.code(), message, data);
    }


    /**
     * 静态方法
     */
    public static <T> Result<T> success(IResultCode resultCode) {
        return new Result<>(resultCode);
    }

    public static <T> Result<T> success(String message) {
        return new Result<>(ResultCode.SUCCESS, message);
    }

    public static <T> Result<T> success(IResultCode resultCode, String message) {
        return new Result<>(resultCode, message);
    }

    /**
     * 处理成功返回数据情况
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> data(T data) {
        return data(ResultCode.SUCCESS.message, data);
    }

    public static <T> Result<T> data(String message, T data) {
        return data(ResultCode.SUCCESS.isSuccess(), ResultCode.SUCCESS.code(), message, data);
    }

    public static <T> Result<T> data(boolean isSuccess, String code, String message, T data) {
        return new Result<>(isSuccess, code, null == data ? ResultCode.SUCCESS.message : message, data);
    }


    /**
     * 处理失败的情况下
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail() {
        return new Result<>(ResultCode.FAILURE);
    }

    public static <T> Result<T> fail(IResultCode resultCode) {
        return new Result<>(resultCode);
    }

    public static <T> Result<T> fail(IResultCode resultCode, String message) {
        return new Result<>(resultCode, message);
    }

    public static <T> Result<T> fail(String message) {
        return new Result<>(ResultCode.FAILURE, message);
    }

    public static <T> Result<T> fail(boolean isSuccess, String code, String message) {
        return new Result<>(isSuccess, code, message, null);
    }

    public static <T> Result<T> condition(boolean flag) {
        return flag ? success(ResultCode.SUCCESS.message()) : fail(ResultCode.FAILURE.message());
    }
}
