package cn.hyqup.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/2/25
 * @description:
 */
public interface ZephyrUserDetailsService extends UserDetailsService {

    /**
     * 根据电话号码查询用户
     *
     * @param mobile
     * @return
     */
    UserDetails loadUserByMobile(String mobile);

}
