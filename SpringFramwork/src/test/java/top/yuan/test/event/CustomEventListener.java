package top.yuan.test.event;

import top.yuan.context.ApplicationListener;

import java.util.Date;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        //处理一些的功能、操作、事件，例如给注册后的用户发送优惠券、短信通知等
        System.out.println("收到： " + event.getSource() + "消息；时间：" + new Date());
        System.out.println("消息: " + event.getId() + ":" + event.getMessage());
    }
}
