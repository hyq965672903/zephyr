package cn.hyqup.sys.api;

import cn.hyqup.commom.cloud.configurtion.FeignConfiguration;
import cn.hyqup.common.core.result.Result;
import cn.hyqup.sys.api.factory.HelloSysApiFallBackFactory;
import cn.hyqup.sys.vo.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/1/31
 * @description:
 */
@FeignClient(name = "zephyr-sys", configuration = FeignConfiguration.class,
        fallbackFactory = HelloSysApiFallBackFactory.class)
public interface HelloSysApi {
    @GetMapping("/saySysHello")
    public Result saySysHello();
}
