package cn.hyqup.common.core.result;

import lombok.Data;

import java.io.Serializable;

/**
 * Copyright © 2020灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2020/3/7
 * @description:
 */
@Data
public class Result implements Serializable {
    private Integer code;
    private String message;
    private Object data="";

    public Result() {
    }

    public Result(IResultCode IResultCode, Object data) {
        this.code = IResultCode.code();
        this.message = IResultCode.message();
        this.data = data;
    }

    public void setResultCode(IResultCode IResultCode) {
        this.code = IResultCode.code();
        this.message = IResultCode.message();
}

    public static Result success() {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static Result failure(IResultCode IResultCode) {
        Result result = new Result();
        result.setResultCode(IResultCode);
        return result;
    }

    public static Result failure(IResultCode IResultCode, Object data) {
        Result result = new Result();
        result.setResultCode(IResultCode);
        result.setData(data);
        return result;
    }


    public static Result failure(String message) {
        Result result = new Result();
        result.setCode(401);
        result.setMessage(message);
        return result;
    }

}
