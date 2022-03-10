package top.yuan.aop;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \  切点通知器
 */
public interface PointcutAdvisor extends Advisor{
    /**
     * 获得切点
     * @return 切点
     */
    Pointcut getPointcut();
}
