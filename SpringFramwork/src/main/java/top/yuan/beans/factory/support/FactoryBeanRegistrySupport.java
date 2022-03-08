package top.yuan.beans.factory.support;

import top.yuan.beans.BeansException;
import top.yuan.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry{

    /**
     * 用于缓存 FactoryBeans 创建的单例对象
     */
    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();

    protected Object getCachedObjectFactoryBean(String beanName) {
        Object object = this.factoryBeanObjectCache.get(beanName);
        return  (object != NULL_OBJECT ? object : null);
    }

    protected Object getObjectFromFactoryBean(FactoryBean factoryBean, String beanName) {
        if (factoryBean.isSingleton()) {
            Object object = this.factoryBeanObjectCache.get(beanName);
            if (object == null) {
                object = doGetObjectFromFactoryBean(factoryBean, beanName);
                this.factoryBeanObjectCache.put(beanName, (object != null ? object : NULL_OBJECT));
            }
            return (object != NULL_OBJECT ? object : null);
        } else {
            return doGetObjectFromFactoryBean(factoryBean, beanName);
        }
    }

    private Object doGetObjectFromFactoryBean(final FactoryBean factoryBean, final String beanName) {
        try {
            return factoryBean.getObject();
        } catch (Exception e) {
            throw new BeansException("FactoryBean在创建'" + beanName + "'对象时发生异常", e);
        }
    }
}
