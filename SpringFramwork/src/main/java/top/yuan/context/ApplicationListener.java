package top.yuan.context;

import java.util.EventListener;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {
    /**
     * 联通并响应一个应用事件
     * @param event 需要响应的事件
     */
    void onApplicationEvent(E event);
}
