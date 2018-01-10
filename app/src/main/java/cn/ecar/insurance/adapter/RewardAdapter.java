package cn.ecar.insurance.adapter;

import android.content.Context;

import java.util.List;

import cn.ecar.insurance.R;
import cn.ecar.insurance.adapter.absrecyclerview.CommonAdapter;
import cn.ecar.insurance.adapter.absrecyclerview.base.ViewHolder;
import cn.ecar.insurance.entity.Member;

/**
 *
 * @author ding
 * @date 2018/1/10
 */

public class RewardAdapter extends CommonAdapter<Member> {

    public RewardAdapter(Context context, int layoutId, List<Member> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, Member member, int position) {
//        if (position > 2) {
            holder.setText(R.id.tv_no, (position + 1) + ".");
            holder.setImageDrawable(R.id.img_icon, member.getIcon());
            holder.setText(R.id.tv_name, member.getName());
            holder.setText(R.id.tv_content, member.getContent());
//        }
    }

}
