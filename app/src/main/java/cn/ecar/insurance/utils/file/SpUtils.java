package cn.ecar.insurance.utils.file;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

/**
 * @author yx
 * @date 2016/7/12
 * sp工具类
 */
public final class SpUtils {

    private static SharedPreferences sp;
    private static final String SP_NAME = "config";
    private static Gson gson;
    private static final String LIST_TAG = ".LIST";

    public static void init(Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
            // 避免一些字符自动转换为Unicode转义字符
            gson = new GsonBuilder().disableHtmlEscaping().create();
        }
    }

    /**
     * 存储boolean型sp
     *
     * @param key
     * @param value
     */
    public static void putBoolean(String key, boolean value) {
        sp.edit().putBoolean(key, value).apply();
    }

    /**
     * 获取boolean型sp
     *
     * @param key
     * @return
     */
    public static boolean getBoolean(String key) {
        return sp.getBoolean(key, false);
    }

    /**
     * 存储String型sp
     *
     * @param key
     * @param value
     */
    public static void putString(String key, String value) {
        sp.edit().putString(key, value).apply();
    }

    /**
     * 获取String型sp
     *
     * @param key
     * @return
     */
    public static String getString(String key) {
        return sp.getString(key, "");
    }

    /**
     * 存储int型sp
     *
     * @param key
     * @param value
     */
    public static void putInt(String key, int value) {
        sp.edit().putInt(key, value).apply();
    }

    /**
     * 获取int型sp
     *
     * @param key
     * @return
     */
    public static int getInt(String key) {
        return sp.getInt(key, -1);
    }

    /**
     * 清除sp
     *
     * @param key
     */
    public static void removeSpWithKey(String key) {
        sp.edit().remove(key).apply();
    }

    /**
     * 清除所有sp
     */
    public static void removeAllSp() {
        sp.edit().clear().apply();
    }


    /**
     * 保存对象数据至SharedPreferences, key默认为类名, 如
     * <pre>
     * PreferencesHelper.putData(saveUser);
     * </pre>
     *
     * @param data 不带泛型的任意数据类型实例
     */
    public static <T> void putData(T data) {
        putData(data.getClass().getName(), data);
    }

    /**
     * 根据key保存对象数据至SharedPreferences, 如
     * <pre>
     * PreferencesHelper.putData(key, saveUser);
     * </pre>
     *
     * @param data 不带泛型的任意数据类型实例
     */
    public static <T> void putData(String key, T data) {
        checkInit();
        if (data == null) {
            throw new IllegalStateException("data should not be null.");
        }
        sp.edit().putString(key, gson.toJson(data)).apply();
    }

    /**
     * 保存List集合数据至SharedPreferences, 请确保List至少含有一个元素, 如
     * <pre>
     * PreferencesHelper.putData(users);
     * </pre>
     *
     * @param data List类型实例
     */
    public static <T> void putData(List<T> data) {
        checkInit();
        if (data == null || data.size() <= 0) {
            throw new IllegalStateException(
                    "List should not be null or at least contains one element.");
        }
        Class returnType = data.get(0).getClass();
        sp.edit().putString(returnType.getName() + LIST_TAG,
                gson.toJson(data)).apply();
    }

    /**
     * 将数据从SharedPreferences中取出, key默认为类名, 如
     * <pre>
     * User user = PreferencesHelper.getData(key, User.class)
     * </pre>
     */
    public static <T> T getData(Class<T> clz) {
        return getData(clz.getName(), clz);
    }

    /**
     * 根据key将数据从SharedPreferences中取出, 如
     * <pre>
     * User user = PreferencesHelper.getData(User.class)
     * </pre>
     */
    public static <T> T getData(String key, Class<T> clz) {
        checkInit();
        String json = sp.getString(key, "");
        return gson.fromJson(json, clz);
    }

    private static void checkInit() {
        if (sp == null || gson == null) {
            throw new IllegalStateException("Please call init(context) first.");
        }
    }

}
