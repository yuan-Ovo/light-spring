package top.yuan.beans.factory.support;

import top.yuan.beans.BeansException;
import top.yuan.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object... args) throws BeansException {
        Class beanClazz = beanDefinition.getBeanClass();
        try {
            if (ctor != null) {
                //如果有显式定义构造器则调用定义的构造器
                return beanClazz.getConstructor(ctor.getParameterTypes()).newInstance(args);
            } else {
                //ctor == null 说明类没有显示定义构造器，则直接调用无参构造器
                return beanClazz.getDeclaredConstructor().newInstance();
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new BeansException("Failed to instantiate [" + beanClazz.getName() + "]", e);
        }
    }
}
