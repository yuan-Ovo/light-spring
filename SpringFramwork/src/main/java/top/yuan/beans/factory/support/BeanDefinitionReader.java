package top.yuan.beans.factory.support;

import top.yuan.beans.BeansException;
import top.yuan.core.io.Resource;
import top.yuan.core.io.ResourceLoader;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public interface BeanDefinitionReader {
    /**
     * 获取Bean定义注册机
     * @return Bean注册机
     */
    BeanDefinitionRegistry getRegistry();

    /**
     * 获取资源读取器
     * @return 资源读取器
     */
    ResourceLoader getResourceLoader();

    /**
     * 通过Resource获取资源
     * @param resource 资源类 ClassPathResource/URL/FileSystem
     * @throws BeansException 通用Bean异常
     */
    void loadBeanDefinitions(Resource resource) throws BeansException;

    /**
     * 通过Resource获取资源
     * @param resources 资源类数组，可以直接输入多个 ClassPathResource/URL/FileSystem
     * @throws BeansException 通用Bean异常
     */
    void loadBeanDefinitions(Resource... resources) throws BeansException;

    /**
     * 通过资源地址location获取资源
     * @param location 资源地址
     * @throws BeansException 通用Bean异常
     */
    void loadBeanDefinitions(String location) throws BeansException;

    /**
     * 通过资源地址locations获取资源
     * @param locations 资源地址
     * @throws BeansException 通用Bean异常
     */
    void loadBeanDefinitions(String... locations) throws BeansException;
}
