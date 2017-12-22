package cn.ecar.insurance.widget.convenientbanner.holder;

/**
 * Created by Sai on 15/12/14.
 * @param <T> 任何你指定的对象
 */

import android.content.Context;
import android.view.View;

public interface Holder<T>{
    View createView(Context context);
    void updateUI(Context context, int position, T data);
}