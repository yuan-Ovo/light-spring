package top.yuan.aop;

import java.lang.reflect.Method;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public interface MethodBeforeAdvice extends BeforeAdvice{
    /**
     * 在一个给定方法执行前调用
     * @param method 需要执行的方法
     * @param args 方法参数
     * @param target 调用方法的对象（需要反射调用，可能是null
     * @throws Throwable 抛出所有异常
     */
    void before(Method method, Object[] args, Object target) throws Throwable;
}
