package cn.ecar.insurance.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.abslistview.CommonAdapter;
import cn.ecar.insurance.utils.file.SpUtils;
import cn.ecar.insurance.utils.system.OtherUtil;


/**
 * @author dzx
 * @date 2016-9-29
 * 选择对话框
 */
public class SelectDialog<T> extends Dialog {

    private MaxListView mListView;

    private TextView mTvHead;

    private boolean mShowHead;

    private Context mContext;

    private CommonAdapter<T> mCommonAdapter;

    private AdapterView.OnItemClickListener mSelectItemClickListener;

    public SelectDialog(Context context) {
        super(context, R.style.select_dialog);
        mContext = context;
    }

    protected SelectDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public SelectDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public SelectDialog(Context context, CommonAdapter<T> commonAdapter, AdapterView.OnItemClickListener onSelectItemClickListener) {
        super(context, R.style.select_dialog);
        mContext = context;
        this.mCommonAdapter = commonAdapter;
        mSelectItemClickListener = onSelectItemClickListener;
    }

    public SelectDialog(Context context, CommonAdapter<T> commonAdapter) {
        super(context, R.style.select_dialog);
        mContext = context;
        this.mCommonAdapter = commonAdapter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        View view = View.inflate(getContext(), R.layout.dialog_select, null);
        mListView = view.findViewById(R.id.select_list);
        mListView.setListViewHeight((int) OtherUtil.getScreenHeight(mContext) / 2);
        mTvHead = view.findViewById(R.id.select_head);
        setContentView(view);
        setOnSelectItemClickListener(mSelectItemClickListener);
        setContentAdapter(mCommonAdapter);

    }

    public SelectDialog showTitleHead(boolean isShow) {
        mShowHead = isShow;
        if (mTvHead != null) {
            mTvHead.setVisibility(mShowHead ? View.VISIBLE : View.GONE);
        }
        return this;
    }


    public SelectDialog setContentAdapter(CommonAdapter<T> contentAdapter) {
        mCommonAdapter = contentAdapter;
        if (mListView != null) {
            mListView.setAdapter(contentAdapter);
        }
        return this;
    }

    public SelectDialog setOnSelectItemClickListener(AdapterView.OnItemClickListener onSelectItemClickListener) {
        mSelectItemClickListener = onSelectItemClickListener;
        if (mListView != null) {
            mListView.setOnItemClickListener(onSelectItemClickListener);
        }
        return this;
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    public void setCancelable(boolean flag) {
        super.setCancelable(flag);
    }

}
