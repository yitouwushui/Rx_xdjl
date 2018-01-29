package cn.ecar.insurance.dao.gson;

import java.util.List;

import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.bean.Bank;
import cn.ecar.insurance.dao.bean.BranchBank;

/**
 * 银行卡gson
 *
 * @author ding
 * @date 2018/1/25
 */

public class BankGson extends BaseGson {

    public BankGson() {
    }

    /**
     * 支行数据
     */
    private List<BranchBank> bankNumberDtoList;

    /**
     * 银行分类数据
     */
    private List<Bank> bankcodeSmartpayDtoList;

    public List<Bank> getBankcodeSmartpayDtoList() {
        return bankcodeSmartpayDtoList;
    }

    public void setBankcodeSmartpayDtoList(List<Bank> bankcodeSmartpayDtoList) {
        this.bankcodeSmartpayDtoList = bankcodeSmartpayDtoList;
    }

    public List<BranchBank> getBankNumberDtoList() {
        return bankNumberDtoList;
    }

    public void setBankNumberDtoList(List<BranchBank> bankNumberDtoList) {
        this.bankNumberDtoList = bankNumberDtoList;
    }
}
