package cn.ecar.insurance.utils.ui;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import cn.ecar.insurance.config.XdAppContext;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.utils.file.FileUtils;


/**
 * Created by yx on 2017/9/19.
 * 通用的工具类,方便调用,想到什么写什么吧
 */

public class CustomUtils {

    /**
     * 隐藏软键盘
     */
    public static void hideSoftInput(View view) {
        InputMethodManager imm = (InputMethodManager) XdAppContext.app().getmContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 隐藏或显示软键盘
     */
    public static void hideOrShowSoftInput() {
        InputMethodManager imm = (InputMethodManager) XdAppContext.app()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

    }

    /**
     * dip转px
     *
     * @param dpValue
     * @return
     */
    public static int dip2px(float dpValue) {
        Context context = XdAppContext.app().getmContext();
        final float density = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);
    }

    /**
     * px转dip
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float density = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / density + 0.5f);
    }


    /**
     * 判断手机号码
     * @param
     * @return
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147,145))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }


    /**
     * 获取 EditText TextView Spinner上面的文本信息
     *
     * @param v
     * @return
     */
    public static String getString(View v) {
        try {
            if (v instanceof EditText) {
                return ((EditText) v).getText().toString().trim();
            } else if (v instanceof TextView) {
                return ((TextView) v).getText().toString().trim();
            } else if (v instanceof Spinner) {
                return ((Spinner) v).getSelectedItem().toString().trim();
            } else if (v instanceof Button) {
                return ((Button) v).getText().toString().trim();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return XdConfig.BLANK;
        }
        return XdConfig.BLANK;
    }

    public static boolean contains(List list, String str) {
        for (int i = 0; i < list.size(); i++) {
            String temp = ((String) list.get(i)).trim();
            if(temp.contains(str.trim()) || (str.trim()).contains(temp)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据key得到spinnerApi中value的值
     *
     * @param key
     * @param dict
     * @return
     */
    public static String getDictValue(Context context, String key, String dict) {
        JSONArray items;
        String temp = "";
        try {
            String mappings = FileUtils.readFileFromAssets(context, "spinnerApi");
            JSONObject jsonObject = new JSONObject(mappings);
            items = jsonObject.getJSONArray(dict);
            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                if (key.equals(item.getString(XdConfig.OPTION))) {
                    temp = item.getString(XdConfig.TEXT);
                    return temp;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return temp;
    }


    /**
     * 根据value得到spinnerApi中的key值
     *
     * @param value
     * @param dict
     * @return
     */
    public static String getDictOption(Context context, String value, String dict) {
        JSONArray items;
        String option = "";
        try {
            String mappings = FileUtils.readFileFromAssets(context, "spinnerApi");
            items = new JSONObject(mappings).getJSONArray(dict);
            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                if (value.equals(item.getString(XdConfig.TEXT))) {
                    option = item.getString(XdConfig.OPTION);
                    return option;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return option;
    }

    /**
     * 获得spinnerApi的数组
     *
     * @param context
     * @param type
     * @return
     */
    public static ArrayList getDictTextList(Context context, String type) {
        ArrayList list = new ArrayList();
        JSONArray items;
        try {
            String mappings = FileUtils.readFileFromAssets(context, "spinnerApi");
            items = new JSONObject(mappings).getJSONArray(type);
            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                list.add(item.getString(XdConfig.TEXT));
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static String listToString(List list) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        return sb.toString();
    }
}
