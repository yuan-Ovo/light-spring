package top.yuan.beans.factory.config;

import top.yuan.beans.BeansException;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{

    /**
     * 在Bean对象执行初始化之前，执行此方法
     * @param beanClass bean的类型
     * @param beanName bean的名称
     * @return 实例
     * @throws BeansException 通用Bean异常处理
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;
}
