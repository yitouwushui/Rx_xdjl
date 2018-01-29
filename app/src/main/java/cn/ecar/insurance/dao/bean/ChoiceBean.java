package cn.ecar.insurance.dao.bean;

import java.io.Serializable;

/**
 *
 * @author ding
 * @date 2016/10/11
 * 选择银行，选择会员单位等基类
 */
public class ChoiceBean extends BaseBean implements Serializable {
    /**
     * id
     */
    private String code;
    /**
     * 名字
     */
    private String name;

    public ChoiceBean() {
    }

    public ChoiceBean(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String id) {
        this.code = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
