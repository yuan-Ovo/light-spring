package top.yuan.beans.factory;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public interface BeanNameAware extends Aware{

    /**
     * 设置Bean的类名称
     * @param beanName bean的类名称
     */
    void setBeanName(String beanName);
}
