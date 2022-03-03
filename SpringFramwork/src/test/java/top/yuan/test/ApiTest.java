package top.yuan.test;

import cn.hutool.core.io.IoUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import top.yuan.core.io.DefaultResourceLoader;
import top.yuan.core.io.Resource;

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
}