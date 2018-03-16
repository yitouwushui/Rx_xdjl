package cn.ecar.insurance.mvvm.model.custom;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Map;

import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.bean.SignIn;
import cn.ecar.insurance.dao.gson.AddressGson;
import cn.ecar.insurance.dao.gson.BalanceGson;
import cn.ecar.insurance.dao.gson.BankGson;
import cn.ecar.insurance.dao.gson.CustomerGson;
import cn.ecar.insurance.dao.gson.FrozenCashGson;
import cn.ecar.insurance.dao.gson.FundsFlowGson;
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


    public LiveData<BankGson> getBankInfo() {
        LiveData<BankGson> bankInfo = new MutableLiveData<>();

        return bankInfo;
    }

    public LiveData<CustomerGson> getCustomerAllInfo() {
        MutableLiveData<CustomerGson> data = new MutableLiveData<>();
        RetrofitUtils.getInstance().getCustomerAllInfo().subscribe(new Observer<CustomerGson>() {
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
        showWaitDialog();
        RetrofitUtils.getInstance().goToWithdrawals().subscribe(new Observer<BalanceGson>() {
            @Override
            public void onCompleted() {
                hideWaitDialog();
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

    public LiveData<InsuranceGson> getInsuranceOrderByPage(int pageNum, int pageSize) {
        MutableLiveData<InsuranceGson> data = new MutableLiveData<>();
        RetrofitUtils.getInstance().getInsuranceOrderByPage(pageNum, pageSize)
                .subscribe(new Observer<InsuranceGson>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        InsuranceGson error = new InsuranceGson();
                        error.setResponseCode(XdConfig.RESPONSE_F);
                        error.setResponseMsg(XdConfig.RESPONSE_MSG_F);
                        data.postValue(error);
                    }

                    @Override
                    public void onNext(InsuranceGson insuranceGson) {
                        data.postValue(insuranceGson);
                    }
                });
        return data;
    }

    public LiveData<TeamGson> getMyTeamList(int pageNum, int pageSize) {
        MutableLiveData<TeamGson> data = new MutableLiveData<>();
        RetrofitUtils.getInstance().getFirstTeamByPage(pageNum, pageSize)
                .subscribe(new Observer<TeamGson>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        TeamGson error = new TeamGson();
                        error.setResponseCode(XdConfig.RESPONSE_F);
                        error.setResponseMsg(XdConfig.RESPONSE_MSG_F);
                        data.postValue(error);
                    }

                    @Override
                    public void onNext(TeamGson teamGson) {
                        data.postValue(teamGson);
                    }
                });
        return data;
    }

    public LiveData<TeamGson> getTeamInfoByLevel(int pageNum, int level, int pageSize) {
        MutableLiveData<TeamGson> data = new MutableLiveData<>();
        RetrofitUtils.getInstance().getTeamInfoByLevel(pageNum, level, pageSize)
                .subscribe(new Observer<TeamGson>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        TeamGson error = new TeamGson();
                        error.setResponseCode(XdConfig.RESPONSE_F);
                        error.setResponseMsg(XdConfig.RESPONSE_MSG_F);
                        data.postValue(error);
                    }

                    @Override
                    public void onNext(TeamGson teamGson) {
                        data.postValue(teamGson);
                    }
                });
        return data;
    }

    public LiveData<FrozenCashGson> getFrozenCapitalList(int pageNum, int pageSize) {
        MutableLiveData<FrozenCashGson> data = new MutableLiveData<>();
        RetrofitUtils.getInstance().getFrozenCapitalList(pageNum, pageSize)
                .subscribe(new Observer<FrozenCashGson>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        FrozenCashGson error = new FrozenCashGson();
                        error.setResponseCode(XdConfig.RESPONSE_F);
                        error.setResponseMsg(XdConfig.RESPONSE_MSG_F);
                        data.postValue(error);
                    }

                    @Override
                    public void onNext(FrozenCashGson frozenCashGson) {
                        data.postValue(frozenCashGson);
                    }
                });
        return data;
    }

    public LiveData<FundsFlowGson> getFundsList(int pageNum, int pageSize) {
        MutableLiveData<FundsFlowGson> data = new MutableLiveData<>();
        RetrofitUtils.getInstance().getFundsList(pageNum, pageSize)
                .subscribe(new Observer<FundsFlowGson>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        FundsFlowGson error = new FundsFlowGson();
                        error.setResponseCode(XdConfig.RESPONSE_F);
                        error.setResponseMsg(XdConfig.RESPONSE_MSG_F);
                        data.postValue(error);
                    }

                    @Override
                    public void onNext(FundsFlowGson fundsFlowGson) {
                        data.postValue(fundsFlowGson);
                    }
                });
        return data;
    }

    public LiveData<SignInGson> getMySignInList(int pageNum, int pageSize) {
        MutableLiveData<SignInGson> data = new MutableLiveData<>();
        RetrofitUtils.getInstance().getCustomerSignByPage(pageNum, pageSize).subscribe(new Observer<SignInGson>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                SignInGson error = new SignInGson();
                error.setResponseCode(XdConfig.RESPONSE_F);
                error.setResponseMsg(XdConfig.RESPONSE_MSG_F);
                data.postValue(error);
            }

            @Override
            public void onNext(SignInGson signInGson) {
                data.postValue(signInGson);
            }
        });
        return data;
    }


    public LiveData<AddressGson> getCustomerAddressList() {
        MutableLiveData<AddressGson> data = new MutableLiveData<>();
        RetrofitUtils.getInstance().getCustomerAddressList().subscribe(new Observer<AddressGson>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                AddressGson error = new AddressGson();
                error.setResponseCode(XdConfig.RESPONSE_F);
                error.setResponseMsg(XdConfig.RESPONSE_MSG_F);
                data.postValue(error);
            }

            @Override
            public void onNext(AddressGson balanceGson) {
                data.postValue(balanceGson);
            }
        });
        return data;
    }

    public LiveData<AddressGson> saveAddress(Map<String, String> params) {
        MutableLiveData<AddressGson> data = new MutableLiveData<>();
        RetrofitUtils.getInstance().saveAddress(params).subscribe(new Observer<AddressGson>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                AddressGson error = new AddressGson();
                error.setResponseCode(XdConfig.RESPONSE_F);
                error.setResponseMsg(XdConfig.RESPONSE_MSG_F);
                data.postValue(error);
            }

            @Override
            public void onNext(AddressGson balanceGson) {
                data.postValue(balanceGson);
            }
        });
        return data;
    }

    public LiveData<AddressGson> updateAddress(Map<String, String> params) {
        MutableLiveData<AddressGson> data = new MutableLiveData<>();
        RetrofitUtils.getInstance().updateAddress(params).subscribe(new Observer<AddressGson>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                AddressGson error = new AddressGson();
                error.setResponseCode(XdConfig.RESPONSE_F);
                error.setResponseMsg(XdConfig.RESPONSE_MSG_F);
                data.postValue(error);
            }

            @Override
            public void onNext(AddressGson balanceGson) {
                data.postValue(balanceGson);
            }
        });
        return data;
    }

    public LiveData<BaseGson> joinShow(Map<String, String> params) {
        MutableLiveData<BaseGson> data = new MutableLiveData<>();
        RetrofitUtils.getInstance().joinShow(params).subscribe(new Observer<BaseGson>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                BaseGson error = new BaseGson();
                error.setResponseCode(XdConfig.RESPONSE_F);
                error.setResponseMsg(XdConfig.RESPONSE_MSG_F);
                data.postValue(error);
            }

            @Override
            public void onNext(BaseGson baseGson) {
                data.postValue(baseGson);
            }
        });
        return data;
    }
}
