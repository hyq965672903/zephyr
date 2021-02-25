package cn.hyqup.auth.exception;

import cn.hyqup.common.core.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class AuthExceptionHandler {

    @ExceptionHandler({InternalAuthenticationServiceException.class, AuthenticationException.class, OAuth2Exception.class})
    public Result handleInternalAuthenticationServiceException(Exception e) {
        return SecurityExceptionHandler.handlerSecurityException(e);
    }

}
