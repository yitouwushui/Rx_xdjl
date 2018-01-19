package cn.ecar.insurance.mvvm.viewmodel.user;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import cn.ecar.insurance.base.BaseEntity;
import cn.ecar.insurance.base.LoginEntity;
import cn.ecar.insurance.base.TokenEntity;
import cn.ecar.insurance.base.UserInfo;
import cn.ecar.insurance.mvvm.model.user.UserInfoModel;
import rx.Observable;

/**
 * Created by yx on 2017/8/18.
 * 用户信息 viewmodel
 */

public class UserInfoViewModel extends ViewModel {

    private UserInfoModel mUserInfoModel;

    public UserInfoViewModel() {
        mUserInfoModel = UserInfoModel.getInstance();
    }

    //设置用户登陆状态
    public void setUserLogin(boolean userLogin) {
        mUserInfoModel.setUserLogin(userLogin);
    }

    //获取用户登录状态
    public boolean isUserLogin() {
        return mUserInfoModel.isUserLogin();
    }

    //获取用户信息实体
    public UserInfo getUserInfo() {
        return mUserInfoModel.getUserInfo();
    }

    //用户登录
    public void userLogin(String name, String token, String photo, String renzheng, String company,
                          String pingjia, String kefuName, String kefuQQ, String kefuMobile, String city, String businessCity,
                          String CPS, String userName) {
//        mUserInfoModel.userLogin(name, token, photo, renzheng, company,
//                pingjia, kefuName, kefuQQ, kefuMobile, city, businessCity,
//                CPS, userName);
    }

    //更新用户信息
    public void updateUserinfo(String name, String token, String photo, String renzheng, String company,
                               String pingjia, String kefuName, String kefuQQ, String kefuMobile, String city, String businessCity,
                               String CPS, String userName) {
        mUserInfoModel.updateUserinfo(name, token, photo, renzheng, company,
                pingjia, kefuName, kefuQQ, kefuMobile, city, businessCity,
                CPS, userName);
    }

    public String getPhoto() {
        return mUserInfoModel.getPhoto();
    }

    public String getKefuName() {
        return mUserInfoModel.getKefuName();
    }

    public String getKefuMobile() {
        return mUserInfoModel.getKefuMobile();
    }

    public String getKefuQQ() {
        return mUserInfoModel.getKefuQQ();
    }

    //获取用户姓名
    public String getUserName() {
        return mUserInfoModel.getUserName();
    }

    //获取用户手机号码
    public String getUserPhone() {
        return mUserInfoModel.getUserPhone();
    }

    //获取用户token
    public String getUserToken() {
        return mUserInfoModel.getUserToken();
    }

    public String getCompany() {
        return mUserInfoModel.getCompany();
    }

    public String getRenzheng() {
        return mUserInfoModel.getRenzheng();
    }

    public String getBusinessCity() {
        return mUserInfoModel.getBusinessCity();
    }

    //删除本地头像
    public void deleteLocalUserAbatar() {
        mUserInfoModel.deleteLocalUserAvatar();
    }

//    public LiveData<LoginEntity> doLogin(String mobile, String type, String loginType, String password, String clientId, String shebeiId) {
//        return mUserInfoModel.doLogin(mobile, type, loginType, password, clientId, shebeiId);
//    }
//
//    //获取验证码
//    public Observable<BaseEntity> doYanzhengCodeRequest(String type, String phone) {
//        return mUserInfoModel.doYanzhengCodeRequest(type, phone);
//    }
//
//    //忘记密码 验证码验证
//    public LiveData<BaseEntity> doYanzhengCodeVerification(String type, String phone, String step, String code) {
//        return mUserInfoModel.doYanzhengCodeVerification(type, phone, step, code);
//    }
//
//    //用户注册第一步
//    public LiveData<TokenEntity> registerOne(String mobile, String mobileCode, String pwd) {
//        return mUserInfoModel.registerOne(mobile, mobileCode, pwd);
//    }
//
//    //用户注册第二步
//    public LiveData<BaseEntity> registerTwo(String trueName, String sex, String birth, String city, String company, String postion) {
//        return mUserInfoModel.registerTwo(trueName, sex, birth, city, company, postion);
//    }
//
//    //用户注册第三步
//    public LiveData<LoginEntity> registerThree(String bussisslimts, String workyear, String spcworkyear, String QQ, String email, String weixin) {
//        return mUserInfoModel.registerThree(bussisslimts, workyear, spcworkyear, QQ, email, weixin);
//    }
//
//    public void sendDuanxin() {
//        mUserInfoModel.sendDuanxin();
//    }
}
