package cn.hyqup.auth.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/3/10
 * @description: 可以异步处理登陆成功后的事件
 */
@Slf4j
@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        log.info("登陆成功，后续做在线统计相关");
    }
}
