package top.yuan.test;

import org.junit.jupiter.api.Test;
import top.yuan.beans.BeansException;
import top.yuan.beans.factory.config.BeanDefinition;
import top.yuan.beans.factory.support.DefaultListableBeanFactory;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class UserService {
    private String name;

    public void queryUserInfo() {
        System.out.println("查询用户信息: " + name + " " + this.hashCode());
    }

    public UserService(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
//        final StringBuilder sb = new StringBuilder("");
//        sb.append(name);
//        return sb.toString();
        return name;
    }
}

class test {
    @Test
    public void test() throws BeansException {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService", "阿源");
        userService.queryUserInfo();

        UserService userService_singleton = (UserService) beanFactory.getBean("userService", "new");
        userService_singleton.queryUserInfo();
    }
}
