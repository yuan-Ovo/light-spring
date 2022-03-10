package top.yuan.aop.aspectj;

import org.aopalliance.aop.Advice;
import top.yuan.aop.Pointcut;
import top.yuan.aop.PointcutAdvisor;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \  AspectJ表达式切点通知器
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {
    /**
     * AspectJ表达式切点匹配器
     * AspectJ表达式匹配的切点
     */
    private AspectJExpressionPointcut pointcut;
    /**
     * 方法拦截器
     * 需要用户在xml文件中配置方法拦截器（MethodInterceptor继承Advice接口）
     * 在AspectJAwareAdvisorAutoProxyCreator设置代理对象的方法拦截器时将Advisor强转为MethodInterceptor
     */
    private Advice advice;
    /**
     * 切入切面的表达式
     */
    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public Pointcut getPointcut() {
        if (null == pointcut) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }
}
