package cn.hyqup.mall.controller;


import cn.hyqup.mall.api.HelloMallApi;
import cn.hyqup.sys.api.HelloSysApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/2/1
 * @description:
 */
@RestController
public class HelloMallApiImpl implements HelloMallApi {
    @Autowired
    HelloSysApi helloSysApi;

    @Override
    public String sayMallHello() {
        return "hello sayMallHello";
    }

    @GetMapping("/test")
    public String test() {
        return helloSysApi.saySysHello();
    }
}
