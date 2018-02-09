package com.sunfusheng.adapter.sample.adapter;

import android.content.Context;

import com.sunfusheng.adapter.ExpandableGroupRecyclerViewAdapter;
import com.sunfusheng.adapter.GroupViewHolder;
import com.sunfusheng.adapter.sample.R;

import java.util.List;

/**
 * @author sunfusheng on 2018/2/9.
 */
public class ExpandCollapseGroupAdapter extends ExpandableGroupRecyclerViewAdapter<String> {

    public ExpandCollapseGroupAdapter(Context context) {
        super(context);
    }

    public ExpandCollapseGroupAdapter(Context context, String[][] groups) {
        super(context, groups);
    }

    public ExpandCollapseGroupAdapter(Context context, List<List<String>> groups) {
        super(context, groups);
    }

    @Override
    public boolean showHeader() {
        return true;
    }

    @Override
    public boolean showFooter() {
        return false;
    }

    @Override
    public int getHeaderLayoutId() {
        return R.layout.item_header_layout;
    }

    @Override
    public int getChildLayoutId() {
        return R.layout.item_child_layout;
    }

    @Override
    public int getFooterLayoutId() {
        return R.layout.item_footer_layout;
    }

    @Override
    public void onBindHeaderViewHolder(GroupViewHolder holder, String item, int groupPosition) {
        holder.setText(R.id.tv_header_title, item);
    }

    @Override
    public void onBindChildViewHolder(GroupViewHolder holder, String item, int groupPosition, int childPosition) {
        holder.setText(R.id.tv_child_title, item);
    }

    @Override
    public void onBindFooterViewHolder(GroupViewHolder holder, String item, int groupPosition) {
        holder.setText(R.id.tv_footer_title, item + "、footer");
    }
}
