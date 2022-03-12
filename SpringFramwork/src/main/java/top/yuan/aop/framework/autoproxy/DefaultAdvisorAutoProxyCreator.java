package top.yuan.aop.framework.autoproxy;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import top.yuan.aop.*;
import top.yuan.aop.aspectj.AspectJExpressionPointcutAdvisor;
import top.yuan.aop.framework.ProxyFactory;
import top.yuan.beans.BeansException;
import top.yuan.beans.PropertyValues;
import top.yuan.beans.factory.BeanFactory;
import top.yuan.beans.factory.BeanFactoryAware;
import top.yuan.beans.factory.config.InstantiationAwareBeanPostProcessor;
import top.yuan.beans.factory.support.DefaultListableBeanFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class DefaultAdvisorAutoProxyCreator implements BeanFactoryAware, InstantiationAwareBeanPostProcessor {

    private DefaultListableBeanFactory beanFactory;

    private final Set<Object> earlyProxyReferences = Collections.synchronizedSet(new HashSet<>());

    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass) || Advisor.class.isAssignableFrom(beanClass);
    }

    protected Object warpIfNecessary(Object bean, String beanName) {
        if (isInfrastructureClass(bean.getClass())) {
            return bean;
        }

        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(
                AspectJExpressionPointcutAdvisor.class).values();

        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            // 进行广播对象匹配
            if (!classFilter.matches(bean.getClass())) {
                continue;
            }
            AdvisedSupport advisedSupport = new AdvisedSupport();
            TargetSource targetSource = new TargetSource(bean);

            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(true);
            //如果匹配到Aware类型则返回代理对象
            return new ProxyFactory(advisedSupport).getProxy();
        }
        return bean;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!earlyProxyReferences.contains(beanName)) {
            return warpIfNecessary(bean, beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues propertyValues, Object bean, String beanName) throws BeansException {
        return propertyValues;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }

    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) {
        earlyProxyReferences.add(beanName);
        return warpIfNecessary(bean, beanName);
    }
}
