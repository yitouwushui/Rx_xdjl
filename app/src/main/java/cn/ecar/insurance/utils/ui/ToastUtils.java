package cn.ecar.insurance.utils.ui;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdAppContext;

/**
 *
 * @author yx
 * @date 2016/7/22
 * 自定义单例吐司
 */
public class ToastUtils {

    private static Toast sToast;

    public static void showToast(String content) {
        Context context = XdAppContext.app().getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);
        TextView tvToast = (TextView) view.findViewById(R.id.tv_toast);
        if (sToast == null) {
            sToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
            sToast.setGravity(Gravity.BOTTOM, 0, CommonUtils.dip2px(80));
        }
        tvToast.setText(content);
        sToast.setView(view);
        sToast.show();
    }


}
