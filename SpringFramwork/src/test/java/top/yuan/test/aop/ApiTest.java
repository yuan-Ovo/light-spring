package top.yuan.test.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import top.yuan.aop.AdvisedSupport;
import top.yuan.aop.ClassFilter;
import top.yuan.aop.TargetSource;
import top.yuan.aop.aspectj.AspectJExpressionPointcut;
import top.yuan.aop.aspectj.AspectJExpressionPointcutAdvisor;
import top.yuan.aop.framework.ProxyFactory;
import top.yuan.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import top.yuan.context.support.ClassPathXmlApplicationContext;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class ApiTest {

    private AdvisedSupport advisedSupport;

    @BeforeEach
    public void init() {
        IUserService userService = new UserService();
        advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* top.yuan.test.aop.IUserService.*(..))"));
    }

    @Test
    public void test_proxyFactory() {
        advisedSupport.setProxyTargetClass(false);
        IUserService proxy = (IUserService) new ProxyFactory(advisedSupport).getProxy();

        System.out.println(proxy.queryUserInfo());
    }

    @Test
    public void test_beforeAdvice() {
        UserServiceBeforeAdvice beforeAdvice = new UserServiceBeforeAdvice();
        MethodBeforeAdviceInterceptor interceptor = new MethodBeforeAdviceInterceptor(beforeAdvice);
        advisedSupport.setMethodInterceptor(interceptor);

        IUserService proxy = (IUserService) new ProxyFactory(advisedSupport).getProxy();
        System.out.println(proxy.queryUserInfo());
    }

    @Test
    public void test_app() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_aop.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println(userService.queryUserInfo());
    }

    @Test
    public void test_advisor() {
        IUserService userService = new UserService();

        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression("execution(* top.yuan.test.aop.IUserService.*(..))");
        advisor.setAdvice(new MethodBeforeAdviceInterceptor(new UserServiceBeforeAdvice()));

        ClassFilter classFilter = advisor.getPointcut().getClassFilter();
        if (classFilter.matches(userService.getClass())) {
            AdvisedSupport advisedSupport = new AdvisedSupport();

            TargetSource targetSource = new TargetSource(userService);

            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(false);
            IUserService proxy = (IUserService) new ProxyFactory(advisedSupport).getProxy();
            System.out.println(proxy.queryUserInfo());
        }
    }
}
