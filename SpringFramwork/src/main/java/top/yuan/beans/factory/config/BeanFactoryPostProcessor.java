package top.yuan.beans.factory.config;

import top.yuan.beans.BeansException;
import top.yuan.beans.factory.ConfigurableListableBeanFactory;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有BeanDefinition加载之后，实例化Bean对象前，提供修改BeanDefinition属性的接口
     * @param beanFactory 需要修改的BeanFactory
     * @throws BeansException 通用Bean异常处理
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
