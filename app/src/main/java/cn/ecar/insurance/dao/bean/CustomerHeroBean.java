package cn.ecar.insurance.dao.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ding on 2018/3/15.
 */

public class CustomerHeroBean {
    /**
     * customerCode : C18030500027
     * customerId : 26
     * customerPrizeRule : {"prizeId":1,"prizeName":"IPHONE X","ruleId":1,"sort":1,"sortName":"第一名","type":"3"}
     * identity : 1
     * phoneNo : 13818175906
     * registTime : 1520220574000
     * shareTimes : 2
     * sort : 1
     * status : 1
     * type : 2
     * activationDate : 1520939204000
     * endDate : 1552475204000
     * firstAgentId : 31
     * secondAgentId : 26
     * thirdAgentId : 26
     */

    private String customerCode;
    private int customerId;
    private CustomerPrizeRuleBean customerPrizeRule;
    private String identity;
    private String phoneNo;
    private long registTime;
    private int shareTimes;
    private int sort;
    @SerializedName("status")
    private String statusX;
    private String type;
    private long activationDate;
    private long endDate;
    private int firstAgentId;
    private int secondAgentId;
    private int thirdAgentId;

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public CustomerPrizeRuleBean getCustomerPrizeRule() {
        return customerPrizeRule;
    }

    public void setCustomerPrizeRule(CustomerPrizeRuleBean customerPrizeRule) {
        this.customerPrizeRule = customerPrizeRule;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public long getRegistTime() {
        return registTime;
    }

    public void setRegistTime(long registTime) {
        this.registTime = registTime;
    }

    public int getShareTimes() {
        return shareTimes;
    }

    public void setShareTimes(int shareTimes) {
        this.shareTimes = shareTimes;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getStatusX() {
        return statusX;
    }

    public void setStatusX(String statusX) {
        this.statusX = statusX;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(long activationDate) {
        this.activationDate = activationDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public int getFirstAgentId() {
        return firstAgentId;
    }

    public void setFirstAgentId(int firstAgentId) {
        this.firstAgentId = firstAgentId;
    }

    public int getSecondAgentId() {
        return secondAgentId;
    }

    public void setSecondAgentId(int secondAgentId) {
        this.secondAgentId = secondAgentId;
    }

    public int getThirdAgentId() {
        return thirdAgentId;
    }

    public void setThirdAgentId(int thirdAgentId) {
        this.thirdAgentId = thirdAgentId;
    }

    public static class CustomerPrizeRuleBean {
        /**
         * prizeId : 1
         * prizeName : IPHONE X
         * ruleId : 1
         * sort : 1
         * sortName : 第一名
         * type : 3
         */

        private int prizeId;
        private String prizeName;
        private int ruleId;
        private int sort;
        private String sortName;
        private String type;

        public int getPrizeId() {
            return prizeId;
        }

        public void setPrizeId(int prizeId) {
            this.prizeId = prizeId;
        }

        public String getPrizeName() {
            return prizeName;
        }

        public void setPrizeName(String prizeName) {
            this.prizeName = prizeName;
        }

        public int getRuleId() {
            return ruleId;
        }

        public void setRuleId(int ruleId) {
            this.ruleId = ruleId;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getSortName() {
            return sortName;
        }

        public void setSortName(String sortName) {
            this.sortName = sortName;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
