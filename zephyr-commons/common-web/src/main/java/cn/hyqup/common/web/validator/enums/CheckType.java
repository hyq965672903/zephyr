package cn.hyqup.common.web.validator.enums;

import cn.hyqup.common.web.validator.core.ParamValidator;
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
public enum CheckType {

    /**
     * 自定义方法佳肴
     */
    Custom("参数验证不通过", CheckUtil::customValidate);

    public String msg;

    public BiFunction<Object, Class<? extends ParamValidator>, Boolean> fun;

}
