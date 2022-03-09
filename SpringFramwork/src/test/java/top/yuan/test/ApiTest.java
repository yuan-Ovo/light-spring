package top.yuan.test;

import cn.hutool.core.io.IoUtil;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import top.yuan.aop.AdvisedSupport;
import top.yuan.aop.MethodMatcher;
import top.yuan.aop.TargetSource;
import top.yuan.aop.aspectj.AspectJExpressionPointcut;
import top.yuan.aop.framework.Cglib2AopProxy;
import top.yuan.aop.framework.JdkDynamicAopProxy;
import top.yuan.aop.framework.ReflectiveMethodInvocation;
import top.yuan.beans.factory.support.DefaultListableBeanFactory;
import top.yuan.beans.factory.xml.XmlBeanDefinitionReader;
import top.yuan.context.support.ClassPathXmlApplicationContext;
import top.yuan.core.io.DefaultResourceLoader;
import top.yuan.core.io.Resource;
import top.yuan.test.proxy.ITestInterceptor;
import top.yuan.test.proxy.ITestInterface;
import top.yuan.test.common.MyBeanFactoryPostProcessor;
import top.yuan.test.common.MyBeanPostProcessor;
import top.yuan.test.event.CustomEvent;
import top.yuan.test.proxy.ITestInterfaceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class ApiTest {

    private DefaultResourceLoader resourceLoader;

    @BeforeEach
    public void init() {
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void test_classpath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String read = IoUtil.readUtf8(inputStream);
        System.out.println(read);
    }

    @Test
    public void test_file() throws IOException {
        Resource resource = resourceLoader.getResource("src/test/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println("fileLoader: " + content);
    }

    @Test
    public void test_url() throws IOException {
        Resource resource = resourceLoader.getResource("https://github.com/jakeybroser/light-spring/blob/master/pom.xml");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println("url: " + content);
    }

    @Test
    public void test_xml() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        UserService userService = factory.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }

    @Test
    public void test_FactoryPostProcessAndBeanPostProcessor() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        MyBeanFactoryPostProcessor factoryPostProcessor = new MyBeanFactoryPostProcessor();
        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();

        factoryPostProcessor.postProcessBeanFactory(beanFactory);
        beanFactory.addBeanPostProcessor(beanPostProcessor);

        UserService userService = beanFactory.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }

    @Test
    public void test_FactoryPostProcessAndBeanPostProcessorWithXml() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_post.xml");

        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }

    @Test
    public void test_init_destroy() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        context.registerShutdownHook();
        UserService userService = context.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }

    @Test
    public void test_aware() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        context.registerShutdownHook();
        UserService userService = context.getBean("userService", UserService.class);
        userService.queryUserInfo();
        System.out.println("ApplicationContextAware:" + userService.getApplicationContext());
        System.out.println("BeanFactory:" + userService.getBeanFactory());
    }

    @Test
    public void test_factoryBean() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_factoryBean.xml");
        applicationContext.registerShutdownHook();

        top.yuan.test.FactoryBeanTest.UserService u1 = applicationContext.getBean("userService", top.yuan.test.FactoryBeanTest.UserService.class);
        top.yuan.test.FactoryBeanTest.UserService u2 = applicationContext.getBean("userService", top.yuan.test.FactoryBeanTest.UserService.class);

        System.out.println(u1);
        System.out.println(u2);

        System.out.println(u1 + " 十六进制哈希：" + Integer.toHexString(u1.hashCode()));
        System.out.println(u2 + " 十六进制哈希：" + Integer.toHexString(u2.hashCode()));
//        System.out.println(ClassLayout.);

    }
    @Test
    public void test_factory_bean() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_factoryBean.xml");
        applicationContext.registerShutdownHook();

        top.yuan.test.FactoryBeanTest.UserService userService = applicationContext.getBean("userService", top.yuan.test.FactoryBeanTest.UserService.class);
        userService.queryUserInfo();
    }

    @Test
    public void test_listener_publisher() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_listen.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 100212199212L, "\n广播的消息"));
        applicationContext.registerShutdownHook();
    }

    @Test
    public void test_proxy() {
        ITestInterface test = (ITestInterface) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[] {ITestInterface.class}, (proxy, method, args) -> "1⃣已被代理");
        System.out.println(test.show());
    }

    @Test
    public void test_aop() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* top.yuan.test.proxy.ITestInterfaceImpl.*(..))");

        Class<ITestInterfaceImpl> clazz = ITestInterfaceImpl.class;
        Method method = clazz.getDeclaredMethod("show");

        System.out.println(pointcut.matches(clazz));
        System.out.println(pointcut.matches(method, clazz));
    }

    @Test
    public void test_dynamic_proxy() {
        ITestInterface iTestInterface = new ITestInterfaceImpl();

        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(iTestInterface));
        advisedSupport.setMethodInterceptor(new ITestInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* top.yuan.test.proxy.ITestInterface.*(..))"));

        ITestInterface proxy_jdk = (ITestInterface) new JdkDynamicAopProxy(advisedSupport).getProxy();
        System.out.println("测试结果：" + proxy_jdk.show());

        ITestInterface proxy_cglib = (ITestInterface) new Cglib2AopProxy(advisedSupport).getProxy();
        System.out.println("测试结果：" + proxy_cglib.register("测试名"));
    }

    @Test
    public void test_proxy_method() {
         Object targetObject = new ITestInterfaceImpl();

         ITestInterface proxy =(ITestInterface) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), targetObject.getClass().getInterfaces(), new InvocationHandler() {

             MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* top.yuan.test.proxy.ITestInterface.*(..))");
             @Override
             public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                 if (methodMatcher.matches(method, targetObject.getClass())) {
                     MethodInterceptor methodInterceptor = invocation -> {
                         long start = System.currentTimeMillis();
                         try {
                             return invocation.proceed();
                         } finally {
                             System.out.println("监控 - Begin by AOP");
                             System.out.println("方法名： " + invocation.getMethod().getName());
                             System.out.println("方法耗时： " + (System.currentTimeMillis() - start) + "ms");
                             System.out.println("监控 - End \r\n");
                         }
                     };
                     return methodInterceptor.invoke(new ReflectiveMethodInvocation(targetObject, method, args));
                 }
                 return method.invoke(targetObject, args);
             }
         });

        String show = proxy.show();
        System.out.println("测试结果： " + show);
    }

}
