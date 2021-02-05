package cn.hyqup.common.core.exception;


import cn.hyqup.common.core.result.IResultCode;

/**
 * Copyright © 2020灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2020/3/7
 * @description: 统一异常捕获类
 */
public class ExceptionCast {
    public static void cast(IResultCode IResultCode){
        throw new CustomException(IResultCode);
    }
}
