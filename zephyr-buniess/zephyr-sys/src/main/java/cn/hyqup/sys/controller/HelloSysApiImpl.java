package cn.hyqup.sys.controller;

import cn.hyqup.common.web.response.annotation.ResponseResult;
import cn.hyqup.sys.api.HelloSysApi;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/hello1")
    public String hello1() {
        return "hello1";
    }

    @ResponseResult
    @GetMapping("/hello2")
    public String hello2() {
        return "hello2";
    }

    @ResponseResult
    @GetMapping("/hello3")
    public User hello3() {

        return User.builder()
                .userName("张三")
                .build();
    }


}

@Builder
@Data
class User {
    private String userName;
    private String age;
    private List<String> foods;
}
