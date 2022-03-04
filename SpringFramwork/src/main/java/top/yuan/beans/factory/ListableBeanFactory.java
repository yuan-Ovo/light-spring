package top.yuan.beans.factory;

import top.yuan.beans.BeansException;

import java.util.Map;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public interface ListableBeanFactory extends BeanFactory{

    /**
     * 按类型返回Bean实例
     * @param type 所需类型
     * @param <T> 实例Bean类型
     * @return map
     * @throws BeansException 通用Bean异常处理
     */
    <T> Map<String, T> getBeansOfType(Class<?> type) throws BeansException;

    /**
     * 获取所有注册的BeanDefinition名
     * @return Bean定义的名称
     */
    String[] getBeanDefinitionNames();
}
