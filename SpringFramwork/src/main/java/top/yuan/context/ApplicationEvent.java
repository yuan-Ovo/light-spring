package top.yuan.context;

import java.util.EventObject;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public abstract class ApplicationEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
