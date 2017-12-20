package cn.ecar.insurance.mvvm.model.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdAppContext;
import cn.ecar.insurance.entity.Member;

/**
 * @author ding
 * @date 2017/12/19
 */

public class HomeModel {

    private static volatile HomeModel instance;

    public static HomeModel getInstance() {
        if (instance == null) {
            synchronized (HomeModel.class) {
                if (instance == null) {
                    instance = new HomeModel();
                }
            }
        }
        return instance;
    }

    public LiveData<List<Drawable>> getAdvertsDrawable() {
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

    public LiveData<List<Member>> getNewsString() {
        MutableLiveData<List<Member>> news = new MutableLiveData<>();
        ArrayList<Member> members = new ArrayList<>();
        Resources resources = XdAppContext.app().getResources();

        for (int i = 0; i < 10; i++) {
            Member member = new Member();
            member.setIcon(resources.getDrawable(R.mipmap.ic_launcher));
            member.setName("name" + i);
            member.setContent("分享了多少");
            members.add(member);
        }
        news.postValue(members);
        return news;
    }
}
