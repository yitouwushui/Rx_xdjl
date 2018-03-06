package cn.ecar.insurance.dao.bean;

/**
 *
 * @author ding
 * @date 2018/3/6
 */

public class InsuranceOrderType extends BaseBean {

    /**
     * name : 机动车损失保险
     * orderNo : IO00000069
     * status : 1
     */
    private String name;
    private String orderNo;
    private String status;
    private String amount;

    public InsuranceOrderType() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
