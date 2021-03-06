package cn.ecar.insurance.mvvm.view.frag;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.recycler.HomeMemberAdapter;
import cn.ecar.insurance.adapter.recycler.RewardAdapter;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.Information;
import cn.ecar.insurance.dao.bean.Message;
import cn.ecar.insurance.databinding.FragmentHomeBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingFragment;
import cn.ecar.insurance.mvvm.view.act.insurance.InsureActivity1;
import cn.ecar.insurance.mvvm.view.act.main.MainActivity;
import cn.ecar.insurance.mvvm.view.act.main.SchoolActivity;
import cn.ecar.insurance.mvvm.viewmodel.main.HomeViewModel;
import cn.ecar.insurance.utils.file.SpUtils;
import cn.ecar.insurance.utils.ui.IntentUtils;
import cn.ecar.insurance.utils.ui.TimeUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;
import cn.ecar.insurance.utils.ui.rxui.OnViewClick;
import cn.ecar.insurance.utils.ui.rxui.RxViewUtils;
import cn.ecar.insurance.widget.convenientbanner.ConvenientBanner;
import cn.ecar.insurance.widget.convenientbanner.holder.Holder;


/**
 * @author yitouwushui
 */
public class HomeFragment extends BaseBindingFragment<FragmentHomeBinding> implements OnViewClick {

    private HomeViewModel mHomeViewModel;
    private String mShareImagePath;
//    private List<NoticeInfo> mNotifyList;

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
        mHomeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

    }

    /**
     * 初始化通知轮播组件
     *
     * @param informationList
     */
    private void initNoticeFiler(List<Information> informationList) {
        mVB.homepageNoticeVf.setInAnimation(getContext(), R.anim.anim_in_bottom_to_top);
        mVB.homepageNoticeVf.setOutAnimation(getContext(), R.anim.anim_out_bottom_to_top);
        mVB.homepageNoticeVf.setFlipInterval(3000);
        for (int i = 0; i < informationList.size(); i++) {
            TextView tvNotice = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.item_notice, null);
            Information information = informationList.get(i);
            tvNotice.setText(information.getTitle());
            tvNotice.setOnClickListener(view -> {
                ToastUtils.showToast("通知id;" + information.getInfoId());
            });
            mVB.homepageNoticeVf.addView(tvNotice);
        }
        mVB.homepageNoticeVf.startFlipping();
    }

    /**
     * 初始化分享消息轮播组件
     *
     * @param messageList
     */
    private void initShareFiler(List<Message> messageList) {
        mVB.homepageShareVf.setInAnimation(getContext(), R.anim.anim_in_bottom_to_top);
        mVB.homepageShareVf.setOutAnimation(getContext(), R.anim.anim_out_bottom_to_top);
        mVB.homepageShareVf.setFlipInterval(3000);
        for (int i = 0; i < messageList.size(); i++) {
            TextView tvNotice = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.item_notice_white, null);
            Message message = messageList.get(i);
            tvNotice.setText(message.getContent());
            mVB.homepageShareVf.addView(tvNotice);
        }
        mVB.homepageShareVf.startFlipping();
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
        /**
         * 广告轮播
         */
        mHomeViewModel.getAdvertsDrawable().observe(
                this,
                drawables -> {
                    mVB.banner.setPages(
                            () -> new BannerImageHolderView(),
                            drawables)
                            .setPageIndicator(new int[]{R.drawable.indicator_unchecked, R.drawable.indicator_checked})
                            .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                            .startTurning(3000);

                });

        /**
         * 明星会员资讯
         */
        mHomeViewModel.getCustomerShowList().observe(
                this,
                showMembers -> {
                    mVB.rcyViewMember.setAdapter(new HomeMemberAdapter(mContext, R.layout.item_home_member_list, showMembers));
                }
        );
        mHomeViewModel.getShareMessageList().observe(
                this,
                messages -> {
                    initShareFiler(messages);
                }
        );
        mHomeViewModel.getNoticeString().observe(
                this,
                informationList -> {
                    initNoticeFiler(informationList);
                }
        );
        String signData = SpUtils.getString(XdConfig.SIGN_IN);
        if (!TimeUtils.getStringByDate(System.currentTimeMillis()).equals(signData)) {
            mHomeViewModel.judgeCustomerIsSignToday().observe(this, signInGson -> {
                if (signInGson != null && signInGson.getIsSign() == 0) {
                    mVB.btSign.setText("已签到");
                    mVB.btSign.setEnabled(false);
                }
            });
        } else {
            mVB.btSign.setText("已签到");
            mVB.btSign.setEnabled(false);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        changeFlipping(hidden, mVB.homepageNoticeVf);
        changeFlipping(hidden, mVB.homepageShareVf);
        super.onHiddenChanged(hidden);
    }

    /**
     * 改变轮播状态
     *
     * @param hidden
     * @param viewFlipper 轮播组件
     */
    private void changeFlipping(boolean hidden, ViewFlipper viewFlipper) {
        boolean notice = viewFlipper.getChildCount() >= 1;
        if (notice) {
            if (hidden) {
                viewFlipper.stopFlipping();
            } else {
                viewFlipper.startFlipping();
            }
        }
    }

    @Override
    protected void initEvent() {
        RxViewUtils.onViewClick(mVB.btInsurance, 1, this);
        RxViewUtils.onViewClick(mVB.btService, 1, this);
        RxViewUtils.onViewClick(mVB.btShare, 1, this);
        RxViewUtils.onViewClick(mVB.btSign, 1, this);
        RxViewUtils.onViewClick(mVB.btStudy, 1, this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.bt_insurance:
                new IntentUtils.Builder(mContext)
                        .setTargetActivity(InsureActivity1.class)
                        .build().startActivity(true);
                break;

            case R.id.bt_service:

                break;

            case R.id.bt_share:
                if (mShareImagePath == null || "".equals(mShareImagePath)) {
                    mShareImagePath = SpUtils.getString(XdConfig.SHARE_IMAGE_PATH);
                }
                if (mShareImagePath == null || "".equals(mShareImagePath)) {
                    ToastUtils.showToast("分享信息为空，请重新登录");
                    return;
                }
                new ShareAction(getActivity())
                        .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                        .withMedia(new UMImage(mContext, mShareImagePath))
                        .open();
                break;

            case R.id.bt_sign:
                mHomeViewModel.customerSignToday().observe(this, signInGson -> {
                    if (signInGson != null && "0".equals(signInGson.getSignStatus())) {
                        ToastUtils.showToast("签到成功");
                        mVB.btSign.setEnabled(false);
                        mVB.btSign.setText("已签到");
                        SpUtils.putData(XdConfig.SIGN_IN, TimeUtils.getStringByDate(System.currentTimeMillis()));
                    }
                });
                break;

            case R.id.bt_study:
                new IntentUtils.Builder(mContext)
                        .setTargetActivity(SchoolActivity.class)
                        .build()
                        .startActivity(true);
                break;
            default:
        }
    }

    @Override
    protected void destroyView() {

    }

}
