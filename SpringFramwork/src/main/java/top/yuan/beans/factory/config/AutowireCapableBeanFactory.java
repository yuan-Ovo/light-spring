package top.yuan.beans.factory.config;

import top.yuan.beans.BeansException;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public interface AutowireCapableBeanFactory {
    /**
     * 执行BeanPostProcessor 接口实现类的postProcessBeforeInitialization方法
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    /**
     * 执行BeanPostProcessor 接口实现类的postProcessAfterInitialization方法
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;
}
