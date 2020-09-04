package cn.milolab.dj.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记于controller方法上，用于启用手动权限检查
 *
 * @author milowang
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ManualRoleCheck {
    String value() default "USER";
}
