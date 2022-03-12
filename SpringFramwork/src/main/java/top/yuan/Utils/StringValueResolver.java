package top.yuan.Utils;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public interface StringValueResolver {

    /**
     * 用于解析字符串值的方法接口
     * @param strVal 通过注解读入的字符串值（可能是占位符）
     * @return 解析后的属性值
     */
    String resolveStringValue(String strVal);
}
