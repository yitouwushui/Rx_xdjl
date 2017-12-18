package cn.ecar.insurance.xdjl.utils.ui;

import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;

import cn.ecar.insurance.xdjl.utils.ui.rxui.TextChangeListener;

/**
 * Created by lq on 2017/11/29.
 */

public class TextWatcherUtils {

    /**
     * 整数限制要求 最大值限制和非零限制
     *
     * @param maxLimit
     * @param msg
     * @return
     */
    public static TextChangeListener getIntengerWatcher(int maxLimit, String msg) {
        return (editText, string) -> {
            Editable editable = editText.getText();
            int len = editable.length();
            if (len == 1 && editable.toString().equals("0")) {
                editText.setText("");
            }
            try {
                if (!TextUtils.isEmpty(editable.toString()) && Double.valueOf(editable.toString()) > maxLimit) {
                    int selEndIndex = Selection.getSelectionEnd(editable);
                    String str = editable.toString();
                    //截取新字符串
                    String newStr = str.substring(0, len - 1);
                    editText.setText(newStr);
                    editable = editText.getText();
                    //新字符串的长度
                    int newLen = editable.length();
                    //旧光标位置超过字符串长度
                    if (selEndIndex > newLen) {
                        selEndIndex = editable.length();
                    }
                    //设置新光标所在的位置
                    Selection.setSelection(editable, selEndIndex);
                    if (!msg.equals("")) {
                        ToastUtils.showToast(msg);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
