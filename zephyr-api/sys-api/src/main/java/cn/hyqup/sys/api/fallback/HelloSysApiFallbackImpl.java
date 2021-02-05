package cn.hyqup.sys.api.fallback;

import cn.hyqup.common.core.result.Result;
import cn.hyqup.sys.api.HelloSysApi;
import cn.hyqup.sys.vo.UserVO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/1/31
 * @description:
 */
@Slf4j
@Component
public class HelloSysApiFallbackImpl implements HelloSysApi {
    @Setter
    private Throwable cause;

    @Override
    public Result saySysHello() {
        log.error("say Hello2 Fail", cause);
        return null;
    }
}
