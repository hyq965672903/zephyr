package cn.hyqup.common.web.validator.utils;

import cn.hyqup.common.web.validator.core.ParamValidator;

import java.util.function.Function;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/2/8
 * @description:
 */
public class CheckUtil {

    public static Boolean customValidate(Object value, Class<? extends ParamValidator> clazz) {
        ParamValidator bean = SpringContextHolder.getBean(clazz);
        if (bean == null) {
            throw new IllegalArgumentException("invalied bean, this bean can not  found in spring context");
        }
        Function<Object, Boolean> func = bean::validate;
        return func.apply(value);
    }
}
