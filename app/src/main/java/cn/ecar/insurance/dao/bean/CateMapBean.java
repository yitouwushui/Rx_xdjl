package cn.ecar.insurance.dao.bean;

import java.io.Serializable;

/**
 * @author ding
 * @date 2018/2/1
 */

public class CateMapBean extends BaseBean implements Serializable {


    /**
     * cateId : 6
     * name : 车上乘客责任险
     * shortName : ChengKe
     * sort : 6
     */


    private int cateId;
    private String name;
    private String shortName;
    private int sort;

    /**
     * cateValue : 0
     * valueId : 24
     */
    private String cateValue;
    private int valueId;

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getCateValue() {
        return cateValue;
    }

    public void setCateValue(String cateValue) {
        this.cateValue = cateValue;
    }

    public int getValueId() {
        return valueId;
    }

    public void setValueId(int valueId) {
        this.valueId = valueId;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
