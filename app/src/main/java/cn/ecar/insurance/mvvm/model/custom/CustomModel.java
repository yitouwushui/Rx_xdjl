package cn.ecar.insurance.mvvm.model.custom;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;

import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.base.BaseEntity;
import cn.ecar.insurance.dao.bean.Insurance;
import cn.ecar.insurance.dao.bean.SignIn;
import cn.ecar.insurance.dao.bean.Team;
import cn.ecar.insurance.dao.gson.BalanceGson;
import cn.ecar.insurance.dao.gson.BankGson;
import cn.ecar.insurance.dao.gson.CustomerGson;
import cn.ecar.insurance.dao.gson.InsuranceGson;
import cn.ecar.insurance.dao.gson.SignInGson;
import cn.ecar.insurance.dao.gson.TeamGson;
import cn.ecar.insurance.mvvm.base.BaseModel;
import cn.ecar.insurance.net.RetrofitUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;
import rx.Observer;

/**
 * @author yx
 * @date 2017/8/11
 * custom model
 */

public class CustomModel extends BaseModel {

    private static volatile CustomModel instance;

    public static CustomModel getInstance() {
        if (instance == null) {
            synchronized (CustomModel.class) {
                if (instance == null) {
                    instance = new CustomModel();
                }
            }
        }
        return instance;
    }

    private CustomModel() {
        super();
    }

    public BaseEntity getBase() {
        return new BaseEntity();
    }


    public LiveData<BankGson> getBankInfo() {
        LiveData<BankGson> bankInfo = new MutableLiveData<>();

        return bankInfo;
    }

    public LiveData<CustomerGson> getCustomerAllInfo() {
        MutableLiveData<CustomerGson> data = new MutableLiveData<>();
        RetrofitUtils.getInstance().getCustomerAllInfo(RetrofitUtils.getSessionId()).subscribe(new Observer<CustomerGson>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showToast(e.toString());
            }

            @Override
            public void onNext(CustomerGson customerGson) {
                if (customerGson.getResponseCode().equals(XdConfig.RESPONSE_T)) {
                    data.postValue(customerGson);
                } else {
                    ToastUtils.showToast(customerGson.getResponseMsg());
                }
            }
        });
        return data;
    }

    public LiveData<BalanceGson> goToWithdrawals() {
        MutableLiveData<BalanceGson> data = new MutableLiveData<>();
        RetrofitUtils.getInstance().goToWithdrawals().subscribe(new Observer<BalanceGson>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                BalanceGson error = new BalanceGson();
                error.setResponseCode(XdConfig.RESPONSE_F);
                error.setResponseMsg(XdConfig.RESPONSE_MSG_F);
                data.postValue(error);
            }

            @Override
            public void onNext(BalanceGson balanceGson) {
                data.postValue(balanceGson);
            }
        });
        return data;
    }

    public LiveData<InsuranceGson> getMyInsuranceList() {
        MutableLiveData<InsuranceGson> data = new MutableLiveData<>();
        InsuranceGson insuranceGson = new InsuranceGson();
        insuranceGson.setResponseCode(XdConfig.RESPONSE_T);
        ArrayList<Insurance> insurances = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Insurance insurance = new Insurance();
            insurance.setId(i);
            insurances.add(insurance);
        }
        insuranceGson.setData(insurances);
        data.postValue(insuranceGson);
//        RetrofitUtils.getInstance().getMyInsuranceList().subscribe(new Observer<InsuranceGson>() {
//            @Override
//            public void onCompleted() {
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                InsuranceGson error = new InsuranceGson();
//                error.setResponseCode(XdConfig.RESPONSE_F);
//                error.setResponseMsg(XdConfig.RESPONSE_MSG_F);
//                data.postValue(error);
//            }
//
//            @Override
//            public void onNext(InsuranceGson balanceGson) {
//                data.postValue(balanceGson);
//            }
//        });
        return data;
    }

    public LiveData<TeamGson> getMyTeamList() {
        MutableLiveData<TeamGson> data = new MutableLiveData<>();
        TeamGson teamGson = new TeamGson();
        teamGson.setResponseCode(XdConfig.RESPONSE_T);
        ArrayList<Team> insurances = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Team t = new Team();
            t.setId(i);
            insurances.add(t);
        }
        teamGson.setData(insurances);
        data.postValue(teamGson);
//        RetrofitUtils.getInstance().getMyInsuranceList().subscribe(new Observer<InsuranceGson>() {
//            @Override
//            public void onCompleted() {
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                InsuranceGson error = new InsuranceGson();
//                error.setResponseCode(XdConfig.RESPONSE_F);
//                error.setResponseMsg(XdConfig.RESPONSE_MSG_F);
//                data.postValue(error);
//            }
//
//            @Override
//            public void onNext(InsuranceGson balanceGson) {
//                data.postValue(balanceGson);
//            }
//        });
        return data;
    }

    public LiveData<SignInGson> getMySignInList() {
        MutableLiveData<SignInGson> data = new MutableLiveData<>();
        SignInGson signInGson = new SignInGson();
        signInGson.setResponseCode(XdConfig.RESPONSE_T);
        ArrayList<SignIn> insurances = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SignIn signIn = new SignIn();
            insurances.add(signIn);
        }
        signInGson.setData(insurances);
        data.postValue(signInGson);
//        RetrofitUtils.getInstance().getMyInsuranceList().subscribe(new Observer<InsuranceGson>() {
//            @Override
//            public void onCompleted() {
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                InsuranceGson error = new InsuranceGson();
//                error.setResponseCode(XdConfig.RESPONSE_F);
//                error.setResponseMsg(XdConfig.RESPONSE_MSG_F);
//                data.postValue(error);
//            }
//
//            @Override
//            public void onNext(InsuranceGson balanceGson) {
//                data.postValue(balanceGson);
//            }
//        });
        return data;
    }
}
