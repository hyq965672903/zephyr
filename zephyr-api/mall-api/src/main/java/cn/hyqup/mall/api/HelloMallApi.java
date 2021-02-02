package cn.hyqup.mall.api;

import cn.hyqup.common.web.configurtion.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/2/1
 * @description:
 */
@FeignClient(name = "zephyr-mall", configuration = FeignConfiguration.class)
public interface HelloMallApi {
    @GetMapping("/sayMallHello")
    public String sayMallHello();
}
