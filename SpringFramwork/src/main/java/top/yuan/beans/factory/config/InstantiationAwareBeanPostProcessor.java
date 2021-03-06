package top.yuan.beans.factory.config;

import top.yuan.beans.BeansException;
import top.yuan.beans.PropertyValues;

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

    /**
     * 在Bean对象实例化后，设置属性操作之前执行此方法加入需要设置的属性值
     * @param propertyValues 需要设置的属性
     * @param bean 实例化的bean对象
     * @param beanName bean名称
     * @return 设置之后的属性值
     * @throws BeansException 通用Bean异常处理
     */
    PropertyValues postProcessPropertyValues(PropertyValues propertyValues, Object bean, String beanName) throws BeansException;

    /**\
     * 在工厂方法实例化Bean之后，填充属性之前执行（自动装配、属性注入），为bean实例执行后处理
     * @param bean 需要进行后处理的bean对象
     * @param beanName bean名称
     * @return
     * @throws BeansException 通用Bean异常处理
     */
    boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException;

    /**
     * 得到二级缓存中的Bean引用
     * @param bean
     * @param beanName
     * @return
     */
    default Object getEarlyBeanReference(Object bean, String beanName) {
        return bean;
    }
}
