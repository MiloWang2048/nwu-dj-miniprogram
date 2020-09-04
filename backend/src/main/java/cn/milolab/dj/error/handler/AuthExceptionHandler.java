package cn.milolab.dj.error.handler;

import cn.milolab.dj.bean.response.ErrorResponse;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 访问控制异常处理器
 *
 * @author milowang
 */
@RestControllerAdvice
public class AuthExceptionHandler {
    @ExceptionHandler(AuthorizationException.class)
    public ErrorResponse authorizationExceptionHandler(HttpServletRequest request,
                                                       HttpServletResponse response,
                                                       AuthorizationException ex) {
        response.setStatus(403);
        ErrorResponse responseBody = new ErrorResponse();
        responseBody.setStatus(403);
        responseBody.setTimestamp(new Date());
        responseBody.setMessage("未授权操作");
        responseBody.setPath(request.getServletPath());
        responseBody.setError("Forbidden");
        return responseBody;
    }

    @ExceptionHandler(AuthenticationException.class)
    public ErrorResponse authenticationExceptionHandler(HttpServletRequest request,
                                                        HttpServletResponse response,
                                                        AuthenticationException ex) {
        response.setStatus(401);
        ErrorResponse responseBody = new ErrorResponse();
        responseBody.setStatus(401);
        responseBody.setTimestamp(new Date());
        responseBody.setMessage("认证失败");
        responseBody.setPath(request.getServletPath());
        responseBody.setError("Unauthorized");
        return responseBody;
    }
}
