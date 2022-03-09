package top.yuan.context.event;

import top.yuan.context.ApplicationContext;
import top.yuan.context.ApplicationEvent;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class ApplicationContextEvent extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
