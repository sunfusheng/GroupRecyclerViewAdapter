package com.sunfusheng.adapter.sample.adapter;

import android.content.Context;

import com.sunfusheng.GroupRecyclerViewAdapter;
import com.sunfusheng.GroupViewHolder;
import com.sunfusheng.adapter.sample.R;

/**
 * @author sunfusheng on 2018/3/7.
 */
public class StickyGroupAdapter extends GroupRecyclerViewAdapter<String> {

    public StickyGroupAdapter(Context context, String[][] groups) {
        super(context, groups);
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
