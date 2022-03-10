package top.yuan.context.annotation;

import cn.hutool.core.util.ClassUtil;
import top.yuan.beans.factory.config.BeanDefinition;
import top.yuan.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \  提供提供配置路径找到Bean对象的方法
 */
public class ClassPathScanningCandidateComponentProvider {

    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new HashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }
}
