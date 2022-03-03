package top.yuan.beans.factory.support;

import top.yuan.beans.BeansException;
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

    /**
     * 通过Bean名获取BeanDefinition
     * @param beanName Bean名
     * @return 对应的BeanDefinition
     * @throws BeansException 通用Bean异常处理
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 通过Bean名查询是否存在对应的BeanDefinition
     * @param beanName Bean名
     * @return 是否存在对应的BeanDefinition
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 获取注册机中所有的beanDefinition对应的Bean名
     * @return 所有Bean名
     */
    String[] getBeanDefinitionNames();
}
