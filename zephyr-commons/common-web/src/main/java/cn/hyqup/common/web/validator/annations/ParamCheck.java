package cn.hyqup.common.web.validator.annations;

import cn.hyqup.common.web.validator.core.ParamCheckValidator;
import cn.hyqup.common.web.validator.core.ParamValidator;
import cn.hyqup.common.web.validator.enums.CheckType;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/2/8
 * @description: message/groups/payload 是JSR303规范必须的参数
 */
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {ParamCheckValidator.class})
public @interface ParamCheck {

    /**
     * 返回错误信息
     */
    String message() default "参数校验不合法";

    /**
     * 分组
     */
    Class<?>[] groups() default {};

    /**
     * 极少用到 携带数据
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * 验证类型 必填
     *
     * @return
     */
    CheckType checkType();

    /**
     * 自定义处理入参的类
     * 使用自定义接口实现参数校验的时候需要指定
     *
     * @return
     */
    Class<? extends ParamValidator> clazz() default ParamValidator.class;
}
