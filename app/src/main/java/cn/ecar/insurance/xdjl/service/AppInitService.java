package cn.ecar.insurance.xdjl.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;


import com.imnjh.imagepicker.PickerConfig;
import com.imnjh.imagepicker.SImagePicker;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;


import cn.ecar.insurance.xdjl.BuildConfig;
import cn.ecar.insurance.xdjl.R;
import cn.ecar.insurance.xdjl.widget.imagepicker.GlideImageLoader;

/**
 * Created by yx on 2016/11/10.
 * app启动intentService,
 * 用于处理耗时操作,加快app启动速度
 */

public class AppInitService extends IntentService {

    private static final String ACTION_INIT_WHEN_APP_CREATE = "service.action.INIT";
    private static final String DEFAULT_TAG = "DOTA_FANS";

    public AppInitService() {
        super("AppInitService");
    }

    public static void startService(Context context) {
        Intent intent = new Intent(context, AppInitService.class);
        intent.setAction(ACTION_INIT_WHEN_APP_CREATE);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            if (ACTION_INIT_WHEN_APP_CREATE.equals(action)) {
                hanldeInit();
            }
        }

    }

    private void hanldeInit() {
        // TODO: 2017/11/9
        Logger.init(DEFAULT_TAG).logLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE)
                .hideThreadInfo().methodCount(0);

        SImagePicker.init(new PickerConfig.Builder().setAppContext(this.getApplicationContext())
                .setImageLoader(new GlideImageLoader())
                .setToolbaseColor(getResources().getColor(R.color.colorPrimaryDark)).build());

    }

}