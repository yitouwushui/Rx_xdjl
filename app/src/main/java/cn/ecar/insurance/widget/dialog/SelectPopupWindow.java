//package cn.ecar.insurance.widget.dialog;
//
//import android.app.Activity;
//import android.content.Context;
//import android.graphics.drawable.BitmapDrawable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.WindowManager;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.PopupWindow;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import cn.ecar.insurance.R;
//
///**
// * Created by yitouwushui on 2018/1/16.
// */
//public class SelectPopupWindow extends PopupWindow {
//
//    private PopupWindow mPopupWindow;
//
//    public SelectPopupWindow(Context context, ArrayAdapter<String> arrayAdapter, AdapterView.OnItemClickListener onItemClickListener) {
//        View mView = LayoutInflater.from(context).inflate(R.layout.select_popwindow_layout, null);
//
//        mPopupWindow = new PopupWindow(mView, ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT, true);
//        mPopupWindow.setFocusable(true);
//        mPopupWindow.setOutsideTouchable(true);
//        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
//        mPopupWindow.setOnDismissListener(() -> {
//            // 设置背景灰度
//            backgroundAlpha(1f, (Activity) context);
//        });
//        ListView listView = mView.findViewById(R.id.select_list);
//        listView.setAdapter(arrayAdapter);
//        listView.setOnItemClickListener(onItemClickListener);
//    }
//
//    public PopupWindow getPopupWindow() {
//        return mPopupWindow;
//    }
//
//    /**
//     * 设置添加屏幕的背景透明度
//     *
//     * @param bgAlpha
//     */
//    public void backgroundAlpha(float bgAlpha, Activity activity) {
//        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
//        //0.0-1.0
//        lp.alpha = bgAlpha;
//        activity.getWindow().setAttributes(lp);
//    }
//}
