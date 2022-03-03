package top.yuan.test;

import org.junit.jupiter.api.Test;
import top.yuan.beans.BeansException;
import top.yuan.beans.PropertyValue;
import top.yuan.beans.PropertyValues;
import top.yuan.beans.factory.config.BeanDefinition;
import top.yuan.beans.factory.config.BeanReference;
import top.yuan.beans.factory.support.DefaultListableBeanFactory;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class UserService {
    private int uid;

    private UserDao userDao;

    public void queryUserInfo() {
        System.out.println("查询用户信息: " + userDao.queryUserName(uid) + " " + this.hashCode());
    }

    public UserService() {}

    public UserService(int uid) {
        this.uid = uid;
    }

    public UserService(int uid, UserDao userDao) {
        this.uid = uid;
        this.userDao = userDao;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String toString() {
//        final StringBuilder sb = new StringBuilder("");
//        sb.append(name);
//        return sb.toString();
        return userDao.queryUserName(uid);
    }
}

class test {
    @Test
    public void test() throws BeansException {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uid", 2));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);

        beanFactory.registerBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
//
//        UserService userService = (UserService) beanFactory.getBean("userService", "阿源");
//        userService.queryUserInfo();
//
//        UserService userService_singleton = (UserService) beanFactory.getBean("userService", "new");
//        userService_singleton.queryUserInfo();
    }
}
