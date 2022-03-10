package top.yuan.test.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class UserServiceInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return methodInvocation.proceed();
        } finally {
            System.out.println("\n监控 - Begin by AOP");
            System.out.println("方法名： " + methodInvocation.getMethod().getName());
            System.out.println("方法耗时： " + (System.currentTimeMillis() - start) + "ms");
            System.out.println("监控 - End \r\n");
        }
    }
}
