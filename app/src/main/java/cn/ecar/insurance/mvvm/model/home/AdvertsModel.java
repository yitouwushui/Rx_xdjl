package cn.ecar.insurance.mvvm.model.home;

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

public class AdvertsModel {

    private static volatile AdvertsModel instance;

    public static AdvertsModel getInstance() {
        if (instance == null) {
            synchronized (AdvertsModel.class) {
                if (instance == null) {
                    instance = new AdvertsModel();
                }
            }
        }
        return instance;
    }

    public LiveData<List<Drawable>> getDrawable() {
        MutableLiveData<List<Drawable>> data = new MutableLiveData<>();

        Resources resources = XdAppContext.app().getResources();
        ArrayList<Drawable> drawables = new ArrayList<>();
        drawables.add(resources.getDrawable(R.drawable.si_dlbanner3x));
        drawables.add(resources.getDrawable(R.drawable.mingpian));
        drawables.add(resources.getDrawable(R.drawable.si_dlbanner3x));
        drawables.add(resources.getDrawable(R.drawable.mingpian));
        drawables.add(resources.getDrawable(R.drawable.si_dlbanner3x));
        drawables.add(resources.getDrawable(R.drawable.mingpian));

        data.postValue(drawables);
        return data;
    }
}
