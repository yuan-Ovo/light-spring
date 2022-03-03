package top.yuan.beans.factory.support;

import top.yuan.beans.factory.config.BeanDefinition;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public interface BeanDefinitionRegistry {

    /**
     * 注册BeanDefinition的接口
     * @param beanName Bean的名称
     * @param beanDefinition Bean的定义
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
