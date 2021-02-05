package cn.hyqup.common.web.validator;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/2/5
 * @description:
 */
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = Check.CheckValidator.class)
public @interface Check {
    String message() default ""; // 自定义异常返回信息

    CheckType type(); // 自定义校验字段

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 校验实现
     * 实现ConstraintValidator接口，这是个泛型接口，泛型中第一个是自定义的注解，第二个是注解使用的类型。
     * 这里就是我们调用的字段校验方法
     */
    class CheckValidator implements ConstraintValidator<Check,Object> {
        private CheckType type;

        @Override
        public void initialize(Check constraintAnnotation) {
            this.type=constraintAnnotation.type();
        }

        @Override
        public boolean isValid(Object value, ConstraintValidatorContext context) {
            return CheckType.validate(type,value);
        }
    }
}
