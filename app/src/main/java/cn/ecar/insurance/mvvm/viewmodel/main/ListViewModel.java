package cn.ecar.insurance.mvvm.viewmodel.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.graphics.drawable.Drawable;

import java.util.List;

import cn.ecar.insurance.entity.Member;
import cn.ecar.insurance.entity.NoticeInfo;
import cn.ecar.insurance.mvvm.model.main.HomeModel;

/**
 * @author yx
 * @date 2017/8/11
 * custom viewmodel
 * 包含三方库调用api(百度地图,友盟,环信等),app启动时调用api,产品统计等相关api
 */
public class ListViewModel extends ViewModel {

    private HomeModel mHomeModel;


    public ListViewModel() {
        mHomeModel = HomeModel.getInstance();
    }

    /**
     * 获的会员资讯消息
     * @return
     */
    public LiveData<List<Member>> getNewsString() {

        return mHomeModel.getNewsString();
    }

}