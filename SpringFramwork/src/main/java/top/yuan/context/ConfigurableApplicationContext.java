package top.yuan.context;

import top.yuan.beans.BeansException;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public interface ConfigurableApplicationContext extends ApplicationContext{
    /**
     * 刷新容器
     * @throws BeansException 通用Bean异常处理
     */
    void refresh() throws BeansException;

    /**
     * 注册shutdown时的钩子方法
     */
    void registerShutdownHook();

    /**
     * 关闭上下文应用
     */
    void close();
}
