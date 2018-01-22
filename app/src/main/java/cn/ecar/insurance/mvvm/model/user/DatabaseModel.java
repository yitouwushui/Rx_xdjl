package cn.ecar.insurance.mvvm.model.user;

import org.litepal.crud.DataSupport;
import org.litepal.crud.callback.SaveCallback;
import org.litepal.crud.callback.UpdateOrDeleteCallback;

import cn.ecar.insurance.dao.base.UserInfo;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.mvvm.base.BaseModel;
import cn.ecar.insurance.utils.file.SpUtils;

/**
 * Created by yx on 2017/8/18.
 * 数据库 model 目前使用litepal
 */

public class DatabaseModel extends BaseModel {

    private static volatile DatabaseModel instance;

    public static DatabaseModel getInstance() {
        if (instance == null) {
            synchronized (DatabaseModel.class) {
                if (instance == null) {
                    instance = new DatabaseModel();
                }
            }
        }
        return instance;
    }

    private DatabaseModel() {
        super();
    }

    //获取用户信息实体
    public UserInfo getUserInfo() {
        return DataSupport.findFirst(UserInfo.class);
    }

    //保存用户数据
    public void saveOrUpdateUserInfo(String name,String token,String photo,String renzheng,String company,
                                     String pingjia,String kefuName,String kefuQQ,String kefuMobile,String city,String businessCity,
                                     String CPS,String userName, SaveCallback callback) {

        UserInfo userInfo = DataSupport.findFirst(UserInfo.class);
        if (userInfo == null) {
            userInfo = new UserInfo();
        }
        userInfo.setName(name);
        userInfo.setPhoto(photo);
        userInfo.setRenzheng(renzheng);
        userInfo.setCompany(company);
        userInfo.setPingjia(pingjia);
        userInfo.setKefuName(kefuName);
        userInfo.setKefuQQ(kefuQQ);
        userInfo.setKefuMobile(kefuMobile);
        userInfo.setCity(city);
        userInfo.setBusinessCity(businessCity);
        userInfo.setCPS(CPS);
        userInfo.setUserName(userName);
        SpUtils.putString(XdConfig.Token,token);
        SpUtils.putString(XdConfig.SP_CURRENT,name);
        userInfo.saveOrUpdateAsync().listen(callback);

    }

    //删除用户信息
    public void deleteUserInfo(UpdateOrDeleteCallback callback) {
        UserInfo userInfo = DataSupport.findFirst(UserInfo.class);
        userInfo.deleteAsync().listen(callback);
    }

    public String getPhoto() {
        UserInfo userInfo = DataSupport.findFirst(UserInfo.class);
        return userInfo.getPhoto();
    }

    public String getKefuName() {
        UserInfo userInfo = DataSupport.findFirst(UserInfo.class);
        return userInfo.getKefuName();
    }

    public String getKefuMobile() {
        UserInfo userInfo = DataSupport.findFirst(UserInfo.class);
        return userInfo.getKefuMobile();
    }

    public String getKefuQQ() {
        UserInfo userInfo = DataSupport.findFirst(UserInfo.class);
        return userInfo.getKefuQQ();
    }

    //获取用户姓名
    public String getUserName() {
        UserInfo userInfo = DataSupport.findFirst(UserInfo.class);
        return userInfo.getUserName();
    }

    //获取用户手机号码
    public String getUserPhone() {
        UserInfo userInfo = DataSupport.findFirst(UserInfo.class);
        return userInfo.getName();
    }

    //获取用户token
    public String getUserToken() {
        return SpUtils.getString(XdConfig.Token);
    }

    public void saveToken(String token) {
        SpUtils.putString(XdConfig.Token,XdConfig.Token);
    }

    public String getCompany() {
        UserInfo userInfo = DataSupport.findFirst(UserInfo.class);
        return userInfo.getCompany();
    }

    public String getRenzheng() {
        UserInfo userInfo = DataSupport.findFirst(UserInfo.class);
        return userInfo.getRenzheng();
    }

    public String getBusinessCity() {
        UserInfo userInfo = DataSupport.findFirst(UserInfo.class);
        return userInfo.getBusinessCity();
    }

//    //获取对应pid的payload实体
//    public LiveData<GPushPayloadBean> getFirstPidBean(int payloadId) {
//        MutableLiveData<GPushPayloadBean> data = new MutableLiveData<>();
//        GPushPayloadBean pidBean = DataSupport.where("pid = ?", payloadId + "").findFirst(GPushPayloadBean.class);
//        data.setValue(pidBean);
//        return data;
//    }
//
//    //获取未点击的payload实体消息列表
//    public LiveData<List<GPushPayloadBean>> getNoClickPidList() {
//        MutableLiveData<List<GPushPayloadBean>> data = new MutableLiveData<>();
//        List<GPushPayloadBean> beanList = DataSupport.where("isclick = ?", "0").find(GPushPayloadBean.class);
//        data.setValue(beanList);
//        return data;
//    }
//
//    //更新个推角标状态
//    public void updateGpushCount(int payloadId) {
//        GPushPayloadBean pidBean = getFirstPidBean(payloadId).getValue();
//        if (pidBean != null) {
//            pidBean.setIsclick("1");
//            pidBean.save();
//            updateLaunchIcon();
//            RxBus.getDefault().post(RxCodeConstants.JUMP_TYPE, RxCodeConstants.TYPE_GETUI_CLICK_NOTIFICATION);
//        }
//
//    }
//
//    //更新luncher角标
//    public void updateLaunchIcon() {
//        List<GPushPayloadBean> beanList = getNoClickPidList().getValue();
//        assert beanList != null;
//        ShortcutBadger.applyCount(getAppContext(), beanList.size());
//    }
//
//    //获取所有个推实体集合
//    public LiveData<List<GPushPayloadBean>> getAllGpushBeanList() {
//        MutableLiveData<List<GPushPayloadBean>> data = new MutableLiveData<>();
//        List<GPushPayloadBean> allList = DataSupport.findAll(GPushPayloadBean.class);
//        data.setValue(allList);
//        return data;
//    }
//
//    //删除单个个推实体
//    public void deleteGpushBean(int pid) {
//        DataSupport.deleteAll(GPushPayloadBean.class, "pid=?", pid + "");
//    }

}
