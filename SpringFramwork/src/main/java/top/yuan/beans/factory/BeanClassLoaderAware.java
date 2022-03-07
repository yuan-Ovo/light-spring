package top.yuan.beans.factory;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public interface BeanClassLoaderAware extends Aware{
    /**
     * 设置Bean的类加载器
     * @param classLoader Bean的类加载器
     */
    void setBeanClassLoader(ClassLoader classLoader);
}
