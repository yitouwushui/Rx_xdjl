package cn.ecar.insurance.adapter.list;

import android.content.Context;

import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.abslistview.CommonAdapter;
import cn.ecar.insurance.adapter.abslistview.ViewHolder;
import cn.ecar.insurance.dao.bean.CategoryBean;

/**
 *
 * @author ding
 * @date 2018/2/28
 */

public class SelectCateAdapter extends CommonAdapter<CategoryBean.CategorySecond> {

    public SelectCateAdapter(Context context,  List<CategoryBean.CategorySecond> datas) {
        super(context, R.layout.item_select_listview_tv, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, CategoryBean.CategorySecond item, int position) {
        viewHolder.setText(R.id.tv_content, item.getName());
    }
}
