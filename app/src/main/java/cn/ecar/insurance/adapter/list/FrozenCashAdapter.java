package cn.ecar.insurance.adapter.list;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.abslistview.CommonAdapter;
import cn.ecar.insurance.adapter.abslistview.ViewHolder;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.FrozenCash;
import cn.ecar.insurance.dao.bean.OrderBean;
import cn.ecar.insurance.utils.ui.TimeUtils;

/**
 * @author ding
 * @date 2017/12/20
 */
public class FrozenCashAdapter extends CommonAdapter<FrozenCash> {

    public FrozenCashAdapter(Context context, int layoutId, List<FrozenCash> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, FrozenCash frozenCash, int position) {
        holder.setText(R.id.tv_order_no, frozenCash.getCustomerCode() == null ? "" : frozenCash.getCustomerCode());
        String status = frozenCash.getStatus();
        if (status != null && status.equals("2")) {
            status = "已支付";
        } else {
            status = "冻结";
        }
        holder.setText(R.id.tv_name, status);
        holder.setText(R.id.tv_amount, String.valueOf(frozenCash.getAmount()));
        holder.setText(R.id.tv_date, TimeUtils.getStringByDate(frozenCash.getCreateTime()));
    }

}

