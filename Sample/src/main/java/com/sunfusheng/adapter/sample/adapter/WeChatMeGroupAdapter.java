package com.sunfusheng.adapter.sample.adapter;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.sunfusheng.GroupViewHolder;
import com.sunfusheng.HeaderGroupRecyclerViewAdapter;
import com.sunfusheng.adapter.sample.R;
import com.sunfusheng.adapter.sample.util.DataSource;

/**
 * @author sunfusheng on 2018/2/3.
 */
public class WeChatMeGroupAdapter extends HeaderGroupRecyclerViewAdapter<DataSource.WeChatItemConfig> {

    public WeChatMeGroupAdapter(RecyclerView recyclerView, DataSource.WeChatItemConfig[][] items) {
        super(recyclerView, items);
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
        if (viewType == DataSource.VIEW_TYPE_WECHAT_PROFILE) {
            return R.layout.item_wechat_me_profile;
        }
        return R.layout.item_wechat_me_common;
    }

    @Override
    public void onBindHeaderViewHolder(GroupViewHolder holder, DataSource.WeChatItemConfig item, int groupPosition) {

    }

    @Override
    public void onBindChildViewHolder(GroupViewHolder holder, DataSource.WeChatItemConfig item, int groupPosition, int childPosition) {
        int viewType = getChildItemViewType(groupPosition, childPosition);

        if (viewType == DataSource.VIEW_TYPE_WECHAT_PROFILE) {
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
