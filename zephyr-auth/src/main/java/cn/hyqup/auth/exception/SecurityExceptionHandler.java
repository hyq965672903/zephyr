package cn.hyqup.auth.exception;

import cn.hyqup.common.core.result.Result;
import cn.hyqup.common.core.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.oauth2.common.exceptions.*;
import org.springframework.security.web.util.ThrowableAnalyzer;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/3/10
 * @description:
 * 1、如果是 InternalAuthenticationServiceException 认证异常去异常堆里去捞
 * 2、如果是普通的 AuthenticationException 直接instanceof 转换
 * 3、如果是 Oauth2Exception 直接instanceof 转换
 * ps:InternalAuthenticationServiceException 是 AuthenticationException 的子类
 */
@Slf4j
public class SecurityExceptionHandler {
    public static Result handlerSecurityException(Exception e) {
        Result<?> result = null;
        if (e instanceof InternalAuthenticationServiceException) {
            log.info("security 包装异常");
            result = obtainInternalAuthentication(e);
            return result;
        }
        if (e instanceof AuthenticationException) {
            log.info("security 直接异常");
            result = handlerAuthenticationException(e);
            return result;
        }
        if (e instanceof OAuth2Exception) {
            log.info("oauth2 异常");
            result = handlerOAuth2Exception(e);
            return result;
        }
        result = Result.fail(ResultCode.USER_LOGIN_FAIL);
        return result;
    }


    /**
     * 从堆栈取获取异常 解析出具体的security 异常
     *
     * @param e
     * @return
     */
    private static Result<?> obtainInternalAuthentication(Exception e) {
        ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();
        Throwable[] causeChain = throwableAnalyzer.determineCauseChain(e);
        log.error("打印认证的异常堆栈 异常列表");
        for (Throwable throwable : causeChain) {
            log.error(throwable.getClass().getName());
        }
        Exception ase = (AccountExpiredException) throwableAnalyzer
                .getFirstThrowableOfType(AccountExpiredException.class, causeChain);
        if (ase != null) {
            log.info("[登录失败] - 用户账号过期");
            return Result.fail(ResultCode.USER_ACCOUNT_EXPIRED);
        }
        ase = (BadCredentialsException) throwableAnalyzer
                .getFirstThrowableOfType(BadCredentialsException.class, causeChain);
        if (ase != null) {
            log.info("[登录失败] - 用户密码错误");
            return Result.fail(ResultCode.USER_PASSWORD_ERROR);
        }
        ase = (CredentialsExpiredException) throwableAnalyzer
                .getFirstThrowableOfType(CredentialsExpiredException.class, causeChain);
        if (ase != null) {
            log.info("[登录失败] - 用户密码过期");
            return Result.fail(ResultCode.USER_PASSWORD_EXPIRED);
        }
        ase = (DisabledException) throwableAnalyzer
                .getFirstThrowableOfType(DisabledException.class, causeChain);
        if (ase != null) {
            log.info("[登录失败] - 用户被禁用");
            return Result.fail(ResultCode.USER_DISABLED);
        }
        ase = (LockedException) throwableAnalyzer
                .getFirstThrowableOfType(LockedException.class, causeChain);
        if (ase != null) {
            log.info("[登录失败] - 用户被锁定");
            return Result.fail(ResultCode.USER_LOCKED);
        }
        log.info("[登录失败] - 其他错误");
        return Result.fail(ResultCode.USER_LOGIN_FAIL);
    }

    /**
     * 处理认证异常
     *
     * @param e
     * @return
     */
    private static Result<?> handlerAuthenticationException(Exception e) {
        Result<?> result = null;
        if (e instanceof AccountExpiredException) {
            // 账号过期
            log.info("[登录失败] - 用户账号过期");
            result = Result.fail(ResultCode.USER_ACCOUNT_EXPIRED);

        } else if (e instanceof BadCredentialsException) {
            // 密码错误
            log.info("[登录失败] - 用户密码错误");
            result = Result.fail(ResultCode.USER_PASSWORD_ERROR);

        } else if (e instanceof CredentialsExpiredException) {
            // 密码过期
            log.info("[登录失败] - 用户密码过期");
            result = Result.fail(ResultCode.USER_PASSWORD_EXPIRED);

        } else if (e instanceof DisabledException) {
            // 用户被禁用
            log.info("[登录失败] - 用户被禁用");
            result = Result.fail(ResultCode.USER_DISABLED);

        } else if (e instanceof LockedException) {
            // 用户被锁定
            log.info("[登录失败] - 用户被锁定");
            result = Result.fail(ResultCode.USER_LOCKED);

        } else {
            // 其他错误
            log.error(String.format("[登录失败] - 其他错误"));
            result = Result.fail(ResultCode.USER_LOGIN_FAIL);
        }
        return result;
    }

    /**
     * 处理oauth2 相关异常
     *
     * @param e
     * @return
     */
    private static Result<?> handlerOAuth2Exception(Exception e) {
        Result<?> result = null;
        if (e instanceof UnsupportedGrantTypeException) {
            result = Result.fail(ResultCode.UNSUPPORTED_GRANT_TYPE);
        } else if (e instanceof InvalidTokenException
                && StringUtils.containsIgnoreCase(e.getMessage(), "Invalid refresh token (expired)")) {
            result = Result.fail(ResultCode.INVALID_TOKEN);
        } else if (e instanceof InvalidScopeException) {
            result = Result.fail(ResultCode.INVALID_SCOPE);
        } else if (e instanceof InvalidGrantException) {
            if (StringUtils.containsIgnoreCase(e.getMessage(), "Invalid refresh token")) {
                result = Result.fail(ResultCode.REFRESH_TOKEN_INVALID);
            } else if (StringUtils.containsIgnoreCase(e.getMessage(), "Invalid authorization code")) {
                result = Result.fail(ResultCode.AUTHORIZATION_CODE_INVALID);
            } else if (StringUtils.containsIgnoreCase(e.getMessage(), "locked")) {
                result = Result.fail(ResultCode.USER_LOCKED);
            }else if (StringUtils.containsIgnoreCase(e.getMessage(), "Bad credentials")){
                result = Result.fail(ResultCode.USER_PASSWORD_ERROR);
            }else {
                result = Result.fail(ResultCode.USER_LOGIN_FAIL);

            }
        }
        return result;
    }
}
