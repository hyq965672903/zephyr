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

    public void setResultCode(IResultCode IResultCode) {
        this.code = IResultCode.code();
        this.message = IResultCode.message();
        this.isSuccess = IResultCode.isSuccess();
    }

    /**
     * 响应成功
     *
     * @return
     */
    public Result<T> success() {
        Result<T> result = new Result<T>();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public Result<T> success(T data) {
        Result<T> result = new Result<T>();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }


    /**
     * 响应失败
     *
     * @return
     */
    public Result<T> failure() {
        Result<T> result = new Result<T>();
        result.setResultCode(ResultCode.FAIL);
        return result;
    }

    public Result<T> failure(IResultCode iResultCode) {
        Result<T> result = new Result<T>();
        result.setResultCode(iResultCode);
        return result;
    }

    public Result<T> failure(T data) {
        Result<T> result = new Result<T>();
        result.setResultCode(ResultCode.FAIL);
        result.setData(data);
        return result;
    }
}
