package top.yuan.beans.factory.support;

import top.yuan.beans.BeansException;
import top.yuan.beans.factory.DisposableBean;
import top.yuan.beans.factory.ObjectFactory;
import top.yuan.beans.factory.config.SingletonBeanRegistry;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * 初始化NULL_OBJECT对象作表示一个相当于null的单例对象
     * 因为ConcurrentHashMap不支持null最为value，因此初始化一个对象来表示null(相当于一个标记)
     */
    protected static final Object NULL_OBJECT = new Object();

    /**
     * 一级缓存，存储普通对象
     */
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();
    /**
     * 二级缓存，用于提前暴露对象，存储没有完全实例化的对象
     */
    protected final Map<String, Object> earlySingletonObjects = new ConcurrentHashMap<>();

    /**
     * 三级缓存，存放代理对象
     */
    private final Map<String, ObjectFactory<?>> singletonFactories = new ConcurrentHashMap<>();


    private final Map<String, DisposableBean> disposableBeanMap = new ConcurrentHashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        Object singletonObject = singletonObjects.get(beanName);
        if (null == singletonObject) {
            singletonObject = earlySingletonObjects.get(beanName);
            //判断二级缓存中是否存有对象，如果没有在三级缓存的代理对象中寻找
            if (null == singletonObject) {
                ObjectFactory<?> singletonFactory = singletonFactories.get(beanName);
                if (singletonFactory != null) {
                    singletonObject = singletonFactory.getObject();
                    //将三级缓存中的代理工厂中的代理对象取出，放入二级缓存当中,并把三级缓存中该实例的工厂移除
                    earlySingletonObjects.put(beanName, singletonObject);
                    singletonFactories.remove(beanName);
                }
            }
        }
        return singletonObject;
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
        earlySingletonObjects.remove(beanName);
        singletonFactories.remove(beanName);
    }

    protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory) {
        if (!this.singletonObjects.containsKey(beanName)) {
            this.singletonFactories.put(beanName, singletonFactory);
            this.earlySingletonObjects.remove(beanName);
        }
    }

    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeanMap.put(beanName, bean);
    }

    public void destroySingletons() {
        Set<String> keySet = this.disposableBeanMap.keySet();
        Object[] disposableBeanNames = keySet.toArray();

        for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeanMap.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("名为 '" + beanName + "' 的Bean在执行Destroy方法时抛出了一个异常", e);
            }
        }
    }

}
