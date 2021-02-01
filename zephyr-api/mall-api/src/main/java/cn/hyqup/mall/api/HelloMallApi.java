package cn.hyqup.mall.api;

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
@FeignClient("zephyr-mall")
public interface HelloMallApi {
    @GetMapping("/sayMallHello")
    public String sayMallHello();
}
