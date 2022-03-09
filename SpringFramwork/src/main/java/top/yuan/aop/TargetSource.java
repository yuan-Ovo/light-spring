package top.yuan.aop;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \ 被代理的目标对象（谁调用的AOP），如果没有环绕通知结束拦截链自身，则通过反射调用该目标
 */
public class TargetSource {

    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    /**
     * 通过返回{@link TargetSource}的目标类型(接口),可以返回空值null
     * @return {@link TargetSource}的目标类型
     */
    public Class<?>[] getTargetClass() {
        return this.target.getClass().getInterfaces();
    }

    /**
     * 返回一个目标实例，在AOP框架调用AOP方法调用的目标之前立即调用
     * @return 包含连接点的目标对象
     */
    public Object getTarget() {
        return this.target;
    }
}
