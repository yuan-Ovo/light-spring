package top.yuan.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import top.yuan.beans.BeansException;
import top.yuan.beans.factory.DisposableBean;
import top.yuan.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \  销毁方法可能有多个，目前实现接口DisposableBean和配置destroy-method两种方法
 *    这两种方法的销毁动作都由AbstractApplicationContext在虚拟机Hook后，虚拟机关闭前执行的操作动作
 *
 *    我们希望在销毁方法执行时有一个统一的接口进行销毁一次性处理所有的销毁方法，
 *    而不是为不同的实现方法创建各自的销毁方法，那样太过繁琐了
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;

    private final String beanName;

    private String destroyMethodName;

    private final String DESTROY_METHOD = "destroy";

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }

        if (StrUtil.isNotEmpty(destroyMethodName) &&
                !(bean instanceof DisposableBean && DESTROY_METHOD.equals(this.destroyMethodName))) {
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if (destroyMethod != null) {
                destroyMethod.invoke(bean);
            } else {
                throw new BeansException("无法在名 '" + beanName + "' bean中找到'" + destroyMethodName + "' destroy方法");
            }
        }
    }
}
