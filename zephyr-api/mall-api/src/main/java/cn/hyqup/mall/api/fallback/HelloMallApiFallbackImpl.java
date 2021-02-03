package cn.hyqup.mall.api.fallback;

import cn.hyqup.mall.api.HelloMallApi;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/1/31
 * @description:
 */
@Slf4j
@Component
public class HelloMallApiFallbackImpl implements HelloMallApi {
    @Setter
    private Throwable cause;

    @Override
    public String sayMallHello() {
        log.error("say Hello Fail", cause);
        return null;
    }
}
