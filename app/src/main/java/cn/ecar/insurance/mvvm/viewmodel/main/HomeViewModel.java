package cn.ecar.insurance.mvvm.viewmodel.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.graphics.drawable.Drawable;

import java.util.List;

import cn.ecar.insurance.dao.bean.CustomerMember;
import cn.ecar.insurance.dao.bean.Information;
import cn.ecar.insurance.dao.bean.Message;
import cn.ecar.insurance.dao.gson.CustomerShowGson;
import cn.ecar.insurance.dao.gson.SignInGson;
import cn.ecar.insurance.mvvm.model.main.HomeModel;

/**
 * @author yx
 * @date 2017/8/11
 * custom viewmodel
 * 包含三方库调用api(百度地图,友盟,环信等),app启动时调用api,产品统计等相关api
 */
public class HomeViewModel extends ViewModel {

    private HomeModel mHomeModel;


    public HomeViewModel() {
        mHomeModel = HomeModel.getInstance();
    }

    /**
     * 获得广告图片
     *
     * @return
     */
    public LiveData<List<Drawable>> getAdvertsDrawable() {

        return mHomeModel.getAdvertsDrawable();
    }

    /**
     * 获的明星会员
     * @return
     */
    public LiveData<List<CustomerMember>> getCustomerShowList() {
        return mHomeModel.getCustomerShowList();
    }

    /**
     * 获取英雄榜信息
     * @return
     */
    public LiveData<CustomerShowGson> getCustomerHero() {
        return mHomeModel.getCustomerHero();
    }

    /**
     * 签到
     * @return
     */
    public LiveData<SignInGson> customerSignToday() {
        return mHomeModel.customerSignToday();
    }

    /**
     * 查询今天是否签到
     * @return
     */
    public LiveData<SignInGson> judgeCustomerIsSignToday() {
        return mHomeModel.judgeCustomerIsSignToday();
    }

    /**
     * 获的分享资讯消息
     * @return
     */
    public LiveData<List<Message>> getShareMessageList() {
        return mHomeModel.getMessageShowList();
    }

    /**
     * 获的资讯消息
     * @return
     */
    public LiveData<List<Information>> getNoticeString() {

        return mHomeModel.getNoticeString();
    }
}
