package top.yuan.Utils;

import cn.hutool.core.annotation.Alias;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.SimpleCache;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ClassUtil;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.function.Supplier;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class MyReflectUtils {

    private static final SimpleCache<Class<?>, Field[]> FIELDS_CACHE = new SimpleCache<>();

    public static void setFieldValue(Object obj, String fieldName, Object value) {
        Assert.notNull(obj);
        Assert.notBlank(fieldName);

        final Field field = getField((obj instanceof Class) ? (Class<?>) obj : obj.getClass(), fieldName);
        Assert.notNull(field, "Field [{}] is not exit int [{}]", fieldName, obj.getClass().getName());
        setFieldValue(obj, field, value);
    }

    public static void setFieldValue(Object obj, Field field, Object value) {
        notNull(field, () -> new IllegalArgumentException("Filed is not exit"));

        final Class<?> fieldType = field.getType();
        if (null != value) {
            if (false == fieldType.isAssignableFrom(value.getClass())) {
                final Object targetValue = Convert.convert(fieldType, value);
                if (null != targetValue) {
                    value = targetValue;
                }
            }
        } else {
            value = ClassUtil.getDefaultValue(fieldType);
        }

        setAccessible(field);
        try {
            field.set(obj instanceof Class ? null : obj, value);
        } catch (IllegalAccessException e) {
            throw new UtilException(e, "IllegalAccess for {}.{}", obj, value);
        }
    }

    public static <T extends AccessibleObject> T setAccessible(T accessibleObject) {
        if (null != accessibleObject && false == accessibleObject.isAccessible()) {
            accessibleObject.setAccessible(true);
        }
        return accessibleObject;
    }

    public static Field getField(Class<?> beanClass, String name) throws SecurityException {
        final Field[] fields = getFields(beanClass);
        return ArrayUtil.firstMatch((field) -> name.equals(getFieldName(field)), fields);
    }

    public static Field[] getFields(Class<?> beanClass) {
        Field[] allFields = FIELDS_CACHE.get(beanClass);
        if (null != allFields) {
            return allFields;
        }

        allFields = getFieldsDirectly(beanClass, true);
        return FIELDS_CACHE.put(beanClass, allFields);
    }

    public static Field[] getFieldsDirectly(Class<?> beanClass, boolean withSuperClassFields) {
        Assert.notNull(beanClass);

        Field[] allFields = null;
        Class<?> searchType = beanClass;
        Field[] declaredFields;
        while (searchType != null) {
            declaredFields = searchType.getDeclaredFields();
            if(null == allFields) {
                allFields = declaredFields;
            } else {
                allFields = ArrayUtil.append(allFields, declaredFields);
            }
            searchType = withSuperClassFields ? searchType.getSuperclass() : null;
        }

        return allFields;
    }

    public static <T, X extends Throwable> T notNull(T object, Supplier<X> errorSupplier) throws X {
        if (null == object) {
            throw errorSupplier.get();
        }
        return object;
    }

    public static String getFieldName(Field field) {
        if (null == field) {
            return  null;
        }

        final Alias alias = field.getAnnotation(Alias.class);
        if (null != alias) {
            return alias.value();
        }

        return field.getName();
    }
}
