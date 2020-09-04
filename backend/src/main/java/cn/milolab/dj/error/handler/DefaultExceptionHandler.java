package cn.milolab.dj.error.handler;

import cn.milolab.dj.bean.response.ErrorResponse;
import cn.milolab.dj.error.exception.BaseRestException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 自定义异常（定义在cn.milolab.dj.error.exception）的处理器
 *
 * @author milowang
 */
@RestControllerAdvice
public class DefaultExceptionHandler {
    @ExceptionHandler(BaseRestException.class)
    public ErrorResponse invalidRequestArgumentsExceptionHandler(HttpServletRequest request,
                                                                 HttpServletResponse response,
                                                                 BaseRestException ex) {
        response.setStatus(ex.getStatus());
        ErrorResponse responseBody = new ErrorResponse();
        responseBody.setStatus(ex.getStatus());
        responseBody.setTimestamp(new Date());
        responseBody.setMessage(ex.getMessage());
        responseBody.setPath(request.getServletPath());
        responseBody.setError(ex.getError());
        return responseBody;
    }
}
