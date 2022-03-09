package top.yuan.aop;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \ AOP的切点接口，切点由类过滤器{@link ClassFilter}和方法匹配器{@link MethodMatcher}组成
 */
public interface Pointcut {
    /**
     * 获取切点的类过滤器
     * @return 类过滤器
     */
    ClassFilter getClassFilter();

    /**
     * 获取切点的方法匹配器
     * @return 方法匹配器
     */
    MethodMatcher getMethodMatcher();
}
