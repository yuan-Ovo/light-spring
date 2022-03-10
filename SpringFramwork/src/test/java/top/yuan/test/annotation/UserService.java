package top.yuan.test.annotation;

import top.yuan.stereotype.Component;
import java.util.Random;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
@Component("userService")
public class UserService implements IUserService {

    private String token;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserService#token = { " + token + " }";
    }
}
