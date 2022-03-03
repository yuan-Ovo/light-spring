package top.yuan.Utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ArrayUtil;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class BeanUtils {

    public static void setFieldValue(Object bean, String fieldNameOrIndex, Object value) {
        if (bean instanceof Map) {
            ((Map) bean).put(fieldNameOrIndex, value);
        } else if (bean instanceof List) {
            CollUtil.setOrAppend((List) bean, Convert.toInt(fieldNameOrIndex), value);
        } else if (bean instanceof Array) {
            ArrayUtil.setOrAppend(bean, Convert.toInt(fieldNameOrIndex), value);
        } else {
            //利用反射在bean对象中注入value的值
            MyReflectUtils.setFieldValue(bean, fieldNameOrIndex, value);
        }
    }
}
