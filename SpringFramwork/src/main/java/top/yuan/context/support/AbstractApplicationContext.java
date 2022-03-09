package top.yuan.context.support;

import top.yuan.beans.BeansException;
import top.yuan.beans.factory.ConfigurableListableBeanFactory;
import top.yuan.beans.factory.config.BeanFactoryPostProcessor;
import top.yuan.beans.factory.config.BeanPostProcessor;
import top.yuan.context.ApplicationEvent;
import top.yuan.context.ApplicationListener;
import top.yuan.context.ConfigurableApplicationContext;
import top.yuan.context.event.ApplicationEventMulticaster;
import top.yuan.context.event.ContextClosedEvent;
import top.yuan.context.event.ContextRefreshedEvent;
import top.yuan.context.event.SimpleApplicationEventMulticaster;
import top.yuan.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    @Override
    public void refresh() throws BeansException {
        //1. 创建BeanFactory并加载BeanDefinition
        refreshBeanFactory();
        //2. 获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        //3. 添加ApplicationContextAwareProcessor，使得继承自ApplicationContextAware的Bean对象能够感知
        //所属的Application
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
        //4. 在Bean实例之前，执行BeanFactoryPostProcessor 执行上下文中的后处理方法
        invokeBeanFactoryPostProcessors(beanFactory);
        //5. BeanPostProcessor提前于其他Bean对象实例化之前执行注册
        registerBeanPostProcessors(beanFactory);
        //6. 初始化事件发布者
        initApplicationEventMulticaster();
        //7. 注册事件监听器
        registerListener();
        //8. 提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();
        //9. 发布容器刷新完成事件
        finishRefresh();
    }

    /**
     * 创建BeanFactory并加载BeanDefinition
     * @throws BeansException 通用Bean异常处理
     */
    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);

        for (BeanFactoryPostProcessor factoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            factoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor postProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(postProcessor);
        }
    }

    private void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    private void registerListener() {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener listener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(listener);
        }
    }

    private void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String beanClassName, Class<?> requiredType) throws BeansException {
        return getBeanFactory().getBean(beanClassName, requiredType);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        //发布容器关闭事件
        publishEvent(new ContextClosedEvent(this));
        //执行销毁单例bean的销毁方法
        getBeanFactory().destroySingletons();
    }
}
