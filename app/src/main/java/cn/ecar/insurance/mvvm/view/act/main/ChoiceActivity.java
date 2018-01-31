package cn.ecar.insurance.mvvm.view.act.main;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.abslistview.CommonAdapter;
import cn.ecar.insurance.adapter.abslistview.ViewHolder;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.databinding.ActivityListViewBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.utils.ui.IntentUtils;

/**
 * 选择银行，会员单位等
 *
 * @author ding
 */
public class ChoiceActivity extends BaseBindingActivity<ActivityListViewBinding> {

    ArrayList<?> dataList;
    Class<?> cls;
    int result;

    @Override
    public void getBundleExtras(Bundle extras) {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Bundle bundle1 = bundle.getBundle(XdConfig.EXTRA_BUNDLE);
            cls = (Class<?>) bundle1.get(XdConfig.EXTRA_CLASS_VALUE);
            dataList = bundle.getParcelableArrayList(XdConfig.EXTRA_ARRAY_VALUE);
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_list_view;
    }

    @Override
    protected void initView() {
        mVB.includeToolbar.textTitle.setText("请选择");
        mVB.listView.setAdapter(new ChoiceAdapter<>(mContext, R.layout.item_select_listview_tv, dataList));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        mVB.listView.setOnItemClickListener((parent, view, position, id) -> {
            //
            new IntentUtils.Builder(mContext)
                    .setParcelableExtra(XdConfig.EXTRA_VALUE, (Parcelable) dataList.get(position))
                    .setIntExtra(XdConfig.EXTRA_INT_VALUE, position)
                    .build()
                    .setResultOkWithFinishUi();
        });
    }

    @Override
    protected void destroyView() {

    }

    class ChoiceAdapter<T> extends CommonAdapter<T> {


        public ChoiceAdapter(Context context, int layoutId, List<T> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder viewHolder, T item, int position) {
            try {
                Method getFuc = cls.getMethod("getSelectContent");
                //取得方法后即可通过invoke方法调用，传入被调用方法所在类的对象和实参,对象可以通过cls.newInstance取得
                viewHolder.setText(R.id.tv_content, getFuc.invoke(item).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }


//            try {
//                Logger.i(cls != null ? cls.getName() : "c is null");
//                Logger.i(dataList != null ? "大小"+ dataList.size() : "dataList is null");
//                for (int i = 0; i < dataList.size(); i++) {
//                    Object obj = dataList.get(i);
//                    Method getFuc = cls.getMethod("getSelectCode");
//                    Method setFuc = cls.getMethod("setBankName",String.class);
//                    Method toStringFunc = cls.getMethod("toString");
//                    //取得方法后即可通过invoke方法调用，传入被调用方法所在类的对象和实参,对象可以通过cls.newInstance取得
//                    setFuc.invoke(obj, "反射修改");
//                    Logger.i(getFuc.invoke(obj).toString());
//                    Logger.i(toStringFunc.invoke(obj).toString());
//                    Logger.i("-------");
//                }
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            }
}
