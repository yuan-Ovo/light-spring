package top.yuan.beans.factory;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \ 实现了此接口的Bean对象，在BeanFactory设置属性后进行相应的处理
 *   例如：执行自定义初始化、检查是否设置了全部的强制属性
 */
public interface InitializingBean {
    /**
     * Bean 处理属性填充后的调用——>初始化方法
     * @throws Exception 抛出所有异常
     */
    void afterPropertiesSet() throws Exception;
}
