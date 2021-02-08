package cn.hyqup.common.web.validator.core;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/2/8
 * @description: 特殊的自定义类
 */
@FunctionalInterface
public interface ParamValidator<T> {
    /**
     * 自定义实现方法  需要用户自己实现 并让此实现类被spring 容器管理
     *
     * @param value 值
     * @return
     */
    Boolean validate(T value);
}
