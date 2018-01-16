package cn.ecar.insurance.widget.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import cn.ecar.insurance.R;

/**
 * Created by yitouwushui on 2018/1/16.
 */
public class SelectPopupWindow {

    private PopupWindow mPopupWindow;

    public PopupWindow getPopupWindow(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.select_popwindow_layout, null);
        PopupWindow popupWindow = null;

        //生成证件列表
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT, true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f, (Activity) context);
            }
        });
        ListView listView = (ListView) view.findViewById(R.id.select_list);
        ArrayAdapter<String> adapter;
//        ArrayList<BankInfo> credents = MyApplication.getPublicInfo().getBankInfos();
//        final String[] banknamearr = new String[credents.size()];
//        final String[] bankcodearr = new String[credents.size()];
//        for (int a = 0; a < credents.size(); a++) {
//            banknamearr[a] = credents.get(a).getBankName();
//            bankcodearr[a] = credents.get(a).getBankCode();
//        }
//        adapter = new ArrayAdapter<>(context, R.layout.item_listview_tv, R.id.tv_content, banknamearr);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                tvBankName.setText(banknamearr[position]);
//                bankcode = bankcodearr[position];
//                LoginPersonalInfo.getInstance().setBankCode(bankcodearr[position]);
//                bankinfopopupwindow.dismiss();
//            }
//        });
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
}
