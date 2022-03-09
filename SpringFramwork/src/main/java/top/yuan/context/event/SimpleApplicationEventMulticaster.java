package top.yuan.context.event;

import top.yuan.beans.factory.BeanFactory;
import top.yuan.context.ApplicationEvent;
import top.yuan.context.ApplicationListener;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster{

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
