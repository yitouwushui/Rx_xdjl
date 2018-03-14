package cn.ecar.insurance.dao.bean;

import java.io.Serializable;

import cn.ecar.insurance.dao.base.BaseBean;

/**
 *
 * @author ding
 * @date 2018/1/23
 */

public class Information extends BaseBean implements Serializable {

    /**
     * categoriesId : 1
     * createBy : 1
     * createTime : 1516377600000
     * infoId : 1
     * publicTime : 1516377600000
     * sort : 1
     * source : 1
     * status : 1
     * title : 车行易保APP上线啦
     */

    private int categoriesId;
    private String createBy;
    private long createTime;
    private int infoId;
    private long publicTime;
    private int sort;
    private String source;
    private String status;
    private String title;

    public int getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(int categoriesId) {
        this.categoriesId = categoriesId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getInfoId() {
        return infoId;
    }

    public void setInfoId(int infoId) {
        this.infoId = infoId;
    }

    public long getPublicTime() {
        return publicTime;
    }

    public void setPublicTime(long publicTime) {
        this.publicTime = publicTime;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
