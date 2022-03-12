package top.yuan.test.annotationValue;

import top.yuan.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
@Component
public class UserDao {

    private static Map<String, String> map = new HashMap<>();

    static {
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");
    }

    public String queryUserName(String uid) {
        return map.get(uid);
    }
}
