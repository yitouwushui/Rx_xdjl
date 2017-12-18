package cn.ecar.insurance.xdjl.utils.ui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.ecar.insurance.xdjl.R;
import cn.ecar.insurance.xdjl.adapter.abslistview.CommonAdapter;
import cn.ecar.insurance.xdjl.adapter.abslistview.ViewHolder;
import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by yx on 2016/8/11.
 * dialog工具类
 */
public class MdDialogUtils {


    /***
     * 获取一个dialog
     *
     * @param context
     * @return
     */
    public static AlertDialog.Builder getDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        return builder;
    }

    public static AlertDialog getCreateDialog(Context context) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        return alertDialog;
    }



    /***
     * 获取一个信息对话框，注意需要自己手动调用show方法显示
     *
     * @param context
     * @param message
     * @param onClickListener
     * @return
     */
    public static AlertDialog.Builder getMessageDialog(Context context, String message, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = getDialog(context);
        builder.setMessage(message);
        builder.setPositiveButton("确定", onClickListener);
        return builder;
    }

    public static AlertDialog.Builder getMessageDialog(Context context, String message) {
        return getMessageDialog(context, message, null);
    }

    public static AlertDialog.Builder getConfirmDialog(Context context, String message, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = getDialog(context);
        builder.setMessage(Html.fromHtml(message));
        builder.setPositiveButton("确定", onClickListener);
        builder.setNegativeButton("取消", null);
        return builder;
    }

    public static AlertDialog.Builder getConfirmDialog(Context context, String message, DialogInterface.OnClickListener onOkClickListener, DialogInterface.OnClickListener onCancleClickListener) {
        AlertDialog.Builder builder = getDialog(context);
        builder.setMessage(message);
        builder.setPositiveButton("确定", onOkClickListener);
        builder.setNegativeButton("取消", onCancleClickListener);
        return builder;
    }

    public static AlertDialog.Builder getSelectDialog(Context context, String title, String[] arrays, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = getDialog(context);
        builder.setItems(arrays, onClickListener);
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        builder.setPositiveButton("取消", null);
        return builder;
    }

    public static AlertDialog.Builder getSelectDialog(Context context, String[] arrays, DialogInterface.OnClickListener onClickListener) {
        return getSelectDialog(context, "", arrays, onClickListener);
    }

    public static AlertDialog.Builder getSingleChoiceDialog(Context context, String title, String[] arrays, int selectIndex, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = getDialog(context);
        builder.setSingleChoiceItems(arrays, selectIndex, onClickListener);
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        builder.setNegativeButton("取消", null);
        return builder;
    }

    public static AlertDialog.Builder getSingleChoiceDialog(Context context, String[] arrays,
                                                            int selectIndex, DialogInterface.OnClickListener onClickListener) {
        return getSingleChoiceDialog(context, "", arrays, selectIndex, onClickListener);
    }


    public static List<String> createList(String... str) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, str);
        return list;
    }

    public static void showListViewDialog(Context context, final List<String> list,
                                          String title, final TextView textView, String oldContent) {
        final AlertDialog alertDialog = getDialog(context).create();
        final ArrayList<String> str = new ArrayList<>();
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.dialog_listview_layout);
        TextView tv_title = (TextView) window.findViewById(R.id.tv_title);
        tv_title.setText(title);
        if (title.equals("")) {
            tv_title.setVisibility(View.GONE);
        }
        //记录选中状态
        final Boolean[] isTrue = new Boolean[list.size()];
        for (int j = 0; j < list.size(); j++) {
            isTrue[j] = false;
        }
        Button button = (Button) window.findViewById(R.id.button_confrim);
        button.setVisibility(View.VISIBLE);
        ListView lvContent = (ListView) window.findViewById(R.id.lv_content);
        if (!oldContent.equals("")) {
            String[] temp = oldContent.split(",");
            for (String s : temp) {
                if (list.contains(s)) {
                    int i = list.indexOf(s.trim());
                    str.add(list.get(i));
                    isTrue[i] = true;
                }
            }
        }
        final CommonAdapter adapter = new CommonAdapter<String>(context, R.layout.dialog_listview_item, list) {
            @Override
            protected void convert(ViewHolder viewHolder, String str, int position) {
                viewHolder.setText(R.id.text_requirement, str);
                viewHolder.setChecked(R.id.check_is_need, isTrue[position]);
            }

        };
        lvContent.setAdapter(adapter);
        lvContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //清楚所有状态
                for (int j = 0; j < list.size(); j++) {
                    isTrue[j] = false;
                }
                isTrue[i] = true;
                adapter.notifyDataSetChanged();
                str.clear();
                str.add(list.get(i));
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (str.size() > 0) {
                    textView.setText(str.get(0));
                }
                alertDialog.dismiss();
            }
        });
    }

    public static void showListViewDialog(Context context, List<String> list, String title, TextView textView) {
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        MaterialDialog materialDialog = new MaterialDialog(context).setTitle(title).setCanceledOnTouchOutside(true);
        ListView listView = new ListView(context);
        listView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        listView.setDividerHeight(0);
//        listView.setAdapter(new CommonAdapter<String>(context, R.layout.item_shaixuan, list) {
//            @Override
//            protected void convert(ViewHolder viewHolder, String str, int position) {
//                viewHolder.setText(R.id.tv_riqi, str);
//            }
//        });
        listView.setOnItemClickListener((parent, view1, position, id) -> {
            materialDialog.dismiss();
            textView.setText(list.get(position));
        });
        materialDialog.setContentView(listView);
        materialDialog.show();
    }

    public static void showListViewDialog(Context context, List<String> list, String title,
                                          TextView textView, OnDialogConfirmListener listener) {
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        MaterialDialog materialDialog = new MaterialDialog(context).setTitle(title).setCanceledOnTouchOutside(true);
        ListView listView = new ListView(context);
        listView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        listView.setDividerHeight(0);
//        listView.setAdapter(new CommonAdapter<String>(context, R.layout.item_shaixuan, list) {
//            @Override
//            protected void convert(ViewHolder viewHolder, String str, int position) {
//                viewHolder.setText(R.id.tv_riqi, str);
//            }
//        });
        listView.setOnItemClickListener((parent, view1, position, id) -> {
            materialDialog.dismiss();
            textView.setText(list.get(position));
            listener.onConfirmClick(view1);
        });
        materialDialog.setContentView(listView);
        materialDialog.show();
    }

    public static void showListViewDialog(Context context, List<String> list, String title, OnItemClickListener listener) {
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        MaterialDialog materialDialog = new MaterialDialog(context).setTitle(title).setCanceledOnTouchOutside(true);
        ListView listView = new ListView(context);
        listView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        listView.setDividerHeight(0);
//        listView.setAdapter(new CommonAdapter<String>(context, R.layout.item_shaixuan, list) {
//            @Override
//            protected void convert(ViewHolder viewHolder, String str, int position) {
//                viewHolder.setText(R.id.tv_riqi, str);
//            }
//        });
        listView.setOnItemClickListener((parent, view1, position, id) -> {
            materialDialog.dismiss();
            listener.onItemClick(view1, position);

        });
        materialDialog.setContentView(listView);
        materialDialog.show();

    }


    public static void showMesseageDialog(Context context, String title, String text, OnDialogConfirmListener listener) {
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        MaterialDialog materialDialog = new MaterialDialog(context).setCanceledOnTouchOutside(true).setTitle(title)
                .setMessage(text);
        materialDialog.setPositiveButton("确定", v -> {
            materialDialog.dismiss();
            listener.onConfirmClick(v);
        }).setNegativeButton("取消", v -> materialDialog.dismiss());
        materialDialog.show();

    }

    public static void showNoCancleMesseageDialog(Context context, String title, String text, OnDialogConfirmListener listener) {
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        MaterialDialog materialDialog = new MaterialDialog(context).setCanceledOnTouchOutside(true).setTitle(title)
                .setMessage(text);
        materialDialog.setPositiveButton("确定", v -> {
            materialDialog.dismiss();
            listener.onConfirmClick(v);
        });
        materialDialog.show();

    }

    public static void showNoTouchOutsiceMsgDialog(Context context, String title, String text, OnDialogConfirmListener listener) {
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        MaterialDialog materialDialog = new MaterialDialog(context).setCanceledOnTouchOutside(false).setTitle(title)
                .setMessage(text);
        materialDialog.setPositiveButton("确定", v -> {
            materialDialog.dismiss();
            listener.onConfirmClick(v);
        });
        materialDialog.show();

    }

    /**
     * 全部弹出dialog
     *
     * @param title
     * @param text
     * @param confirmText
     * @param listener
     */
    public static void showApplicationMsgDialog(Activity activity, String title, String text, String confirmText, OnDialogConfirmListener listener) {
        if (activity.isFinishing()) {
            return;
        }
        MaterialDialog materialDialog = new MaterialDialog(activity)
                .setCanceledOnTouchOutside(false).setTitle(title)
                .setMessage(text);
        materialDialog.setPositiveButton(confirmText, v -> {
            materialDialog.dismiss();
            listener.onConfirmClick(v);
        });
        materialDialog.show();
    }



    public static void showLocationDialog(Context context, String title, String text, String btnText, OnDialogConfirmListener listener) {
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        MaterialDialog materialDialog = new MaterialDialog(context).setCanceledOnTouchOutside(true)
                .setTitle(title).setMessage(text);
        materialDialog.setPositiveButton(btnText, v -> {
            materialDialog.dismiss();
            listener.onConfirmClick(v);
        }).setNegativeButton("取消", v -> materialDialog.dismiss());
        materialDialog.show();

    }

    public static void showLoginDialog(Context context, String title, String text, String confirm, String cancle,
                                       OnDialogConfirmListener confirmListener, OnDialogCancleListener cancleListener) {
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        MaterialDialog materialDialog = new MaterialDialog(context).setCanceledOnTouchOutside(true)
                .setTitle(title).setMessage(text);
        materialDialog.setPositiveButton(confirm, v -> {
            materialDialog.dismiss();
            confirmListener.onConfirmClick(v);
        }).setNegativeButton(cancle, v -> {
            cancleListener.onCancleClick(v);
            materialDialog.dismiss();
        });
        materialDialog.show();

    }

    public static void showLoginDialog(Context context, String title, String text, String confirm, String cancle, boolean isCancle,
                                       OnDialogConfirmListener confirmListener, OnDialogCancleListener cancleListener) {
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        MaterialDialog materialDialog = new MaterialDialog(context).setCanceledOnTouchOutside(isCancle).setTitle(title)
                .setMessage(text);
        materialDialog.setPositiveButton(confirm, v -> {
            materialDialog.dismiss();
            confirmListener.onConfirmClick(v);
        }).setNegativeButton(cancle, v -> {
            cancleListener.onCancleClick(v);
            materialDialog.dismiss();
        });
        materialDialog.show();

    }

    public interface OnDialogConfirmListener {
        void onConfirmClick(View view);
    }

    public interface OnDialogCancleListener {
        void onCancleClick(View view);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, Integer integer);
    }

}
