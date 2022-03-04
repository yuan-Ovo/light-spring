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
}
