package cn.hyqup.sys.api;

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
@FeignClient("zephyr-sys")
public interface HelloSysApi {
    @GetMapping("/saySysHello")
    public String saySysHello();
}
