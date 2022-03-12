package top.yuan.beans.factory.config;

import top.yuan.Utils.StringValueResolver;
import top.yuan.beans.factory.HierarchicalBeanFactory;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    /**
     * 添加后处理方法
     * @param beanPostProcessor bean的后处理操作
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例对象
     */
    void destroySingletons();

    /**
     * 为注入的属性值 添加字符串解析器
     * @param valueResolver 添加的字符串解析器
     */
    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    /**
     * 解析给定的嵌入值（注解属性）
     * @param value 需要解析的属性值
     * @return 解析后的值（可能还是原始值）
     */
    String resolveEmbeddedValue(String value);
}
