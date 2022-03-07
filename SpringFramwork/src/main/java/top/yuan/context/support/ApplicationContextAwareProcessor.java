package top.yuan.context.support;

import top.yuan.beans.BeansException;
import top.yuan.beans.factory.config.BeanPostProcessor;
import top.yuan.context.ApplicationContext;
import top.yuan.context.ApplicationContextAware;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
