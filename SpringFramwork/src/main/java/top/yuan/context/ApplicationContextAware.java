package top.yuan.context;

import top.yuan.beans.BeansException;
import top.yuan.beans.factory.Aware;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \  实现此接口的子类，能够感知到所属的ApplicationContext
 */
public interface ApplicationContextAware extends Aware {

    /**
     * 设置应用的上下文应用
     * @param applicationContext 上下文应用
     * @throws BeansException 通用Bean异常处理
     */
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
