package cn.hyqup.auth.component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/3/12
 * @description:
 */
@Component
@AllArgsConstructor
@Slf4j
public class InitPermissionRoles implements CommandLineRunner {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void run(String... args) throws Exception {
        redisTemplate.delete("auth:permission:roles");
        Map<String, List<String>> permissionRoles = new TreeMap<>();
        List<String> roles = Lists.newArrayList("ROLE_admin");
        permissionRoles.put("*_/zephyr-auth/**", roles);
        redisTemplate.opsForHash().putAll("auth:permission:roles", permissionRoles);
    }
}
