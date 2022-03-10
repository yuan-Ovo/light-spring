package top.yuan.aop.framework;

import top.yuan.aop.AdvisedSupport;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \  AOP代理工厂类，此类提供了一种在代码中获取和配置AOP代理的简单方法
 */
public class ProxyFactory {

    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy() {
        return createProxy().getProxy();
    }

    private AopProxy createProxy() {
        if (advisedSupport.isProxyTargetClass()) {
            return new Cglib2AopProxy(advisedSupport);
        }
        return new JdkDynamicAopProxy(advisedSupport);
    }
}
