package cn.ecar.insurance.mvvm.model.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdAppContext;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.Customer;
import cn.ecar.insurance.dao.bean.Information;
import cn.ecar.insurance.dao.bean.Message;
import cn.ecar.insurance.dao.gson.CustomerShowGson;
import cn.ecar.insurance.dao.gson.InformationListGson;
import cn.ecar.insurance.dao.gson.MessageListGson;
import cn.ecar.insurance.net.RetrofitUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;
import rx.Observer;

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
        drawables.add(resources.getDrawable(R.drawable.home_top_bg_2));
        drawables.add(resources.getDrawable(R.drawable.mingpian));
        drawables.add(resources.getDrawable(R.drawable.home_top_bg_2));
        drawables.add(resources.getDrawable(R.drawable.mingpian));
        drawables.add(resources.getDrawable(R.drawable.home_top_bg_2));

        data.postValue(drawables);
        return data;
    }

    public LiveData<List<Message>> getMessageShowList() {
        MutableLiveData<List<Message>> data = new MutableLiveData<>();
        RetrofitUtils.getInstance().getMessageList().subscribe(new Observer<MessageListGson>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Logger.e(e.toString());
                ToastUtils.showToast("获取分享信息失败");
            }

            @Override
            public void onNext(MessageListGson messageListGson) {
                Logger.e(messageListGson.toString());
                if (XdConfig.RESPONSE_T.equals(messageListGson.getResponseCode())) {
                    data.postValue(messageListGson.getMessageShowDto());
                } else {
                    ToastUtils.showToast(messageListGson.getResponseMsg());
                }
            }
        });
        return data;
    }

    public LiveData<List<Customer>> getCustomerShowList() {
        MutableLiveData<List<Customer>> data = new MutableLiveData<>();
        RetrofitUtils.getInstance().getCustomerShowList().subscribe(new Observer<CustomerShowGson>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Logger.e(e.toString());
                ToastUtils.showToast("获取会员信息失败");
            }

            @Override
            public void onNext(CustomerShowGson customerShowGson) {
                if (XdConfig.RESPONSE_T.equals(customerShowGson.getResponseCode())) {
                    if (customerShowGson.getCustomers() != null && !customerShowGson.getCustomers().isEmpty()){
                        data.postValue(customerShowGson.getCustomers());
                    }
                } else {
                    ToastUtils.showToast(customerShowGson.getResponseMsg());
                }
            }
        });
        return data;
    }

    public LiveData<List<Information>> getNoticeString() {
        MutableLiveData<List<Information>> data = new MutableLiveData<>();
        RetrofitUtils.getInstance().getInformationList().subscribe(new Observer<InformationListGson>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Logger.e(e.toString());
                ToastUtils.showToast("获取资讯失败");
            }

            @Override
            public void onNext(InformationListGson informationGson) {
                Logger.e(informationGson.toString());
                if (XdConfig.RESPONSE_T.equals(informationGson.getResponseCode())) {
                    data.postValue(informationGson.getInformationDto());
                } else {
                    ToastUtils.showToast(informationGson.getResponseMsg());
                }
            }
        });
        return data;
    }
}
