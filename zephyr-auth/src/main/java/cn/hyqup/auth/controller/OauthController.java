package cn.hyqup.auth.controller;

import cn.hyqup.common.core.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/3/7
 * @description: 重写 获取token的controller
 */
@RestController
@RequestMapping("/oauth")
public class OauthController {
    @Autowired
    private TokenEndpoint tokenEndpoint;

    //自定义返回信息添加基本信息
    @PostMapping("/token")
    public Result postAccessTokenWithUserInfo(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        OAuth2AccessToken accessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        Map<String, Object> data = new LinkedHashMap();
        data.put("accessToken", accessToken.getValue());
        data.put("token_type", accessToken.getTokenType());
        data.put("refreshToken", accessToken.getRefreshToken().getValue());
        data.put("scope", accessToken.getScope());
        data.put("expires_in", accessToken.getExpiresIn());
        data.put("jti", accessToken.getAdditionalInformation().get("jti"));
        return Result.data(data);
    }
}
