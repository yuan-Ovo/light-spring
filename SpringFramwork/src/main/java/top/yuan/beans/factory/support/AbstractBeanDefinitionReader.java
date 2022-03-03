package top.yuan.beans.factory.support;

import top.yuan.core.io.DefaultResourceLoader;
import top.yuan.core.io.ResourceLoader;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{

    private final BeanDefinitionRegistry registry;

    private ResourceLoader resourceLoader;

    /**
     * 抽象Bean定义读取器构造方法，会包含在子类构造方法当中，资源加载器不能为空
     * @param registry  注册机
     */
    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
