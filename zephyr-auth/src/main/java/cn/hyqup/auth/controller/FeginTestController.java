package cn.hyqup.auth.controller;

import cn.hyqup.sys.api.HelloApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/1/31
 * @description:
 */
@RestController
public class FeginTestController {
    @Resource
    HelloApi helloApi;

    @GetMapping("/testFeign")
    public String testFeign() {
        return helloApi.sayHello();
    }
}
