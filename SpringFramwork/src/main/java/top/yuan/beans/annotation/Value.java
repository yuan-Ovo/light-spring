package top.yuan.beans.annotation;

import java.lang.annotation.*;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Value {

    /**
     * 实际属性值的表达式 #{Properties.someProperty}
     * @return 属性值
     */
    String value();
}
