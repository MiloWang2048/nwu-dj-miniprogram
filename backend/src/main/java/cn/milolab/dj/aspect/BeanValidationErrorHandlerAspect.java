package cn.milolab.dj.aspect;

import cn.milolab.dj.error.exception.BadRequestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;


/**
 * 检查controller方法输入的参数，如果不符合bean validation则报错400
 *
 * @author milowang
 */
@Component
@Aspect
public class BeanValidationErrorHandlerAspect {
    private static final Logger LOGGER = LogManager.getLogger();

    /*
     * 约定controller方法的参数表为以下格式：
     * bean1, bindResult1, bean2, bindResult2, ...
     * 其中bean必须用@validated标记，bindResult必须为org.springframework.validation.BindingResult
     * */

    @Before("execution(public * cn.milolab.dj.controller..*(..))")
    public static void before(JoinPoint joinPoint) {

        // 获取方法被调用时的参数表
        Object[] args = joinPoint.getArgs();

        // 如果长度是奇数，忽略最后一个，设置扫描长度为参数表长度减一
        int length = args.length - args.length % 2;

        // 检查参数表的bindResult，如果有错误则抛出异常，异常会阻断handler继续执行
        for (int i = 0; i < length; i += 2) {
            if (!(args[i + 1] instanceof BindingResult)) {
                LOGGER.error("parameter auto validation failed on " + joinPoint.getStaticPart());
                return;
            }
            BindingResult bindingResult = (BindingResult) args[i + 1];
            if (bindingResult.hasErrors()) {
                throw new BadRequestException(bindingResult.getAllErrors().get(0).getDefaultMessage());
            }
        }

    }
}
