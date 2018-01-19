package cn.ecar.insurance.mvvm.model.user;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;


import cn.ecar.insurance.base.BaseEntity;
import cn.ecar.insurance.base.LoginEntity;
import cn.ecar.insurance.base.TokenEntity;
import cn.ecar.insurance.base.UserInfo;
import cn.ecar.insurance.mvvm.base.BaseModel;
import cn.ecar.insurance.net.RetrofitUtils;
import cn.ecar.insurance.rxevent.RxBus;
import cn.ecar.insurance.rxevent.RxCodeConstants;
import cn.ecar.insurance.utils.file.FileUtils;
import cn.ecar.insurance.utils.file.SpUtils;
import cn.ecar.insurance.utils.system.DataCleanUtils;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yx on 2017/8/18.
 * 用户信息model
 */

public class UserInfoModel extends BaseModel {

    private static volatile UserInfoModel instance;

    private boolean userLogin = false;

    public static UserInfoModel getInstance() {
        if (instance == null) {
            synchronized (UserInfoModel.class) {
                if (instance == null) {
                    instance = new UserInfoModel();
                }
            }
        }
        return instance;
    }

    private UserInfoModel() {
        super();
    }

    //设置用户登陆状态
    public void setUserLogin(boolean userLogin) {
        this.userLogin = userLogin;
    }

    //获取用户登录状态
    public boolean isUserLogin() {
        return userLogin;
    }

    //获取用户信息实体
    public UserInfo getUserInfo() {
        return DatabaseModel.getInstance().getUserInfo();
    }

//    //用户登录
//    public void userLogin(String name, String token, String photo, String renzheng, String company,
//                          String pingjia, String kefuName, String kefuQQ, String kefuMobile, String city, String businessCity,
//                          String CPS, String userName) {
//        DatabaseModel.getInstance().saveOrUpdateUserInfo(name, token, photo, renzheng, company,
//                pingjia, kefuName, kefuQQ, kefuMobile, city, businessCity,
//                CPS, userName,
//                success -> {
//                    setUserLogin(true);
//                    RxBus.getDefault().post(RxCodeConstants.JUMP_TYPE, RxCodeConstants.TYPE_USER_LOGIN);
//                    MobclickAgent.onProfileSignIn(name);//登录统计
//                });
//    }

    //更新用户信息
    public void updateUserinfo(String name, String token, String photo, String renzheng, String company,
                               String pingjia, String kefuName, String kefuQQ, String kefuMobile, String city, String businessCity,
                               String CPS, String userName) {
        DatabaseModel.getInstance().saveOrUpdateUserInfo(name, token, photo, renzheng, company,
                pingjia, kefuName, kefuQQ, kefuMobile, city, businessCity,
                CPS, userName,
                success -> RxBus.getDefault().post(RxCodeConstants.JUMP_TYPE, RxCodeConstants.TYPE_USERINFO_CHANGED));
    }


    //用户退出登录
    public void userLogout() {
//        MobclickAgent.onProfileSignOff();//退出登录统计
        DatabaseModel.getInstance().deleteUserInfo(rowsAffected -> {
            setUserLogin(false);
            // 清除数据缓存
            DataCleanUtils.cleanInternalCache();
            //这三个sp必须保存,不然帮助贷覆盖城市无法判断
            SpUtils.removeAllSp();
            RxBus.getDefault().post(RxCodeConstants.JUMP_TYPE, RxCodeConstants.TYPE_USER_LOGOUT);
        });
    }

    public String getPhoto() {
        return DatabaseModel.getInstance().getPhoto();
    }

    public String getKefuName() {
        return DatabaseModel.getInstance().getKefuName();
    }

    public String getKefuMobile() {
        return DatabaseModel.getInstance().getKefuMobile();
    }

    public String getKefuQQ() {
        return DatabaseModel.getInstance().getKefuQQ();
    }

    //获取用户姓名
    public String getUserName() {
        return DatabaseModel.getInstance().getUserName();
    }

    public void saveToken(String token) {
        DatabaseModel.getInstance().saveToken(token);
    }

    //获取用户手机号码
    public String getUserPhone() {
        return DatabaseModel.getInstance().getUserPhone();
    }

    //获取用户token
    public String getUserToken() {
        return DatabaseModel.getInstance().getUserToken();
    }

    public String getCompany() {
        return DatabaseModel.getInstance().getCompany();
    }

    public String getRenzheng() {
        return DatabaseModel.getInstance().getRenzheng();
    }

    public String getBusinessCity() {
        return DatabaseModel.getInstance().getBusinessCity();
    }

    //删除本地头像
    public void deleteLocalUserAvatar() {
        FileUtils.deleteAvata();
    }

//    //用户登录
//    public LiveData<LoginEntity> doLogin(String mobile, String type, String loginType, String password, String clientId, String shebeiId) {
//        MutableLiveData<LoginEntity> data = new MutableLiveData<>();
//        RetrofitUtils.getInstance().getNoTokenData(
//                RetrofitUtils.getInstance().getParamsMap(
//                        "d",
//                        RetrofitUtils.getInstance().toAesJsonString("mobile", mobile, loginType, password, "sbtype", "Android",
//                                "type", type, "deviceToken", "", "clientid", clientId, "shebeiid", shebeiId)))
//                .map(aesEntity -> {
//                    return GsonUtil.fromJson(RetrofitUtils.getInstance().decpytJsonString(aesEntity.getD()), new TypeToken<LoginEntity>() {
//                    });
//                })
//                .subscribe(new Subscriber<LoginEntity>() {
//                    @Override
//                    public void onStart() {
//                        super.onStart();
//                        showWaitDialog();
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                        hideWaitDialog();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        ToastUtils.showToast(e.getMessage());
//                        hideWaitDialog();
//                    }
//
//                    @Override
//                    public void onNext(LoginEntity loginEntity) {
//                        hideWaitDialog();
//                        Logger.wtf("返回结果 = " + loginEntity.toString());
//                        if (loginEntity.getResult().equals("suc")) {
//                            data.setValue(loginEntity);
//                            userLogin(loginEntity.getName(), loginEntity.getToken(), loginEntity.getPhoto(), loginEntity.getRenzheng(), loginEntity.getCompany(),
//                                    loginEntity.getPingjia(), loginEntity.getKefuName(), loginEntity.getKefuQQ(), loginEntity.getKefuMobile(), loginEntity.getCity(), loginEntity.getBusinessCity(),
//                                    loginEntity.getCPS(), loginEntity.getUserName());
//                        } else {
//                            handingResult(loginEntity.getResult(), loginEntity.getMsg());
//                        }
//                    }
//                });
//        return data;
//    }
//
//    //用户获取验证码
//    public Observable<BaseEntity> doYanzhengCodeRequest(String type, String phone) {
//        return RetrofitUtils.getInstance().getNoTokenData(RetrofitUtils.getInstance().getParamsMap(
//                "d", RetrofitUtils.getInstance().toAesJsonString("mobile", phone, "type", type)))
//                .map(aesEntity -> {
//                    Logger.wtf("aesEntity = " + RetrofitUtils.getInstance().decpytJsonString(aesEntity.getD()) + "    aesEntity = " + aesEntity.getD());
//                    return GsonUtil.fromJson(RetrofitUtils.getInstance().decpytJsonString(aesEntity.getD()), new TypeToken<BaseEntity>() {
//                    });
//                })
//                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
//    }
//
//    //用户忘记密码验证
//    public LiveData<BaseEntity> doYanzhengCodeVerification(String type, String phone, String step, String code) {
//        MutableLiveData<BaseEntity> data = new MutableLiveData<>();
//        RetrofitUtils.getInstance().getNoTokenData(RetrofitUtils.getInstance().getParamsMap(
//                "d",
//                RetrofitUtils.getInstance().toAesJsonString("type", type, "mobile", phone, step, code)))
//                .map(aesEntity -> {
//                    return GsonUtil.fromJson(RetrofitUtils.getInstance().decpytJsonString(aesEntity.getD()), new TypeToken<BaseEntity>() {
//                    });
//                })
//                .subscribe(new Subscriber<BaseEntity>() {
//                    @Override
//                    public void onStart() {
//                        super.onStart();
//                        showWaitDialog();
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                        hideWaitDialog();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        ToastUtils.showToast(e.getMessage());
//                        hideWaitDialog();
//                    }
//
//                    @Override
//                    public void onNext(BaseEntity baseEntity) {
//                        hideWaitDialog();
//                        if (baseEntity.getResult().equals("suc")) {
//                            data.setValue(baseEntity);
//                        } else {
//                            handingResult(baseEntity.getResult(), baseEntity.getMsg());
//                        }
//                    }
//                });
//        return data;
//    }
//
//    //用户注册第一步
//    public LiveData<TokenEntity> registerOne(String mobile, String mobileCode, String pwd) {
//        MutableLiveData<TokenEntity> data = new MutableLiveData<>();
//        RetrofitUtils.getInstance().getNoTokenData(RetrofitUtils.getInstance().getParamsMap(
//                "d",
//                RetrofitUtils.getInstance().toAesJsonString("type", "RegisterOne", "mobile", mobile, "mobileCode", mobileCode, "pwd", pwd)))
//                .map(aesEntity -> {
//                    return GsonUtil.fromJson(RetrofitUtils.getInstance().decpytJsonString(aesEntity.getD()), new TypeToken<TokenEntity>() {
//                    });
//                })
//                .subscribe(new Subscriber<TokenEntity>() {
//
//                    @Override
//                    public void onStart() {
//                        super.onStart();
//                        showWaitDialog();
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                        hideWaitDialog();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        hideWaitDialog();
//                        ToastUtils.showToast(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(TokenEntity tokenEntity) {
//                        if (tokenEntity.getResult().equals("suc")) {
//                            saveToken(tokenEntity.getToken());
//                            data.setValue(tokenEntity);
//                        } else {
//                            handingResult(tokenEntity.getResult(), tokenEntity.getMsg());
//                        }
//                    }
//                });
//        return data;
//    }
//
//    //注册第二步
//    public LiveData<BaseEntity> registerTwo(String trueName, String sex, String birth, String city, String company, String postion) {
//        MutableLiveData<BaseEntity> data = new MutableLiveData<>();
//        RetrofitUtils.getInstance().getNoTokenData(RetrofitUtils.getInstance().getParamsMap(
//                "d",
//                RetrofitUtils.getInstance().toAesJsonString(
//                        "token", getUserToken(), "type", "RegisterTwo", "trueName", trueName,
//                        "sex", sex, "birth", birth, "city", city, "company", company, "postion", postion)))
//                .map(aesEntity -> {
//                    return GsonUtil.fromJson(RetrofitUtils.getInstance().decpytJsonString(aesEntity.getD()), new TypeToken<BaseEntity>() {
//                    });
//                })
//                .subscribe(new Subscriber<BaseEntity>() {
//                    @Override
//                    public void onStart() {
//                        super.onStart();
//                        showWaitDialog();
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                        hideWaitDialog();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        ToastUtils.showToast(e.getMessage());
//                        hideWaitDialog();
//                    }
//
//                    @Override
//                    public void onNext(BaseEntity baseEntity) {
//                        hideWaitDialog();
//                        if (baseEntity.getResult().equals("suc")) {
//                            data.setValue(baseEntity);
//                        } else {
//                            handingResult(baseEntity.getResult(), baseEntity.getMsg());
//                        }
//                    }
//                });
//        return data;
//    }
//
//    //注册第二步 验证短信
//    public void sendDuanxin() {
//        String clientId = PushManager.getInstance().getClientid(XdAppContext.app().getmContext());
//        RetrofitUtils.getInstance().getNoTokenData(RetrofitUtils.getInstance().getParamsMap(
//                "d",
//                RetrofitUtils.getInstance().toAesJsonString("type", "sentRegisterSms",
//                        "token", getUserToken(),
//                        "clientID", clientId)))
//                .subscribe(new Subscriber<AesEntity>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable throwable) {
//
//                    }
//
//                    @Override
//                    public void onNext(AesEntity aesEntity) {
//
//                    }
//                });
//    }


}
