package top.yuan.test.circular;

import top.yuan.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class SpouseAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("夫妻俩的切面；" + method);
    }
}
