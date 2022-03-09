package top.yuan.test.event;

import top.yuan.context.ApplicationListener;
import top.yuan.context.event.ContextRefreshedEvent;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("刷新事件：" + this.getClass().getName());
    }
}
