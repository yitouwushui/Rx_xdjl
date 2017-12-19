package cn.ecar.insurance.mvvm.view.frag;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.databinding.FragmentHomeBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingFragment;
import cn.ecar.insurance.mvvm.viewmodel.custom.CustomViewModel;
import cn.ecar.insurance.mvvm.viewmodel.home.AdvertsViewModel;
import cn.ecar.insurance.widget.convenientbanner.ConvenientBanner;
import cn.ecar.insurance.widget.convenientbanner.holder.CBViewHolderCreator;
import cn.ecar.insurance.widget.convenientbanner.holder.Holder;


/**
 * @author yitouwushui
 */
public class HomeFragment extends BaseBindingFragment<FragmentHomeBinding> {


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

    }

    /**
     * 轮播banner图片holder类
     */
    public class BannerImageHolderView implements Holder<Drawable> {
        private AppCompatImageView mIvBanner;

        @Override
        public View createView(Context context) {

            mIvBanner = (AppCompatImageView) View.inflate(context, R.layout.item_convenient_banner, null);
            return mIvBanner;
        }

        @Override
        public void UpdateUI(Context context, int position, Drawable data) {
            mIvBanner.setImageDrawable(data);
        }
    }

    @Override
    protected void initData() {
        CustomViewModel customViewModel = ViewModelProviders.of(this).get(CustomViewModel.class);
        customViewModel.getBase();
        customViewModel.getBaiDu().observe(this, str -> {
            assert str != null;

        });
        AdvertsViewModel advertsViewModel = ViewModelProviders.of(this).get(AdvertsViewModel.class);
        advertsViewModel.getDrawable().observe(
                this,
                drawables -> mVB.banner.setPages(
                        () -> new BannerImageHolderView(),
                        drawables)
                        .setPageIndicator(new int[]{R.drawable.indicator_unchecked, R.drawable.indicator_checked})
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                        .startTurning(3000));
        mVB.btSign.setText("签到2");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void destroyView() {

    }

}
