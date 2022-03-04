package top.yuan.beans.factory.config;

import top.yuan.beans.factory.HierarchicalBeanFactory;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    /**
     * 添加后处理方法
     * @param beanPostProcessor bean的后处理操作
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
