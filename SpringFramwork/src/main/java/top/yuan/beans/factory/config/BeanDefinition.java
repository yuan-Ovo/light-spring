package top.yuan.beans.factory.config;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 */
public class BeanDefinition {

    private Class beanClass;

    private String beanClassname;

//    private PropertyValue


    public BeanDefinition() {
    }

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
