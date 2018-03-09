package com.sunfusheng.adapter.sample.adapter;

import android.support.v7.widget.RecyclerView;

import com.sunfusheng.GroupViewHolder;
import com.sunfusheng.StickyGroupRecyclerViewAdapter;
import com.sunfusheng.adapter.sample.R;

/**
 * @author sunfusheng on 2018/3/7.
 */
public class StickyGroupAdapter extends StickyGroupRecyclerViewAdapter<String> {

    public StickyGroupAdapter(RecyclerView recyclerView, String[][] groups) {
        super(recyclerView, groups);
    }

    @Override
    public boolean showFooter() {
        return false;
    }

    @Override
    public int getHeaderLayoutId(int viewType) {
        return R.layout.item_header_layout;
    }

    @Override
    public int getChildLayoutId(int viewType) {
        return R.layout.item_child_layout;
    }

    @Override
    public int getFooterLayoutId(int viewType) {
        return 0;
    }

    @Override
    public void onBindHeaderViewHolder(GroupViewHolder holder, String item, int groupPosition) {
        holder.setText(R.id.tv_title, item);
    }

    @Override
    public void onBindChildViewHolder(GroupViewHolder holder, String item, int groupPosition, int childPosition) {
        holder.setText(R.id.tv_title, item);
    }

    @Override
    public void onBindFooterViewHolder(GroupViewHolder holder, String item, int groupPosition) {

    }

}
