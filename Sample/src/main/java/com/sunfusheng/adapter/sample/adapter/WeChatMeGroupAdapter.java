package com.sunfusheng.adapter.sample.adapter;

import android.content.Context;
import android.os.Build;
import android.widget.TextView;

import com.sunfusheng.GroupViewHolder;
import com.sunfusheng.HeaderGroupRecyclerViewAdapter;
import com.sunfusheng.adapter.sample.R;
import com.sunfusheng.adapter.sample.util.GroupData;

import java.util.List;

/**
 * @author sunfusheng on 2018/2/3.
 */
public class WeChatMeGroupAdapter extends HeaderGroupRecyclerViewAdapter<GroupData.WeChatMeItemConfig> {

    public WeChatMeGroupAdapter(Context context) {
        super(context);
    }

    public WeChatMeGroupAdapter(Context context, List<List<GroupData.WeChatMeItemConfig>> items) {
        super(context, items);
    }

    public WeChatMeGroupAdapter(Context context, GroupData.WeChatMeItemConfig[][] items) {
        super(context, items);
    }

    @Override
    public int getChildItemViewType(int groupPosition, int childPosition) {
        return getItem(groupPosition, childPosition).viewType;
    }

    @Override
    public int getHeaderLayoutId(int viewType) {
        return R.layout.divider_20dp;
    }

    @Override
    public int getChildLayoutId(int viewType) {
        if (viewType == GroupData.VIEW_TYPE_WECHAT_ME_PROFILE) {
            return R.layout.item_wechat_me_profile;
        }
        return R.layout.item_wechat_me_common;
    }

    @Override
    public void onBindHeaderViewHolder(GroupViewHolder holder, GroupData.WeChatMeItemConfig item, int groupPosition) {

    }

    @Override
    public void onBindChildViewHolder(GroupViewHolder holder, GroupData.WeChatMeItemConfig item, int groupPosition, int childPosition) {
        int viewType = getChildItemViewType(groupPosition, childPosition);

        if (viewType == GroupData.VIEW_TYPE_WECHAT_ME_PROFILE) {
            TextView tvName = holder.get(R.id.tv_wechat_name);
            tvName.setText(item.titleId);
            holder.setImageResource(R.id.iv_avatar, R.mipmap.ic_me_avatar);
        } else {
            TextView tvTitle = holder.get(R.id.tv_title);
            tvTitle.setText(item.titleId);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                tvTitle.setCompoundDrawablesRelativeWithIntrinsicBounds(item.imgId, 0, 0, 0);
            } else {
                tvTitle.setCompoundDrawables(context.getResources().getDrawable(item.imgId), null, null, null);
            }
        }

        holder.setVisible(R.id.divider, !isGroupLastItem(groupPosition, childPosition));
    }

}
