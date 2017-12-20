package cn.ecar.insurance.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.absrecyclerview.CommonAdapter;
import cn.ecar.insurance.adapter.absrecyclerview.base.ViewHolder;
import cn.ecar.insurance.entity.Member;

/**
 * Created by ding on 2017/12/20.
 */

public class HomeMemberAdapter extends CommonAdapter<Member> {


    public HomeMemberAdapter(Context context, int layoutId, List<Member> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, Member member, int position) {
        holder.setImageDrawable(R.id.img_icon,member.getIcon());
        holder.setText(R.id.tv_name,member.getName());
        holder.setText(R.id.tv_content,member.getContent());
    }

}

