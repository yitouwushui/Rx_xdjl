package cn.ecar.insurance.adapter.abslistview;

import android.content.Context;

import java.util.List;

import cn.ecar.insurance.adapter.abslistview.base.ItemViewDelegate;

/**
 * @author ding
 */
public abstract class CommonAdapter<T> extends MultiItemTypeAdapter<T> {

    public CommonAdapter(Context context, final int layoutId, List<T> datas) {
        super(context, datas);

        addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position) {
                CommonAdapter.this.convert(holder, t, position);
            }
        });
    }

    @Override
    protected abstract void convert(ViewHolder viewHolder, T item, int position);

}
