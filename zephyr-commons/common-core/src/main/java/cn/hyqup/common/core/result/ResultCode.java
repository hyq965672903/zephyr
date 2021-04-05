package cn.hyqup.common.core.result;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Copyright © 2020灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2020/3/7
 * @description:
 */

@AllArgsConstructor
@NoArgsConstructor
public enum ResultCode implements IResultCode {
    /**
     * SpringMVC默认级别的异常封装
     */
    METHOD_NOT_SUPPORT(false, "405", "请求方法类型不支持,请检查请求类型！"),
    MEDIA_TYPE_NOT_SUPPORT(false, "405", "HTTP媒体类型不支持异常!"),
    MEDIA_TYPE_NOT_ACCEPTABLE(false, "406", "客户端请求期望响应的媒体类型与服务器响应的媒体类型不一致!"),
    MISSING_PATH_VARIABLE(false, "500", "缺少可选的路径参数!"),
    MISSING_SERVLET_REQUEST_PARAMETER(false, "400", "缺少请求参数!"),
    REQUEST_BINDING(false, "400", "请求绑定异常!"),
    CONVERSION_NOT_SUPPORTED(false, "500", "参数绑定异常!"),
    TYPE_MIS_MATCH(false, "400", "类型不匹配异常!"),
    MESSAGE_NOT_READABLE(false, "400", "消息不可读异常!"),
    MESSAGE_NOT_WRITABLE(false, "500", "消息不可写异常!"),
    METHOD_ARGUMENT_NOT_VALID(false, "400", "请求参数不合法！"),
    MISSING_SERVLET_REQUEST_PART(false, "400", ""),
    BIND(false, "400", "请求参数不合法！"),
    NO_HANDLER_FOUND(false, "404", "没有找到合适的处理器,处理器可能不存在,请检查路径是否正确！"),
    ASYNC_REQUEST_TIMEOUT(false, "503", "异步请求超时"),

    /**
     * 不可预知系统异常
     */
    SERVER_ERROR(false, "99999", "抱歉，系统繁忙，请稍后重试！"),
    /**
     * 基本的返回
     */
    SUCCESS(true, "00000", "操作成功！"),
    FAILURE(false, "A0001", "操作失败！"),
    PARMERROR(false, "A0002", "请求参数不合法！"),

    /**
     * 系统可预知异常
     */
    UN_KNOW(false, "A0200", "未知错误"),
    NULLPOINT(false, "A0201", "空指针异常"),

    /**
     * 账户异常
     */
    USER_LOGIN_FAIL(false, "A0300", "用户登录失败"),
    USER_PASSWORD_ERROR(false, "A0301", "用户名或密码错误"),
    USER_ACCOUNT_EXPIRED(false, "A0302", "用户账号过期"),
    USER_PASSWORD_EXPIRED(false, "A0303", "用户密码过期"),
    USER_DISABLED(false, "A0304", "用户被禁用,请联系管理员"),
    USER_LOCKED(false, "A0305", "用户被锁定"),
    UNSUPPORTED_GRANT_TYPE(false, "A0306", "不支持该认证类型,grant_type错误"),
    INVALID_TOKEN(false, "A0307", "刷新令牌已过期，请重新登录"),
    INVALID_SCOPE(false, "A0308", "不是有效的scope值"),
    REFRESH_TOKEN_INVALID(false, "A0309", "refreshtoken无效"),
    AUTHORIZATION_CODE_INVALID(false, "A0310", "authorization code无效"),
    TOKEN_INVALID_OR_EXPIRED(false, "A0311", "token无效或者已过期自定义响应"),
    ACCESS_UNAUTHORIZED(false, "A0312", "访问未授权"),



    ;
    public Boolean isSuccess;
    public String code;
    public String message;

    @Override
    public Boolean isSuccess() {
        return this.isSuccess;
    }

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.message;
    }
}
