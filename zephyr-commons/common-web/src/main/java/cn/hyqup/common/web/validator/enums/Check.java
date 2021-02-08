package cn.hyqup.common.web.validator.enums;

import cn.hyqup.common.web.validator.utils.CheckUtil;
import lombok.AllArgsConstructor;

import java.util.function.BiFunction;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/2/8
 * @description:
 */
@AllArgsConstructor
public enum Check {

    /**
     * 特殊 用于自定义验证器逻辑   此时 express = beanName
     */
    Custom("参数验证不通过", CheckUtil::customValidate);

    public String msg;

    /**
     * BiFunction：接收字段值(Object)和 表达式(String)，返回是否符合规则(Boolean)
     */
    public BiFunction<Object, String, Boolean> fun;

}
