package top.yuan.beans.annotation;

import java.lang.annotation.*;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Qualifier {

    String value() default "";
}
