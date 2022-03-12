package top.yuan.test.circular;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class Husband {

    Wife wife;

    public String queryWife() {
        return "Husband.wife";
    }

    public Wife getWife() {
        return wife;
    }

    public void setWife(Wife wife) {
        this.wife = wife;
    }
}
