package top.yuan.aop;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \ 类过滤器：限制切点的匹配，给对应的类设置切点
 */
public interface ClassFilter {
    /**
     * 判断切点是否应该应用于给定的接口或目标类
     * @param clazz 待选取的目标类
     * @return 切点是否应该应用于该目标类
     */
    boolean matches(Class<?> clazz);
}
