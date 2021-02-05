package cn.hyqup.common.core.exception;


import cn.hyqup.common.core.result.IResultCode;

/**
 * 自定义异常类型
 * @author hyq
 * @version 1.0
 * @create
 **/
public class CustomException extends RuntimeException {

    //错误代码
    IResultCode IResultCode;

    public CustomException(IResultCode IResultCode){
        this.IResultCode = IResultCode;
    }

    public IResultCode getIResultCode() {
        return IResultCode;
    }
}
