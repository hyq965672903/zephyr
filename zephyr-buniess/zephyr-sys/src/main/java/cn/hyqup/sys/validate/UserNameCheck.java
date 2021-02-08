package cn.hyqup.sys.validate;

import cn.hyqup.common.web.validator.core.ParamValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/2/8
 * @description:
 */
@Slf4j
@Component
public class UserNameCheck implements ParamValidator {
    @Override
    public Boolean validate(Object value) {
        log.info("进入自定义规则校验");
        return false;
    }
}
