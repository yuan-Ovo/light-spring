package top.yuan.beans.factory;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \  连接扩展对象的接口
 */
public interface FactoryBean<T> {
    /**
     * 获取Bean对象
     * @return 一个Bean对象
     * @throws Exception 抛出所有异常
     */
    T getObject() throws Exception;

    /**
     * 获取Bean对象的Class类对象
     * @return Class类对象
     */
    Class<?> getObjectType();

    /**
     * 判断是否为单例模式
     * @return 返回true则是单例模式，返回false则为原型模式
     */
    boolean isSingleton();
}
