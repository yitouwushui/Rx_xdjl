package cn.ecar.insurance.adapter.list;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.abslistview.CommonAdapter;
import cn.ecar.insurance.adapter.abslistview.ViewHolder;
import cn.ecar.insurance.dao.bean.FrozenCash;
import cn.ecar.insurance.dao.bean.FundsFlow;
import cn.ecar.insurance.dao.gson.FundsFlowGson;
import cn.ecar.insurance.utils.ui.TimeUtils;

/**
 * @author ding
 * @date 2017/12/20
 */
public class FundsAdapter extends CommonAdapter<FundsFlow> {


    public FundsAdapter(Context context, int layoutId, List<FundsFlow> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, FundsFlow fundsFlow, int position) {
        holder.setText(R.id.tv_order_no, String.valueOf(fundsFlow.getFundioOrderSn()));
        String status = fundsFlow.getStatus();
        if (status != null && !status.equals("")) {
            // 0：初始状态 1：渠道待支付/提现 2:账户待支付/提现 3：渠道支付/提现失败 4：账户支付/提现失败 8：成功状态 9：提现撤销
            switch (status) {
                case "0":
                    status = "初始状态";
                    break;
                case "1":
                    status = "渠道待支付/提现";
                    break;
                case "2":
                    status = "账户待支付/提现 ";
                    break;
                case "4":
                    status = "渠道支付/提现失败";
                    break;
                case "8":
                    status = "成功";
                    break;
                case "9":
                    status = "提现撤销";
                    break;
            }
        }
        //ioflag: 1:购买会员  2:提现 3:提现撤销 4:后台出金 5:购买保险
        String ioflag = fundsFlow.getIoflag();
        if (ioflag != null && !status.equals("")) {
            switch (ioflag) {
                case "1":
                    ioflag = "购买会员";
                    break;
                case "2":
                    ioflag = "提现";
                    break;
                case "3":
                    ioflag = "提现撤销";
                    break;
                case "4":
                    ioflag = "后台出金";
                    break;
                case "5":
                    ioflag = "购买保险";
                    break;
            }
        }
        holder.setText(R.id.tv_name, status);
        holder.setText(R.id.tv_yongjin, ioflag);
        holder.setText(R.id.tv_amount, String.valueOf(fundsFlow.getAmount()));
        holder.setText(R.id.tv_date, TimeUtils.getStringByDate(fundsFlow.getOrderTime()));
    }

}

