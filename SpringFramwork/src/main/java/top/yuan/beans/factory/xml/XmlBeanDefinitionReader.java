package top.yuan.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import top.yuan.beans.BeansException;
import top.yuan.beans.PropertyValue;
import top.yuan.beans.factory.config.BeanDefinition;
import top.yuan.beans.factory.config.BeanReference;
import top.yuan.beans.factory.support.AbstractBeanDefinitionReader;
import top.yuan.beans.factory.support.BeanDefinitionRegistry;
import top.yuan.core.io.Resource;
import top.yuan.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            try (InputStream is = resource.getInputStream()){
                doLoadBeanDefinitions(is);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new BeansException("无法从" + resource + "中读取xml文件", e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(String... locations) throws BeansException {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }

    /**
     * 解析XML文件内容的主要方法
     * @param inputStream xml文件的输入流
     * @throws ClassNotFoundException 找不到xml文件中提到的类
     */
    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        // TODO: 2022/1/26 自己实现XML的读取方法
        Document doc = XmlUtil.readXML(inputStream);
        Element root = doc.getDocumentElement();
        NodeList childNodes = root.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            if (!(childNodes.item(i) instanceof Element)) {
                continue;
            }

            if (!"bean".equals(childNodes.item(i).getNodeName())) {
                continue;
            }

            Element bean = (Element) childNodes.item(i);
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");
            String initMethod = bean.getAttribute("init-method");
            String destroyMethod = bean.getAttribute("destroy-method");
            String beanScope = bean.getAttribute("scope");

            Class<?> clazz = Class.forName(className);
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            //定义Bean
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            //在BeanDefinition中加入init-method和destroy-method属性
            beanDefinition.setInitMethodName(initMethod);
            beanDefinition.setDestroyMethodName(destroyMethod);
            //设置单例模式/多例模式
            if (StrUtil.isNotEmpty(beanName)) {
                beanDefinition.setScope(beanScope);
            }

            for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                if (!(bean.getChildNodes().item(j) instanceof Element)) {
                    continue;
                }
                if (!"property".equals(bean.getChildNodes().item(j).getNodeName())) {
                    continue;
                }

                //解析property标签
                Element property = (Element) bean.getChildNodes().item(j);
                String atterName = property.getAttribute("name");
                String atterValue = property.getAttribute("value");
                String atterRef = property.getAttribute("ref");

                //获取属性值：引入对象、值对象
                Object value = StrUtil.isNotEmpty(atterRef) ? new BeanReference(atterRef) : atterValue;
                PropertyValue propertyValue = new PropertyValue(atterName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("不允许出现重复的Bean名[" + beanName + "]");
            }
            //注册BeanDefinition
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }
}
