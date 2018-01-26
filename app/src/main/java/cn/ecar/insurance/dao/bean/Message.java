package cn.ecar.insurance.dao.bean;

import java.io.Serializable;

/**
 *
 * @author ding
 * @date 2018/1/23
 */

public class Message implements Serializable {

    /**
     * content : 客户176****9432刚刚已经注册啦！
     * createBy : SYSTEM
     * createTime : 1516698920000
     * isShow : 1
     * messageId : 1
     * status : 1
     * type : 2
     */
    private String content;
    private String createBy;
    private long createTime;
    private String isShow;
    private int messageId;
    private String status;
    private String type;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
