package cn.ecar.insurance.mvvm.view.act.insurance;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.Cheese;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.databinding.ActivityInsure1Binding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;
import cn.ecar.insurance.mvvm.view.act.main.MutiSelectActivity;
import cn.ecar.insurance.utils.system.OtherUtil;
import cn.ecar.insurance.utils.ui.IntentUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;
import cn.ecar.insurance.utils.ui.rxui.RxViewUtils;
import cn.ecar.insurance.widget.verticaltablayout.badgeview.DisplayUtil;

/**
 * @author ding
 */
public class InsureActivity1 extends BaseBindingActivity<ActivityInsure1Binding> implements OnViewClick {

    private PopupWindow mPopupWindow;
    private int[] windowLocation = new int[2];

    @Override
    public void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_insure1;
    }

    @Override
    protected void initView() {
        mVB.viewTitle.setTitle("车险1");
    }

    /**
     * 初始化
     */
    private PopupWindow initPopupWindow(Context context, ArrayList<String> data,
                                        AdapterView.OnItemClickListener onItemClickListener) {
        View mView = LayoutInflater.from(context).inflate(R.layout.select_popwindow_layout, null);
        PopupWindow popupWindow = new PopupWindow(
                mView,
                (int) OtherUtil.getScreenWidth(context) / 4,
                (int) OtherUtil.getScreenHeight(context) / 2,
                true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOnDismissListener(() -> {
            // 设置背景灰度
            backgroundAlpha(1f, (Activity) context);
        });
        ListView listView = mView.findViewById(R.id.select_list);
        listView.setAdapter(new ArrayAdapter<>(context, R.layout.item_select_listview_tv, R.id.tv_content, data));
        listView.setOnItemClickListener(onItemClickListener);
        return popupWindow;
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha, Activity activity) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        //0.0-1.0
        lp.alpha = bgAlpha;
        activity.getWindow().setAttributes(lp);
    }


    @Override
    protected void initData() {
        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(Cheese.PLATE_HEADER));
        mPopupWindow = initPopupWindow(
                mContext,
                arrayList,
                (parent, view, position, id) -> {
                    mVB.tvPlate.setText(arrayList.get(position));
                    mPopupWindow.dismiss();
                });
    }

    @Override
    protected void initEvent() {
        RxViewUtils.onViewClick(mVB.lBtPlate, this);
        RxViewUtils.onViewClick(mVB.lBtRegion, this);
        RxViewUtils.onViewClick(mVB.btNext, this);


    }

    @Override
    protected void destroyView() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.l_bt_plate:
                if (mPopupWindow != null) {
                    mVB.lBtPlate.getLocationOnScreen(windowLocation);
                    mPopupWindow.showAtLocation(
                            mVB.lBtPlate,
                            Gravity.NO_GRAVITY,
                            windowLocation[0],
                            windowLocation[1] + mVB.lBtPlate.getHeight());
                    backgroundAlpha(0.5f, this);
                }
                break;
            case R.id.l_bt_region:
                new IntentUtils.Builder(mContext)
                        .setTargetActivity(MutiSelectActivity.class)
                        .build().startActivityForResult(XdConfig.LOCATION_MUTISELECT_REQUEST);
                break;
            case R.id.bt_next:
                break;
            default:
        }
    }
}
