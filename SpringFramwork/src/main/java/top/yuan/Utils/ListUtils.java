package top.yuan.Utils;

import java.util.List;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class ListUtils {

    public static <T> List<T> setOrAppend(List<T> list, int index, T element) {
        if (index < list.size()) {
            list.set(index, element);
        } else {
            list.add(element);
        }
        return list;
    }
}
