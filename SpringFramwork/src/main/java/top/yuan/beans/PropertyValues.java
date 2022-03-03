package top.yuan.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * \* Create by Yuan
 * \* @author: Yuan
 * \
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue pv) {
        this.propertyValueList.add(pv);
    }

    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue py : propertyValueList) {
            if (py.getName().equals(propertyName)) {
                return py;
            }
        }
        return null;
    }
}
