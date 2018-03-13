package cn.ecar.insurance.config;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.telephony.TelephonyManager;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tbruyelle.rxpermissions.RxPermissions;

import org.litepal.LitePal;

import java.util.List;
import java.util.UUID;

import cn.ecar.insurance.R;
import cn.ecar.insurance.mvvm.model.CityModel;
import cn.ecar.insurance.net.NetWorkApi;
import cn.ecar.insurance.net.NetServer;
import cn.ecar.insurance.service.AppInitService;
import cn.ecar.insurance.utils.file.SpUtils;
import cn.ecar.insurance.utils.city.CityChoiceUtils;

/**
 * @author yx
 * @date 2016/7/12
 * 启动Application
 */
public class XdAppContext extends Application {
    //    MultiDexApplication
    @SuppressLint("StaticFieldLeak")
    private static XdAppContext context;

    private Resources mResources;
    private NetServer mWcNetServer;
    private int mActivityRefCount = 0;//actvity的引用个数
    private Activity mStartActivity;//栈顶activty
    private String mTopActivityName;//栈顶activity名称
    private List<CityModel> mCityList;
    private List<String> mHotCityList;


    private String deviceId;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        init();
    }

    public static synchronized XdAppContext app() {
        return context;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);//分包
    }


    private void init() {
        mResources = getResources();
        mWcNetServer = NetWorkApi.getInstance().gradleRetrofit(this).create(NetServer.class);
        AppInitService.startService(this);//初始化三方库
        SpUtils.init(context); //初始化sp工具
        //设置头布局样式,全局有效
        SmartRefreshLayout.setDefaultRefreshHeaderCreater((context, layout) -> {
            //全局设置主题颜色
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            //指定为经典Header，默认是 贝塞尔雷达Header
            return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);
        });
        //设置脚布局样式,全局有效
        SmartRefreshLayout.setDefaultRefreshFooterCreater((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
        });
        LitePal.initialize(context); //LitePal初始化
        mCityList = CityChoiceUtils.getInstance().initCity(); //初始化城市列表
        mHotCityList = CityChoiceUtils.getInstance().initHotCity();
        initActivityLifecycle();
    }

    /**
     * 注册activity生命周期
     * 1.目前主要可以统计activity的引用个数,如果refcount=0,表示程序处于后台
     * 2.回调当前activity给basemodel，用于model逻辑处理
     */
    private void initActivityLifecycle() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {


            }

            @Override
            public void onActivityResumed(Activity activity) {
                mStartActivity = activity;
                mTopActivityName = activity.getComponentName().getClassName();
                mActivityRefCount++;
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                mActivityRefCount--;

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_UI_HIDDEN) {
            Glide.get(this).clearMemory();
        }
        Glide.get(this).trimMemory(level);

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(this).clearMemory();
    }


    public synchronized Context getContext() {
        return context;
    }

    public synchronized Resources getmResources() {
        Configuration configuration = new Configuration();
        configuration.setToDefaults();
        mResources.updateConfiguration(configuration, mResources.getDisplayMetrics());
        return mResources;
    }


    public synchronized NetServer getNetServer() {
        return mWcNetServer;
    }

    public synchronized int getActivityRefCount() {
        return mActivityRefCount;
    }

    public Activity getStartActivity() {
        return mStartActivity;
    }

    public String getTopActivityName() {
        return mTopActivityName;
    }

    /**
     * 获取App唯一标识
     */
    public String getAppId() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取设备imei号
     */
    @SuppressLint({"HardwareIds", "MissingPermission"})
    public String getDeviceImei() {
        new RxPermissions(getStartActivity()).request(Manifest.permission.READ_PHONE_STATE)
                .subscribe(grated -> {
                    if (grated) {
                        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                        deviceId = tm != null ? tm.getDeviceId() : "";
                    } else {
                        deviceId = "";
                    }

                });
        return deviceId;

    }

    public synchronized List<CityModel> getCityList() {
        return mCityList;
    }

    public synchronized List<String> getHotCityList() {
        return mHotCityList;
    }


    public Context getmContext() {
        return context;
    }
}
