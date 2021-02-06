package cn.hyqup.common.web.validator;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/2/6
 * @description:
 */
@Component
@Aspect
public class ValidatorAspect {
    @Resource
    private LocalValidatorFactoryBean localValidatorFactoryBean;

    public ValidatorAspect() {
    }

    /**
     * 方式1：切入点(
     */
    @Pointcut(
            "@annotation(org.springframework.web.bind.annotation.RequestMapping)" +
                    "||@annotation(org.springframework.web.bind.annotation.GetMapping)" +
                    "||@annotation(org.springframework.web.bind.annotation.PostMapping)" +
                    "||@annotation(org.springframework.web.bind.annotation.PutMapping)"
    )
//    @Pointcut("execution(* com.*..controller.*.*(..))")//方式2
    private void parameterPointCut() {
    }


}
