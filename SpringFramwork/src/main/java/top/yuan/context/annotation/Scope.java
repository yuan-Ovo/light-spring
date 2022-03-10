package top.yuan.context.annotation;

import java.lang.annotation.*;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \  用于配置作用域的自定义注解
 *    通过配置Bean对象注解，拿到Bean对象的作用域，默认使用singleton
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {

    String value() default "singleton";
}
