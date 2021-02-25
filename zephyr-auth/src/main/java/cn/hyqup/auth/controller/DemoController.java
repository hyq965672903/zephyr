package cn.hyqup.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/2/25
 * @description:
 */
@RestController
public class DemoController {
    @RequestMapping(path = "/hello")
    public String hello() {
        return "验证通过";
    }
}
