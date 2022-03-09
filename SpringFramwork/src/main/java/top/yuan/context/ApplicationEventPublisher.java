package top.yuan.context;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public interface ApplicationEventPublisher {

    /**
     * 将应用事件发布给所有使用此应用注册的监听器，事件可以是框架事件也可是某特定事件
     * @param event 需要发布的事件
     */
    void publishEvent(ApplicationEvent event);
}
