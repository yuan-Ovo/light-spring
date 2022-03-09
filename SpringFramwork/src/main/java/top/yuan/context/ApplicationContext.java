package top.yuan.context;

import top.yuan.beans.factory.HierarchicalBeanFactory;
import top.yuan.beans.factory.ListableBeanFactory;
import top.yuan.core.io.ResourceLoader;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {

}
