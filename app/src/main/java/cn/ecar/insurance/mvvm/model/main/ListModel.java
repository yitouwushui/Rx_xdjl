package cn.ecar.insurance.mvvm.model.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdAppContext;
import cn.ecar.insurance.entity.Member;
import cn.ecar.insurance.entity.NoticeInfo;

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

    public LiveData<List<Member>> getNewsString() {
        MutableLiveData<List<Member>> news = new MutableLiveData<>();
        ArrayList<Member> members = new ArrayList<>();
        Resources resources = XdAppContext.app().getResources();

        for (int i = 0; i < 10; i++) {
            Member member = new Member();
            member.setIcon(resources.getDrawable(R.drawable.home_member_filling));
            member.setName("张三" + i);
            member.setContent("分享给i位");
            members.add(member);
        }
        news.postValue(members);
        return news;
    }

    public LiveData<List<NoticeInfo>> getNoticeString() {
        MutableLiveData<List<NoticeInfo>> data = new MutableLiveData<>();
        ArrayList<NoticeInfo> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            NoticeInfo noticeInfo = new NoticeInfo();
            noticeInfo.setTitle("这是一条通知，" + i);
            list.add(noticeInfo);
        }
        data.postValue(list);
        return data;
    }
}
