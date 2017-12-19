package cn.ecar.insurance.config;

import android.app.ActivityManager;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.module.GlideModule;

/**
 *
 * @author yx
 * @date 2017/10/9
 * Glidec初始化配置
 */

public class GlideApplyModule implements GlideModule{

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (am != null) {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            am.getMemoryInfo(memoryInfo);
            builder.setDecodeFormat(memoryInfo.lowMemory ? DecodeFormat.PREFER_RGB_565 : DecodeFormat.PREFER_ARGB_8888);
        }

    }

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {

    }
}
