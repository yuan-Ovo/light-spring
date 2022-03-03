package top.yuan.beans.factory.support;

import top.yuan.beans.BeansException;
import top.yuan.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public interface InstantiationStrategy {

    /**
     * 实例化策略接口
     * @param beanDefinition bean定义
     * @param beanName bean名称
     * @param ctor 构造器
     * @param args bean的构造函数入参
     * @return 实例化对Bean对象
     * @throws BeansException 通用Bean运行时异常
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName,
                       Constructor ctor, Object... args) throws BeansException;
}
