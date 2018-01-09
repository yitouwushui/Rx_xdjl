package cn.ecar.insurance.mvvm.view.frag;


import android.os.Build;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import cn.ecar.insurance.R;
import cn.ecar.insurance.databinding.FragmentMeBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingFragment;
import cn.ecar.insurance.utils.system.OtherUtil;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseBindingFragment<FragmentMeBinding> {


    public MeFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView() {
//        if (OtherUtil.getSDKInt() >= Build.VERSION_CODES.LOLLIPOP) {
//            // 5.0以上的系统，全屏式状态栏
//            int barHeight = OtherUtil.getStatusBarHeight(mContext);
//            OtherUtil.setBarColorAll(getActivity(), true);
////            ViewGroup.LayoutParams layoutParams = mVB.relaInfo.getLayoutParams();
//            mVB.relaInfo.setPadding(mVB.relaInfo.getPaddingLeft(), barHeight, mVB.relaInfo.getPaddingRight(), 0);
////            layoutParams.height += barHeight;
////            mVB.relaInfo.setLayoutParams(layoutParams);
//        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void destroyView() {

    }

}
