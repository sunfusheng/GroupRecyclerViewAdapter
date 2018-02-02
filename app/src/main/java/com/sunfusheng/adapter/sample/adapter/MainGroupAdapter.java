package com.sunfusheng.adapter.sample.adapter;

import android.content.Context;

import com.sunfusheng.adapter.GroupViewHolder;
import com.sunfusheng.adapter.HeaderGroupRecyclerViewAdapter;
import com.sunfusheng.adapter.sample.R;

import java.util.List;

/**
 * @author sunfusheng on 2018/2/2.
 */
public class MainGroupAdapter extends HeaderGroupRecyclerViewAdapter<String> {

    public MainGroupAdapter(Context context) {
        super(context);
    }

    public MainGroupAdapter(Context context, List<List<String>> items) {
        super(context, items);
    }

    public MainGroupAdapter(Context context, String[][] items) {
        super(context, items);
    }

    @Override
    public int getHeaderLayoutId() {
        return R.layout.divider_25dp;
    }

    @Override
    public int getChildLayoutId() {
        return R.layout.item_child_layout;
    }

    @Override
    public void onBindHeaderViewHolder(GroupViewHolder holder, String item, int groupPosition) {

    }

    @Override
    public void onBindChildViewHolder(GroupViewHolder holder, String item, int groupPosition, int childPosition) {
        holder.setText(R.id.tv_child_title, item);
    }
}
