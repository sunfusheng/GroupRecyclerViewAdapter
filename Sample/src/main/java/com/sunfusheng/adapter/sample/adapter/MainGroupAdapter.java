package com.sunfusheng.adapter.sample.adapter;

import android.content.Context;

import com.sunfusheng.GroupViewHolder;
import com.sunfusheng.HeaderGroupRecyclerViewAdapter;
import com.sunfusheng.adapter.sample.R;
import com.sunfusheng.adapter.sample.util.GroupData;

import java.util.List;

/**
 * @author sunfusheng on 2018/2/2.
 */
public class MainGroupAdapter extends HeaderGroupRecyclerViewAdapter<GroupData.MainItemConfig> {

    public MainGroupAdapter(Context context) {
        super(context);
    }

    public MainGroupAdapter(Context context, List<List<GroupData.MainItemConfig>> items) {
        super(context, items);
    }

    public MainGroupAdapter(Context context, GroupData.MainItemConfig[][] items) {
        super(context, items);
    }

    @Override
    public int getHeaderLayoutId(int viewType) {
        return R.layout.divider_20dp;
    }

    @Override
    public int getChildLayoutId(int viewType) {
        return R.layout.item_child_layout;
    }

    @Override
    public void onBindHeaderViewHolder(GroupViewHolder holder, GroupData.MainItemConfig item, int groupPosition) {

    }

    @Override
    public void onBindChildViewHolder(GroupViewHolder holder, GroupData.MainItemConfig item, int groupPosition, int childPosition) {
        holder.setText(R.id.tv_title, item.titleId);
        holder.setVisible(R.id.divider, !isGroupLastItem(groupPosition, childPosition));
    }
}
