package top.yuan.test.circular;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class Wife {
    private Husband husband;

    private IMother mother;

    public String queryHusband() {
        return  "Wife.husband, Mother.callMother" + mother.callMother();
    }

    public Husband getHusband() {
        return husband;
    }

    public void setHusband(Husband husband) {
        this.husband = husband;
    }

    public IMother getMother() {
        return mother;
    }

    public void setMother(IMother mother) {
        this.mother = mother;
    }
}
