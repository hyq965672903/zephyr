package cn.hyqup.common.web.validator.config;

import cn.hyqup.common.web.validator.utils.SpringContextHolder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/2/8
 * @description:
 */
@Configuration
@Import( SpringContextHolder.class)
public class ValidateAutoConfig {
}
