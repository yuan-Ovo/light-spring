package top.yuan.test.aop;

import java.util.Random;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class UserService implements IUserService{
    @Override
    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "查询业务";
    }

    @Override
    public String register(String name) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户: " + name + " 成功！";
    }
}
