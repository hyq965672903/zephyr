package cn.hyqup.sys.controller;

import cn.hyqup.sys.api.HelloApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/1/31
 * @description:
 */
@RestController
public class HelloApiImpl implements HelloApi {
    @GetMapping("/sayHello")
    @Override
    public String sayHello() {
        return "hello feign";
    }
}
