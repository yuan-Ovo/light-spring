package top.yuan.context.event;


import top.yuan.context.ApplicationEvent;
import top.yuan.context.ApplicationListener;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public interface ApplicationEventMulticaster {

    /**
     * 添加一个监听所有事件通知的监听器
     * @param listener 添加的监听器
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * 从notification通知列表移除一个监听器
     * @param listener 需要移除的监听器
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * 将给定都应用事件广播到适当的监听器
     * @param event 广播的事件
     */
    void multicastEvent(ApplicationEvent event);
}
