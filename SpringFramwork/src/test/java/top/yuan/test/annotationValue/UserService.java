package top.yuan.test.annotationValue;

import top.yuan.beans.annotation.Autowired;
import top.yuan.beans.annotation.Value;
import top.yuan.stereotype.Component;

import java.util.Random;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
@Component("userService")
public class UserService implements IUserService{

    @Value("777777777")
    private String token;

    @Autowired
    private UserDao userDao;

    @Override
    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userDao.queryUserName("1") + "-----" + token;
    }

    @Override
    public String register(String userName) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册"  + userName + "success";
    }

    @Override
    public String toString() {
        return "USS#token = {" + token + "}";
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
