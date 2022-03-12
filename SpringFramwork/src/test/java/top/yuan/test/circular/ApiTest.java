package top.yuan.test.circular;

import org.junit.jupiter.api.Test;
import top.yuan.context.support.ClassPathXmlApplicationContext;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class ApiTest {

    @Test
    public void test_circular() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_circular.xml");
        Husband husband = applicationContext.getBean("husband", Husband.class);
        Wife wife = applicationContext.getBean("wife", Wife.class);
        System.out.println("老公的媳妇：" + husband.queryWife());
        System.out.println("媳妇的老公：" + wife.queryHusband());
    }
}
