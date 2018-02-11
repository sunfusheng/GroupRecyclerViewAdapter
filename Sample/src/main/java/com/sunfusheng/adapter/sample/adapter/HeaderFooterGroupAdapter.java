package com.sunfusheng.adapter.sample.adapter;

import android.content.Context;

import com.sunfusheng.adapter.GroupRecyclerViewAdapter;
import com.sunfusheng.adapter.GroupViewHolder;
import com.sunfusheng.adapter.sample.R;
import com.sunfusheng.adapter.sample.util.GroupData;

/**
 * @author sunfusheng on 2018/2/1.
 */
public class HeaderFooterGroupAdapter extends GroupRecyclerViewAdapter<String> {

    public HeaderFooterGroupAdapter(Context context, String[][] items) {
        super(context, items);
    }

    @Override
    public boolean showHeader() {
        return true;
    }

    @Override
    public boolean showFooter() {
        return true;
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
        return R.layout.item_footer_layout;
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
        holder.setText(R.id.tv_title, item + GroupData.FOOTER_SUFFIX);
    }
}
