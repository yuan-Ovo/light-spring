package top.yuan.beans.factory;

import top.yuan.beans.BeansException;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \ 定义一个能够返回Object实例（可以是独占或者共享的）的工厂
 */
public interface ObjectFactory<T> {
    /**
     * 通过工厂返回一个Object实例
     * @return Object实例
     * @throws BeansException 通用Bean异常处理
     */
    T getObject() throws BeansException;
}
