package top.yuan.test.bean;

import top.yuan.beans.factory.FactoryBean;
import top.yuan.test.FactoryBeanTest.IUserDao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class ProxyBeanFactory implements FactoryBean<IUserDao> {
    @Override
    public IUserDao getObject() throws Exception {
        InvocationHandler handler = (proxy, method, args) -> {

            Map<Integer, String> map = new HashMap<>();
            map.put(1, "a");
            map.put(2, "b");
            map.put(3, "c");
            return "你被代理来 " + method.getName() + ": " + map.get(args[0]);
        };
        return (IUserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserDao.class}, handler);
    }

    @Override
    public Class<?> getObjectType() {
        return IUserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
