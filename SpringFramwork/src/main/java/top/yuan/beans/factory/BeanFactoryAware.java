package top.yuan.beans.factory;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \  实现此接口的子类可以感应到所属的BeanFactory
 */
public interface BeanFactoryAware extends Aware{

    /**
     * 设置Bean所属的BeanFactory
     * @param beanFactory bean所属的beanFactory
     */
    void setBeanFactory(BeanFactory beanFactory);
}
