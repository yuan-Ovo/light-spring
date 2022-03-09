package top.yuan.test.event;

import top.yuan.context.ApplicationListener;
import top.yuan.context.event.ContextClosedEvent;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件：" + this.getClass().getName());
    }
}
