package top.yuan.core.io;

import top.yuan.Utils.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class ClassPathResource implements Resource{
    /**
     * 这里定义成name其实更合理，直接在ClassLoader找到的路径下读取文件
     */
    private final String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, (ClassLoader) null);
    }

    /**
     * 初始化资源的构造器，注意classLoader不能为空
     * @param path 资源路径
     * @param classLoader 类加载器，用于获取类，默认路径为classpath、类所在的包、ClassUtils所在的Utils包
     */
    public ClassPathResource(String path, ClassLoader classLoader) {
        this.path = path;
        this.classLoader = classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = classLoader.getResourceAsStream(path);
        if (null == is) {
            throw new FileNotFoundException(this.path + " 路径文件(ClassPath下)不存在，无法打开");
        }
        return is;
    }
}
