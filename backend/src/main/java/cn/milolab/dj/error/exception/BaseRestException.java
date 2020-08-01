package cn.milolab.dj.error.exception;

/**
 * @author milowang
 */
public abstract class BaseRestException extends RuntimeException {
    public BaseRestException(String message) {
        super(message);
    }

    /**
     * 返回该异常对应的状态码，供异常处理器设置响应状态码
     *
     * @return 该异常对应的状态码
     */
    abstract public Integer getStatus();

    /**
     * 返回该异常对应的错误类型，供异常处理器设置响应体
     *
     * @return 该异常对应的错误类型
     */
    abstract public String getError();
}
