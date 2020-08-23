package cn.milolab.dj.aspect;

import cn.milolab.dj.annotation.ManualRoleCheck;
import cn.milolab.dj.error.exception.UnauthorizedException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.HttpClientErrorException;

import java.lang.reflect.Method;

/**
 * @author milowang
 */
@Component
@Aspect
public class ManualRoleCheckAspect {
    @Value("${enableAccessControl}")
    private Boolean enabled;

    @Before("@annotation(cn.milolab.dj.annotation.ManualRoleCheck)")
    public void roleCheck(JoinPoint joinPoint){
        if(!enabled) {
            return;
        }
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ManualRoleCheck manualRoleCheck = method.getAnnotation(ManualRoleCheck.class);
        Subject subject = SecurityUtils.getSubject();
        if(!subject.hasRole(manualRoleCheck.value())){
            throw new UnauthorizedException(String.format("需要%s权限", manualRoleCheck.value()));
        }
    }
}
