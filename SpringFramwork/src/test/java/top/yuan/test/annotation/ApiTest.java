package top.yuan.test.annotation;

import org.junit.jupiter.api.Test;
import top.yuan.context.support.ClassPathXmlApplicationContext;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class ApiTest {

    @Test
    public void test_scan() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_scan.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println(userService.queryUserInfo());
    }

    @Test
    public void test_property() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_property.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println(userService);
    }
}
