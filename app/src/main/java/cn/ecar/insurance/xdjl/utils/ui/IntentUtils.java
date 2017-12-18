package cn.ecar.insurance.xdjl.utils.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

import cn.ecar.insurance.xdjl.R;

/**
 * Created by yx on 2017/7/25.
 * intent工具类,主要用于启动activity和回传intent
 */

public class IntentUtils {

    private Context mContext;
    private Intent mIntent;

    public static class Builder {
        private Context mContext;
        private Intent mIntent;

        public Builder(Context context) {
            this.mContext = context;
            mIntent = new Intent();
        }


        /**
         * 设置目标ui
         * @param targetActivity
         * @return
        */
        public Builder setTargetActivity(Class<? extends Activity> targetActivity) {
            mIntent.setClass(mContext, targetActivity);
            return this;
        }

        /**
         * 设置action
         * @param action
         * @return
         */
        public Builder setAction(String action) {
            mIntent.setAction(action);
            return this;
        }

        /**
         * 设置uri
         * @param data
         * @return
         */
        public Builder setData(Uri data) {
            mIntent.setData(data);
            return this;
        }

        /**
         * 设置falg
         * @param flag
         * @return
         */
        public Builder setFlag(int flag) {
            mIntent.addFlags(flag);
            return this;
        }

        /**
         * 一下是各类intent参数设置
         * @param key
         * @param value
         * @return
         */
        public Builder setBooleanExtra(String key, boolean value) {
            mIntent.putExtra(key, value);
            return this;
        }

        public Builder setByteExtra(String key, byte value) {
            mIntent.putExtra(key, value);
            return this;
        }

        public Builder setCharExtra(String key, char value) {
            mIntent.putExtra(key, value);
            return this;
        }

        public Builder setShortExtra(String key, short value) {
            mIntent.putExtra(key, value);
            return this;
        }

        public Builder setIntExtra(String key, int value) {
            mIntent.putExtra(key, value);
            return this;
        }

        public Builder setLongExtra(String key, long value) {
            mIntent.putExtra(key, value);
            return this;
        }

        public Builder setFloatExtra(String key, float value) {
            mIntent.putExtra(key, value);
            return this;
        }

        public Builder setDoubleExtra(String key, double value) {
            mIntent.putExtra(key, value);
            return this;
        }

        public Builder setStringExtra(String key, String value) {
            mIntent.putExtra(key, value);
            return this;
        }

        public Builder setCharSequenceExtra(String key, CharSequence value) {
            mIntent.putExtra(key, value);
            return this;
        }

        public Builder setParcelableExtra(String key, Parcelable value) {
            mIntent.putExtra(key, value);
            return this;
        }

        public Builder setSerializableExtra(String key, Serializable value) {
            mIntent.putExtra(key, value);
            return this;
        }

        public Builder setBooleanArrayExtra(String key, boolean[] value) {
            mIntent.putExtra(key, value);
            return this;
        }

        public Builder setByteArrayExtra(String key, byte[] value) {
            mIntent.putExtra(key, value);
            return this;
        }

        public Builder setCharArrayExtra(String key, char[] value) {
            mIntent.putExtra(key, value);
            return this;
        }

        public Builder setShortArrayExtra(String key, short[] value) {
            mIntent.putExtra(key, value);
            return this;
        }

        public Builder setIntArrayExtra(String key, int[] value) {
            mIntent.putExtra(key, value);
            return this;
        }

        public Builder setLongArrayExtra(String key, long[] value) {
            mIntent.putExtra(key, value);
            return this;
        }

        public Builder setFloatArrayExtra(String key, float[] value) {
            mIntent.putExtra(key, value);
            return this;
        }

        public Builder setDoubleArrayExtra(String key, double[] value) {
            mIntent.putExtra(key, value);
            return this;
        }

        public Builder setStringArrayExtra(String key, String[] value) {
            mIntent.putExtra(key, value);
            return this;
        }

        public Builder setStringArrayListExtra(String name, ArrayList<String> value) {
            mIntent.putExtra(name,value);
            return this;
        }
        public Builder setCharSequenceArrayExtra(String key, CharSequence[] value) {
            mIntent.putExtra(key, value);
            return this;
        }

        public Builder setBundleExtra(String key, Bundle value) {
            mIntent.putExtra(key, value);
            return this;
        }

        public IntentUtils build() {
            return new IntentUtils(this);
        }
    }

    public IntentUtils(Builder builder) {
        this.mContext = builder.mContext;
        this.mIntent = builder.mIntent;
    }

    /**
     * 打开新的activity
     */
    public void startActivity(boolean needAnim) {
        mContext.startActivity(mIntent);
        if (needAnim) {
            ((Activity) mContext).overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
        }
    }

    /**
     * 打开新的activity并关闭当前ui
     */
    public void startActivityWithFinishUi(boolean needAnim) {
        mContext.startActivity(mIntent);
        ((Activity) mContext).finish();
        if (needAnim) {
            ((Activity) mContext).overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
        }
    }

    /**
     * 打开有result返回的activity
     *
     * @param requestCode
     */
    public void startActivityForResult(int requestCode) {
        ((Activity) mContext).startActivityForResult(mIntent, requestCode);
        ((Activity) mContext).overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
    }

    /**
     * 设置relsult为ok的intent且关闭当前ui
     */
    public void setResultOkWithFinishUi() {
        ((Activity) mContext).setResult(-1, mIntent);
        ((Activity) mContext).finish();
        ((Activity) mContext).overridePendingTransition(R.anim.anim_enter2, R.anim.anim_exit2);

    }

    public void setResultWithFinishUi(int result) {
        ((Activity) mContext).setResult(result, mIntent);
        ((Activity) mContext).finish();
        ((Activity) mContext).overridePendingTransition(R.anim.anim_enter2, R.anim.anim_exit2);

    }

    public Intent getIntent() {
        return mIntent;
    }
}
