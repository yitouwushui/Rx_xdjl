package cn.ecar.insurance.adapter.list;

import android.content.Context;

import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.abslistview.CommonAdapter;
import cn.ecar.insurance.adapter.abslistview.ViewHolder;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.InsuranceOrderType;
import cn.ecar.insurance.dao.bean.OrderBean;
import cn.ecar.insurance.mvvm.view.act.insurance.InsureActivity4;
import cn.ecar.insurance.mvvm.view.act.insurance.InsureActivity5;
import cn.ecar.insurance.utils.ui.IntentUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;

/**
 * @author ding
 * @date 2017/12/20
 */

public class InsuranceOrderListAdapter extends CommonAdapter<InsuranceOrderType> {


    public InsuranceOrderListAdapter(Context context, int layoutId, List<InsuranceOrderType> datas) {
        super(context, layoutId, datas);

    }

    @Override
    protected void convert(ViewHolder holder, InsuranceOrderType insuranceOrderType, int position) {
        holder.setText(R.id.tv_insureName, insuranceOrderType.getName());
        holder.setText(R.id.tv_amount, insuranceOrderType.getAmount());
    }

}

