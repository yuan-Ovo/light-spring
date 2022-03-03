package top.yuan.beans.factory.config;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public interface SingletonBeanRegistry {
    /**
     * 获取单例对象的接口
     * @param beanName 要获取类的类名
     * @return 返回所需的类实例
     */
    Object getSingleton(String beanName);
}
