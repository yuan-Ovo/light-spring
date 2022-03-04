package top.yuan.beans.factory.support;

import top.yuan.beans.BeansException;
import top.yuan.beans.factory.BeanFactory;
import top.yuan.beans.factory.config.BeanDefinition;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    @Override
    public <T> T getBean(String beanClassName, Class<?> requiredType) throws BeansException {
        return (T) getBean(beanClassName);
    }

    protected  <T>T doGetBean(String name, final Object[] args) throws BeansException{
        Object bean = getSingleton(name);
        if (bean != null) {
            return (T) bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T) createBean(name, beanDefinition, args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;
}
