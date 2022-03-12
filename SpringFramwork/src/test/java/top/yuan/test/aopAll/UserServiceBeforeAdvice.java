package top.yuan.test.aopAll;

import top.yuan.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截方法: " + method.getName());
    }
}
