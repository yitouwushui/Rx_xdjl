package cn.ecar.insurance.dao.gson;

import cn.ecar.insurance.dao.base.BaseGson;

/**
 * Created by ding on 2018/1/30.
 */

public class BalanceGson extends BaseGson {


    /**
     * balance : 100.0
     */
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
