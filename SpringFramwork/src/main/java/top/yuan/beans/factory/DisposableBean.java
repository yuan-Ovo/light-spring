package top.yuan.beans.factory;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \ 实现此接口的BeanFactory在Bean对象销毁时能够执行destroy方法
 */
public interface DisposableBean {
    /**
     * 在Bean对象销毁时执行的销毁方法
     * @throws Exception
     */
    void destroy() throws Exception;
}
