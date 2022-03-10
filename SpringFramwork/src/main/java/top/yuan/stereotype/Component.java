package top.yuan.stereotype;

import java.lang.annotation.*;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \  扫描带有注释的组件。基于注释的配置和类路径扫描，此类被是为自动扫描的候选对象
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

    String value() default "";
}
