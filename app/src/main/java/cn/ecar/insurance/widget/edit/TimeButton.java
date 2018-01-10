package cn.ecar.insurance.widget.edit;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;

import java.util.Date;

import cn.ecar.insurance.config.XdConfig;

/**
 * Created by ding on 2016/9/26.
 */
public class TimeButton extends android.support.v7.widget.AppCompatButton implements View.OnClickListener {

    /**
     * 下次获取验证码的时间
     */
    private Long nextTime;
    private Context context;
    private boolean isTimeCount = false;
    private TimeCount timeCount;
    private Long time = 60000L;

    public TimeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;


        Date date = new Date();
        nextTime = context.getSharedPreferences(XdConfig.PARAM_NEXT_TIME, Context.MODE_PRIVATE).getLong(XdConfig.PARAM_NEXT_TIME, 0L);
        if (nextTime > date.getTime()) {
            timeCount = new TimeCount(nextTime - date.getTime(), 1000);
            timeCount.start();
            this.setEnabled(false);
        } else {
            timeCount = new TimeCount(time, 1000);
        }
    }

    private void setTime(Long time) {
        this.time = time;
    }

    public boolean isTimeCount() {
        return isTimeCount;
    }

    public void setTimeCountStart(boolean b) {
        if (isTimeCount = b) {
            timeCount = new TimeCount(time, 1000);
            timeCount.start();
            this.setEnabled(false);
            // 下次可重新获取时间
            context.getSharedPreferences(XdConfig.PARAM_NEXT_TIME, Context.MODE_PRIVATE).edit().putLong(XdConfig.PARAM_NEXT_TIME, System.currentTimeMillis() + 60000L).apply();
        } else {
            this.setText("重新获取验证码");
            this.setEnabled(true);
        }
    }

    /**
     * 启动计时
     */
    public void timeButtonStart() {
        timeCount.start();
        isTimeCount = true;

    }

    public void reset() {

    }

    class TimeCount extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            TimeButton.this.setText((millisUntilFinished / 1000) + "s重新获取");
        }

        @Override
        public void onFinish() {
            TimeButton.this.setText("重新获取验证码");
            TimeButton.this.setEnabled(true);
            // 计时取消
            isTimeCount = false;
        }
    }

    @Override
    public void onClick(View v) {

    }
}
