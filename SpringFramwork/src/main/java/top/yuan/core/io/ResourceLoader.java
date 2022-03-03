package top.yuan.core.io;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    /**
     * 通过位置获取资源
     * @param location 资源地址/路径
     * @return Resource资源
     */
    Resource getResource(String location);
}
