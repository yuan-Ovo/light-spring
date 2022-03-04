package top.yuan.beans.factory;

import top.yuan.beans.BeansException;
import top.yuan.beans.factory.config.AutowireCapableBeanFactory;
import top.yuan.beans.factory.config.BeanDefinition;
import top.yuan.beans.factory.config.ConfigurableBeanFactory;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;
}
