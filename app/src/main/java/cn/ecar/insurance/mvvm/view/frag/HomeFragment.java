package cn.ecar.insurance.mvvm.view.frag;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.HomeMemberAdapter;
import cn.ecar.insurance.databinding.FragmentHomeBinding;
import cn.ecar.insurance.entity.Member;
import cn.ecar.insurance.mvvm.base.BaseBindingFragment;
import cn.ecar.insurance.mvvm.viewmodel.home.HomeViewModel;
import cn.ecar.insurance.widget.convenientbanner.ConvenientBanner;
import cn.ecar.insurance.widget.convenientbanner.holder.Holder;


/**
 * @author yitouwushui
 */
public class HomeFragment extends BaseBindingFragment<FragmentHomeBinding> {

    private HomeViewModel homeViewModel;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        mVB.rcyViewMember.setLayoutManager(new LinearLayoutManager(
                mContext,LinearLayoutManager.HORIZONTAL,false
        ));
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
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.getAdvertsDrawable().observe(
                this,
                drawables -> mVB.banner.setPages(
                        () -> new BannerImageHolderView(),
                        drawables)
                        .setPageIndicator(new int[]{R.drawable.indicator_unchecked, R.drawable.indicator_checked})
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                        .startTurning(3000));
        homeViewModel.getNewsString().observe(
                this,
                members -> {
                    mVB.rcyViewMember
                            .setAdapter(
                                    new HomeMemberAdapter(mContext, R.layout.item_home_member_list, members)
                            );
                    mVB.rcyViewMember.getAdapter().notifyDataSetChanged();
                }
        );

        mVB.btSign.setText("签到2");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void destroyView() {

    }

}
