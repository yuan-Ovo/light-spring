package top.yuan.beans.factory.config;

import top.yuan.beans.BeansException;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \  用于修改实例化Bean对象的扩展点
 */
public interface BeanPostProcessor {
    /**
     * 在Bean对象初始化后进行后处理
     * @param bean 需要处理的bean对象
     * @param beanName Bean对象类名
     * @return 处理后的bean对象
     * @throws BeansException 通用Bean异常处理
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 在Bean对象初始化前进行后处理
     * @param bean 需要处理的bean对象
     * @param beanName Bean对象类名
     * @return 处理后的bean对象
     * @throws BeansException 通用Bean异常处理
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
