package cn.hyqup.common.web.validator.core;

import cn.hyqup.common.web.validator.annations.ParamCheck;
import cn.hyqup.common.web.validator.enums.CheckType;

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

    private String message;
    private CheckType checkType;
    private Class<? extends ParamValidator> clazz;

    @Override
    public void initialize(ParamCheck paramCheck) {
        message = paramCheck.message();
        checkType = paramCheck.checkType();
        clazz= paramCheck.clazz();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (null == value) {
            return true;
        }
        String tmpMsg = message;
        Boolean res = false;
        try {
            res = checkType.fun.apply(value, clazz);
        } catch (Exception e) {
            String errorMessage = "";
            if (e.getCause() != null && e.getCause().getMessage() != null) {
                errorMessage = e.getCause().getMessage();
            } else {
                errorMessage = e.getMessage();
            }
            tmpMsg = message + "; raw exception occured, info: " + errorMessage;
        }
        if (!res) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(tmpMsg)
                    .addConstraintViolation();
        }
        return res;
    }
}
