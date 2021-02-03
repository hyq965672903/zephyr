package cn.hyqup.sys.controller;

import cn.hyqup.sys.api.HelloSysApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
@RestController
public class HelloSysApiImpl implements HelloSysApi {
//    @Autowired
//    MyProperties myProperties;

    @Override
    public String saySysHello() {
//        log.info(myProperties.toString());
//        int i= 1/0;
        return "hello saySysHello";
    }
}
