package cn.hyqup.auth;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;


/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/3/10
 * @description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordEncoderTest {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void test1() {
        String encode = passwordEncoder.encode("zephyrsecret");
        log.info(encode);
    }

    @Test
    public void test2() {
        PathMatcher pathMatcher = new AntPathMatcher();
        boolean match = pathMatcher.match("POST_/zephyr/sys/**", "/zephyr/sys/hello");
        boolean match2 = pathMatcher.match("POST_/zephyr/sys/**", "POST_/zephyr/sys/hello");
        boolean match3 = pathMatcher.match("*_/zephyr/sys/**", "POST_/zephyr/sys/hello");
        log.info(match+"");
        log.info(match2+"");
        log.info(match3+"");
    }


}
