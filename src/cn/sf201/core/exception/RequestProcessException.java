package cn.sf201.core.exception;

/**
 * Created by sf201 on 2017/8/16.
 */
//请求处理异常
public class RequestProcessException extends Exception {
    public RequestProcessException() {
    }

    public RequestProcessException(String message) {
        super(message);
    }
}
