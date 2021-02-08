package cn.hyqup.common.web.validator.core;

import cn.hyqup.common.web.validator.annations.ParamCheck;
import cn.hyqup.common.web.validator.enums.Check;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/2/8
 * @description:
 */
public class ParamCheckValidator implements ConstraintValidator<ParamCheck, Object> {

    private static final String DEFAULT_MSG = "参数验证错误";
    private Check func;
    private String express;
    private String msg;

    @Override
    public void initialize(ParamCheck constraintAnnotation) {
        func = constraintAnnotation.fun();
        express = constraintAnnotation.express();
        msg = constraintAnnotation.message();
        if (DEFAULT_MSG.equals(msg)) {
            msg = func.msg + express;
        }
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (null == value) {
            return true;
        }
        String tmpMsg = msg;
        Boolean res = false;
        try {
            res = func.fun.apply(value, express);
        } catch (Exception e) {
            String errorMessage = "";
            if (e.getCause() != null && e.getCause().getMessage() != null) {
                errorMessage = e.getCause().getMessage();
            } else {
                errorMessage = e.getMessage();
            }
            tmpMsg = msg + "; raw exception occured, info: " + errorMessage;
        }
        if (!res) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(tmpMsg)
                    .addConstraintViolation();
        }
        return res;
    }
}
