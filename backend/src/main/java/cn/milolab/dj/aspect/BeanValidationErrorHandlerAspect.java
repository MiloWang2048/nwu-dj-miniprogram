package cn.milolab.dj.aspect;

import cn.milolab.dj.bean.response.LoginResponse;
import cn.milolab.dj.error.exception.BadRequestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;


/**
 * @author milowang
 */
@Component
@Aspect
public class BeanValidationErrorHandlerAspect {
    private static final Logger LOGGER = LogManager.getLogger();

    @Before("execution(public * cn.milolab.dj.controller..*(..))")
    public static void before(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        // 如果长度是奇数，设置为参数表长度减一
        int length = args.length - args.length % 2;
        for(int i=0;i<length;i+=2){
            if(!(args[i+1] instanceof BindingResult)){
                LOGGER.error("parameter auto validation failed on "+joinPoint.getStaticPart());
                return;
            }
            BindingResult bindingResult = (BindingResult) args[i+1];
            if (bindingResult.hasErrors()) {
                throw new BadRequestException(bindingResult.getAllErrors().get(0).getDefaultMessage());
            }
        }

    }
}
