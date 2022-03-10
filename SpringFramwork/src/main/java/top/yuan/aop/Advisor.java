package top.yuan.aop;

import org.aopalliance.aop.Advice;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \  通知器 具体的方法拦截需要使用者比阿些，对应spring中的前置通知、后置通知、环切通知。。。
 */
public interface Advisor {
    /**
     * 获取该切面的通知器。
     * @return 通知器
     */
    Advice getAdvice();
}
