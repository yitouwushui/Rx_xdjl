package cn.ecar.insurance.dao.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author ding
 * @date 2018/2/1
 */

public class CategoryBean extends BaseBean implements Serializable {


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
    private List<CategorySecond> categoriesValuesDtoLit;

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

    public List<CategorySecond> getCategoriesValuesDtoLit() {
        return categoriesValuesDtoLit;
    }

    public void setCategoriesValuesDtoLit(List<CategorySecond> categoriesValuesDtoLit) {
        this.categoriesValuesDtoLit = categoriesValuesDtoLit;
    }

    public static class CategorySecond {

        /**
         * cateId : 1
         * cateValue : 0
         * name : 单商业
         * valueId : 1
         */
        private int cateId;
        private String cateValue;
        private String name;
        private int valueId;

        public int getCateId() {
            return cateId;
        }

        public void setCateId(int cateId) {
            this.cateId = cateId;
        }

        public String getCateValue() {
            return cateValue;
        }

        public void setCateValue(String cateValue) {
            this.cateValue = cateValue;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValueId() {
            return valueId;
        }

        public void setValueId(int valueId) {
            this.valueId = valueId;
        }
    }

}
