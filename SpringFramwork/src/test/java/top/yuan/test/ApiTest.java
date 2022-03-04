package top.yuan.test;

import cn.hutool.core.io.IoUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import top.yuan.beans.factory.support.DefaultListableBeanFactory;
import top.yuan.beans.factory.xml.XmlBeanDefinitionReader;
import top.yuan.context.support.ClassPathXmlApplicationContext;
import top.yuan.core.io.DefaultResourceLoader;
import top.yuan.core.io.Resource;
import top.yuan.test.common.MyBeanFactoryPostProcessor;
import top.yuan.test.common.MyBeanPostProcessor;

import java.io.IOException;
import java.io.InputStream;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class ApiTest {

    private DefaultResourceLoader resourceLoader;

    @BeforeEach
    public void init() {
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void test_classpath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String read = IoUtil.readUtf8(inputStream);
        System.out.println(read);
    }

    @Test
    public void test_file() throws IOException {
        Resource resource = resourceLoader.getResource("src/test/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println("fileLoader: " + content);
    }

    @Test
    public void test_url() throws IOException {
        Resource resource = resourceLoader.getResource("https://github.com/jakeybroser/light-spring/blob/master/pom.xml");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println("url: " + content);
    }

    @Test
    public void test_xml() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        UserService userService = factory.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }

    @Test
    public void test_FactoryPostProcessAndBeanPostProcessor() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        MyBeanFactoryPostProcessor factoryPostProcessor = new MyBeanFactoryPostProcessor();
        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();

        factoryPostProcessor.postProcessBeanFactory(beanFactory);
        beanFactory.addBeanPostProcessor(beanPostProcessor);

        UserService userService = beanFactory.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }

    @Test
    public void test_FactoryPostProcessAndBeanPostProcessorWithXml() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_post.xml");

        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }


}
