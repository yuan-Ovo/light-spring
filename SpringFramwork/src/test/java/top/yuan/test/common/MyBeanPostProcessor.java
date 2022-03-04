package top.yuan.test.common;

import top.yuan.beans.BeansException;
import top.yuan.beans.factory.config.BeanPostProcessor;
import top.yuan.test.UserService;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setLocation("改为：上海");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)) {
            ((UserService) bean).setCompany("美团");
        }
        return bean;
    }
}
