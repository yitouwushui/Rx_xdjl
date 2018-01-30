package cn.ecar.insurance.dao.gson;

import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.bean.BankBind;

/**
 * @author ding
 * @date 2018/1/25
 */

public class BankBindGson extends BaseGson {

    /**
     * bankBindDto : {"bankCardNo":"6226021001010501","bankId":3,"bindId":1,"branchName":"外滩支行","branchNo":"20008812","certificateCode":"413026198709094848","customerId":4,"name":"曹强","payType":"1","status":"1","type":"1"}
     */
    private BankBind bankBindDto;
    /**
     * cash : 20
     */
    private String cash;

    public BankBind getBankBindDto() {
        return bankBindDto;
    }

    public void setBankBindDto(BankBind bankBindDto) {
        this.bankBindDto = bankBindDto;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }
}
