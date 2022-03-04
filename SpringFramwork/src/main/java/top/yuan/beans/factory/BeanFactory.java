package top.yuan.beans.factory;

import top.yuan.beans.BeansException;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public interface BeanFactory {
    /**
     * 从BeanFactory中获取Bean对象
     * @param name 要获取的Bean的类名
     * @return 返回一个相应类型的实例
     * @throws BeansException Bean通用异常处理
     */
    Object getBean(String name) throws BeansException;

    Object getBean(String name, Object... args) throws BeansException;

    <T> T getBean(String beanClassName, Class<?> requiredType) throws BeansException;
}
