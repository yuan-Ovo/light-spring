package top.yuan.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \  AOP代理配置管理器的基类
 *    这些类自身不是AOP代理，但是此类的子类通常是工厂，从中能够直接获取AOP代理实例
 */
public class AdvisedSupport {
    /**
     *  被代理的目标对象
     */
    private TargetSource targetSource;
    /**
     * 方法拦截器
     */
    private MethodInterceptor methodInterceptor;
    /**
     * 方法匹配器(检查方法是否符合通知条件)
     */
    private MethodMatcher methodMatcher;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodMatcher getMethodMatcher() {
        return this.methodMatcher;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
