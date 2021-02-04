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
     * 基本的返回
     */
    SUCCESS(10000, "操作成功！"),
    FAIL(11111, "操作失败！");

    public int code;
    public String message;

    @Override
    public int code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.message;
    }
}
