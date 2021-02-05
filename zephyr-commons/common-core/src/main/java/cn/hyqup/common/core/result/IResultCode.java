package cn.hyqup.common.core.result;

/**
 * Copyright © 2020灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2020/7/29
 * @description:
 */
public interface IResultCode {
    /**
     * 是否成功
     *
     * @return
     */
    Boolean isSuccess();

    /**
     * 返回状态码
     *
     * @return
     */
    String code();

    /**
     * 状态消息描述
     *
     * @return
     */
    String message();
}
