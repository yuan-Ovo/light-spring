package top.yuan.test.aopAll;

import org.junit.jupiter.api.Test;
import top.yuan.context.support.ClassPathXmlApplicationContext;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class ApiTest {

    @Test
    public void test_aop_all() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_aopAll.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println(userService.queryUserInfo());
    }
}
