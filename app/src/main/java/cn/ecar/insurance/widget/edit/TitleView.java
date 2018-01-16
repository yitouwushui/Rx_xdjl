package cn.ecar.insurance.widget.edit;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.ecar.insurance.R;


/**
 * 自定义标题栏
 *
 * @author ding
 * @date 2016/9/4
 */
public class TitleView extends RelativeLayout {

    private TextView tvTitle;
    private ImageView imgBack;

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_title, this);
        imgBack = findViewById(R.id.img_back);
        tvTitle = findViewById(R.id.tv_title);
        imgBack.setOnClickListener(v -> ((Activity) getContext()).finish());
    }

    /**
     * 修改标题
     *
     * @param title 标题，null为隐藏
     */
    public void setTitle(String title) {
        if (title != null) {
            tvTitle.setText(title);
        } else {
            tvTitle.setVisibility(GONE);
        }
    }

    public void setTitleColor(int color) {
        tvTitle.setTextColor(color);
    }

    public ImageView getImgBack() {
        return imgBack;
    }

    public void setImgBack(ImageView imgBack) {
        this.imgBack = imgBack;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(TextView tvTitle) {
        this.tvTitle = tvTitle;
    }
}
