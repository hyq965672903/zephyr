package cn.hyqup.sys.controller;

import cn.hyqup.common.core.exception.ExceptionCast;
import cn.hyqup.common.core.result.Result;
import cn.hyqup.common.core.result.ResultCode;
import cn.hyqup.common.web.response.annotation.ResponseResult;
import cn.hyqup.sys.DTO.Dog;
import cn.hyqup.sys.DTO.School;
import cn.hyqup.sys.api.HelloSysApi;
import cn.hyqup.sys.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/1/31
 * @description:
 */
@Slf4j
@Validated
@RestController
public class HelloSysApiImpl implements HelloSysApi {
//    @Autowired
//    MyProperties myProperties;

    @Override
    public Result saySysHello() {
//        ExceptionCast.cast(ResultCode.NULLPOINT);
        UserVO userVO = UserVO.builder()
                .userName("张三")
                .build();
        return Result.data(userVO);
    }

    @GetMapping("/hello1")
    public String hello1() {
//        int i = 1 / 0;
        ExceptionCast.cast(ResultCode.NULLPOINT);
        return "hello1";
    }

    @ResponseResult
    @GetMapping("/hello2")
    public String hello2() {
        return "hello2";
    }

    @ResponseResult
    @GetMapping("/hello3")
    public String hello3() {
        return "张三";
    }

    @ResponseResult
    @PostMapping("/hello4")
    public String hello4(@Validated @RequestBody Dog dog) {
        log.info(dog.toString());
        return dog.toString();
    }

    @ResponseResult
    @GetMapping("/hello5")
    public String hello5(
//            @Min(value = 1, message = "班级最小只能1")
//            @Max(value = 99, message = "班级最大只能99")
            @RequestParam(name = "grade")
                    Integer grade,
            @Email(message = "邮箱格式错误")
//            @ParamCheck(fun = Check.Custom, message = "错误", express = "userNameCheck")
//            @NotBlank(message = "不能为空")
            @RequestParam(name = "email", required = false)
                    String email) {
        log.info(grade + email);
        return "张三";
    }

    @ResponseResult
    @PostMapping("/hello6")
    public String hello6(@Validated @RequestBody School school) {
        log.info(school.toString());
        return "张三";
    }


}

