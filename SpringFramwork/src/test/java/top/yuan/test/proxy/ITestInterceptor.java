package top.yuan.test.proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class ITestInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return invocation.proceed();
        } finally {
            System.out.println("\n监控 - Begin by AOP");
            System.out.println("方法名： " + invocation.getMethod().getName());
            System.out.println("方法耗时： " + (System.currentTimeMillis() - start) + "ms");
            System.out.println("监控 - End \r\n");
        }
    }
}
