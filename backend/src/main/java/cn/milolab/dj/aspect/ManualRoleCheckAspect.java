package cn.milolab.dj.aspect;

import cn.milolab.dj.annotation.ManualRoleCheck;
import cn.milolab.dj.error.exception.UnauthorizedException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 手动角色检查切面，检查所有标注了@ManualRoleCheck的方法
 *
 * @author milowang
 */
@Component
@Aspect
public class ManualRoleCheckAspect {

    /**
     * 从配置文件中获取配置，决定是否启用访问控制
     */
    @Value("${enableAccessControl}")
    private Boolean enabled;

    @Before("@annotation(cn.milolab.dj.annotation.ManualRoleCheck)")
    public void roleCheck(JoinPoint joinPoint){

        // 如果没有启用访问控制则直接跳过检查阶段
        if(!enabled) {
            return;
        }

        // 获取要检测的目标Role
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ManualRoleCheck manualRoleCheck = method.getAnnotation(ManualRoleCheck.class);

        // 获取subject，检查role
        Subject subject = SecurityUtils.getSubject();
        if(!subject.hasRole(manualRoleCheck.value())){

            // 如果没有所需权限，报403
            throw new UnauthorizedException(String.format("需要%s权限", manualRoleCheck.value()));
        }
    }
}
