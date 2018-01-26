package cn.ecar.insurance.dao.gson;

import java.util.List;

import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.bean.Bank;

/**
 * 银行卡gson
 *
 * @author ding
 * @date 2018/1/25
 */

public class BankGson extends BaseGson {

    private List<Bank> bankNumberDtoList;

    public List<Bank> getBankNumberDtoList() {
        return bankNumberDtoList;
    }

    public void setBankNumberDtoList(List<Bank> bankNumberDtoList) {
        this.bankNumberDtoList = bankNumberDtoList;
    }
}
