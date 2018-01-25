package cn.ecar.insurance.mvvm.model.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdAppContext;

/**
 * @author ding
 * @date 2017/12/19
 */

public class ListModel {

    private static volatile ListModel instance;

    public static ListModel getInstance() {
        if (instance == null) {
            synchronized (ListModel.class) {
                if (instance == null) {
                    instance = new ListModel();
                }
            }
        }
        return instance;
    }

    public LiveData<List<Drawable>> getAdvertsDrawable() {
        MutableLiveData<List<Drawable>> data = new MutableLiveData<>();

        Resources resources = XdAppContext.app().getResources();
        ArrayList<Drawable> drawables = new ArrayList<>();
        drawables.add(resources.getDrawable(R.drawable.home_top_bg_2));
        drawables.add(resources.getDrawable(R.drawable.mingpian));
        drawables.add(resources.getDrawable(R.drawable.home_top_bg_2));
        drawables.add(resources.getDrawable(R.drawable.mingpian));
        drawables.add(resources.getDrawable(R.drawable.home_top_bg_2));

        data.postValue(drawables);
        return data;
    }


}
