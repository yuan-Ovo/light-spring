package top.yuan.test;

import java.util.HashMap;
import java.util.Map;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class UserDao {

    private static Map<Integer, String> map = new HashMap<>();

    public void initDataMethod() {
        System.out.println("执行UserDao中定义的init-method：初始化map数据");
        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");
    }

    public void destroyDataMethod() {
        System.out.println("执行UserDao中定义的destroy-method：清空map数据");
        map.clear();
    }
//    static {
//        map.put(1, "a");
//        map.put(2, "b");
//        map.put(3, "c");
//    }

    public String queryUserName(int uid) {
        return map.get(uid);
    }
}
