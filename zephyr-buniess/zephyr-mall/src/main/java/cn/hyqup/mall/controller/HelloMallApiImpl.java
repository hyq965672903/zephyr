package cn.hyqup.mall.controller;


import cn.hyqup.common.core.result.Result;
import cn.hyqup.mall.api.HelloMallApi;
import cn.hyqup.sys.api.HelloSysApi;
import cn.hyqup.sys.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/2/1
 * @description:
 */
@Slf4j
@RestController
public class HelloMallApiImpl implements HelloMallApi {
    @Autowired
    HelloSysApi helloSysApi;

    @Override
    public Result sayMallHello() {
        return Result.builder().build().success("hello sayMallHello");
    }

    @GetMapping("/test")
    public Result test() {
        Result userVOResult = helloSysApi.saySysHello();
        log.info(userVOResult.toString());
        if (userVOResult.getIsSuccess()) {
            return userVOResult;
        }
        return userVOResult;
    }
}
