package top.yuan.test.common;

import top.yuan.beans.BeansException;
import top.yuan.beans.PropertyValue;
import top.yuan.beans.PropertyValues;
import top.yuan.beans.factory.ConfigurableListableBeanFactory;
import top.yuan.beans.factory.config.BeanDefinition;
import top.yuan.beans.factory.config.BeanFactoryPostProcessor;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");

        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company", "阿里巴巴"));
    }
}
