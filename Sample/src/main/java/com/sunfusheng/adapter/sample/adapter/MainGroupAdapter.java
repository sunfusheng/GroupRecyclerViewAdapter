package com.sunfusheng.adapter.sample.adapter;

import android.support.v7.widget.RecyclerView;

import com.sunfusheng.GroupViewHolder;
import com.sunfusheng.HeaderGroupRecyclerViewAdapter;
import com.sunfusheng.adapter.sample.R;
import com.sunfusheng.adapter.sample.util.DataSource;

/**
 * @author sunfusheng on 2018/2/2.
 */
public class MainGroupAdapter extends HeaderGroupRecyclerViewAdapter<DataSource.MainItemConfig> {

    public MainGroupAdapter(RecyclerView recyclerView, DataSource.MainItemConfig[][] items) {
        super(recyclerView, items);
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
    public void onBindHeaderViewHolder(GroupViewHolder holder, DataSource.MainItemConfig item, int groupPosition) {

    }

    @Override
    public void onBindChildViewHolder(GroupViewHolder holder, DataSource.MainItemConfig item, int groupPosition, int childPosition) {
        holder.setText(R.id.tv_title, item.titleId);
        holder.setVisible(R.id.divider, !isGroupLastItem(groupPosition, childPosition));
    }
}
