package cn.hyqup.mall.api.factory;

import cn.hyqup.mall.api.HelloMallApi;
import cn.hyqup.mall.api.fallback.HelloMallApiFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/1/31
 * @description:
 */
@Component
public class HelloMallApiFallBackFactory implements FallbackFactory<HelloMallApi> {
    @Override
    public HelloMallApi create(Throwable throwable) {
        HelloMallApiFallbackImpl helloMallApiFallback = new HelloMallApiFallbackImpl();
        helloMallApiFallback.setCause(throwable);
        return helloMallApiFallback;
    }
}
