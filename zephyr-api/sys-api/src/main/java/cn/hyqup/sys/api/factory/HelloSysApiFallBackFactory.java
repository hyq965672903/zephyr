package cn.hyqup.sys.api.factory;

import cn.hyqup.sys.api.HelloSysApi;
import cn.hyqup.sys.api.fallback.HelloSysApiFallbackImpl;
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
public class HelloSysApiFallBackFactory implements FallbackFactory<HelloSysApi> {
    @Override
    public HelloSysApi create(Throwable throwable) {
        HelloSysApiFallbackImpl helloSysApiFallback = new HelloSysApiFallbackImpl();
        helloSysApiFallback.setCause(throwable);
        return helloSysApiFallback;
    }
}
