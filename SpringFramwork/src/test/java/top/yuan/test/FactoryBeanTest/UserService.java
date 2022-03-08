package top.yuan.test.FactoryBeanTest;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class UserService {

    private int uid;

    private IUserDao userDao;

    private String location;

    private String company;

    public void queryUserInfo() {
        System.out.println("查询用户信息: " + userDao.queryUserName(uid) + " " + company + location + " " + this.hashCode());
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
