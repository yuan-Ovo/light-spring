package top.yuan.test.annotationValue;

import org.junit.jupiter.api.Test;
import top.yuan.context.support.ClassPathXmlApplicationContext;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class ApiTest {

    @Test
    public void test_scan_value() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_value.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println(userService.queryUserInfo());
    }
}
