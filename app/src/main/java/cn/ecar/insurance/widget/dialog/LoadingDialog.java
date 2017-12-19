package cn.ecar.insurance.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import cn.ecar.insurance.R;


/**
 * Created by lq on 2016-9-29.
 * 加载框
 */
public class LoadingDialog extends Dialog {

    private ImageView mIvLoading;

    private TextView mTvMessage;

    private Context mContext;

    private String mMessage;

    public LoadingDialog(Context context) {
        super(context, R.style.load_dialog);
        mContext = context;
    }

    protected LoadingDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public LoadingDialog(Context context, String message) {
        super(context, R.style.load_dialog);
        mContext = context;
        mMessage = message;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        View view = View.inflate(getContext(), R.layout.dialog_loading, null);
        setContentView(view);

        mIvLoading = (ImageView) view.findViewById(R.id.iv_loading);
        mTvMessage = (TextView) view.findViewById(R.id.tv_message);
        if (!TextUtils.isEmpty(mMessage)) {
            mTvMessage.setText(mMessage);
        }
        // 加载动画
        Animation loadAnimation = AnimationUtils.loadAnimation(mContext, R.anim.anim_rotate);
        // 使用ImageView显示动画
        mIvLoading.startAnimation(loadAnimation);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mIvLoading != null) {
            mIvLoading.clearAnimation();
        }
    }

    @Override
    public void setCancelable(boolean flag) {
        super.setCancelable(flag);
    }
}
