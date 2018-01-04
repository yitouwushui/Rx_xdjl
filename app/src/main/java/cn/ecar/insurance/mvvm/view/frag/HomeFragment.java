package cn.ecar.insurance.mvvm.view.frag;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.HomeMemberAdapter;
import cn.ecar.insurance.databinding.FragmentHomeBinding;
import cn.ecar.insurance.entity.NoticeInfo;
import cn.ecar.insurance.mvvm.base.BaseBindingFragment;
import cn.ecar.insurance.mvvm.viewmodel.main.HomeViewModel;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.widget.convenientbanner.ConvenientBanner;
import cn.ecar.insurance.widget.convenientbanner.holder.Holder;


/**
 * @author yitouwushui
 */
public class HomeFragment extends BaseBindingFragment<FragmentHomeBinding> {

    private HomeViewModel mHomeViewModel;

    private List<NoticeInfo> mNotifyList;


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
                mContext, LinearLayoutManager.HORIZONTAL, false
        ));
    }

    private void initNoticeFiler(List<NoticeInfo> noticeInfos) {
        mVB.homepageNoticeVf.setInAnimation(getContext(), R.anim.anim_in_bottom_to_top);
        mVB.homepageNoticeVf.setOutAnimation(getContext(), R.anim.anim_out_bottom_to_top);
        mVB.homepageNoticeVf.setFlipInterval(3000);
        for (int i = 0; i < noticeInfos.size(); i++) {
            TextView tvNotice = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.item_notice, null);
            tvNotice.setText(noticeInfos.get(i).getTitle());
            tvNotice.setOnClickListener(view -> {
                ToastUtils.showToast("点击公告");
//                showNoticeAlert(noticeInfo);
//                netQuery.getNoticeDetail(String.valueOf(noticeInfo.getNoticeInfoId()));
            });
            mVB.homepageNoticeVf.addView(tvNotice);
        }
        mVB.homepageNoticeVf.startFlipping();
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
        public void updateUI(Context context, int position, Drawable data) {
            mIvBanner.setImageDrawable(data);
        }
    }

    @Override
    protected void initData() {
        mHomeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        /**
         * 广告轮播
         */
        mHomeViewModel.getAdvertsDrawable().observe(
                this,
                drawables -> mVB.banner.setPages(
                        () -> new BannerImageHolderView(),
                        drawables)
                        .setPageIndicator(new int[]{R.drawable.indicator_unchecked, R.drawable.indicator_checked})
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                        .startTurning(3000));
        /**
         * 会员咨询
         */
        mHomeViewModel.getNewsString().observe(
                this,
                members -> {
                    mVB.rcyViewMember
                            .setAdapter(
                                    new HomeMemberAdapter(mContext, R.layout.item_home_member_list, members)
                            );
                    mVB.rcyViewMember.getAdapter().notifyDataSetChanged();
                }
        );

        mHomeViewModel.getNoticeString().observe(
                this,
                noticeInfo -> {
                    initNoticeFiler(noticeInfo);
                }
        );

        mVB.btSign.setText("签到2");


    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        boolean notice = mVB.homepageNoticeVf.getChildCount() > 1;
        if (hidden) {
            if (notice) {
                mVB.homepageNoticeVf.stopFlipping();
            }
        } else {
            if (notice) {
                mVB.homepageNoticeVf.startFlipping();
            }
        }
        super.onHiddenChanged(hidden);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void destroyView() {

    }

}
