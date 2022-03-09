package top.yuan.aop;

import java.lang.reflect.Method;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \ 是切点的一部分{@link Pointcut}，检测目标方法是否能够切入
 */
public interface MethodMatcher {
    /**
     * 检测给定方法是否匹配
     * @param method 待切入的方法
     * @param targetClass 目标类
     * @return 该方法是否能够切入
     */
    boolean matches(Method method, Class<?> targetClass);
}
