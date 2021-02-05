package cn.hyqup.common.core.result;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Copyright © 2020灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2020/3/7
 * @description:
 */

@AllArgsConstructor
@NoArgsConstructor
public enum ResultCode implements IResultCode {
    /**
     * 不可预知系统异常
     */
    SERVER_ERROR(false, "99999", "抱歉，系统繁忙，请稍后重试！"),
    /**
     * 基本的返回
     */
    SUCCESS(true, "00000", "操作成功！"),
    FAIL(false, "A0001", "操作失败！"),

    /**
     * 系统可预知异常
     */
    NULLPOINT(false, "A0201", "空指针异常");


    public Boolean isSuccess;
    public String code;
    public String message;

    @Override
    public Boolean isSuccess() {
        return this.isSuccess;
    }

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.message;
    }
}
