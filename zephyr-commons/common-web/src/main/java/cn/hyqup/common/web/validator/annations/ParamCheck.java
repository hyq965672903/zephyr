package cn.hyqup.common.web.validator.annations;

import cn.hyqup.common.web.validator.core.ParamCheckValidator;
import cn.hyqup.common.web.validator.enums.Check;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/2/8
 * @description:
 * message/groups/payload 是JSR303规范必须的参数
 */
@Target(FIELD)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {ParamCheckValidator.class})
public @interface ParamCheck {

    /**
     * 实际上会取验证函数的msg 自定义此值的话优先级会覆盖的
     */
    String message() default "参数验证错误";

    /**
     * 分组
     */
    Class<?>[] groups() default {};

    /**
     * 极少用到 携带数据
     */
    Class<? extends Payload>[] payload() default {};
    /**
     * 验证函数
     *
     * @return
     */
    Check fun();

    /**
     * 多个值逗号隔开 此值和fun的里的验证方法息息相关
     */
    String express() default "";

}
