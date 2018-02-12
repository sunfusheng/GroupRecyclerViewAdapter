package com.sunfusheng.adapter.sample.adapter;

import android.content.Context;

import com.sunfusheng.GroupViewHolder;
import com.sunfusheng.HeaderGroupRecyclerViewAdapter;
import com.sunfusheng.adapter.sample.R;

import java.util.List;

/**
 * @author sunfusheng on 2018/2/3.
 */
public class HeaderGroupAdapter extends HeaderGroupRecyclerViewAdapter<String> {

    public HeaderGroupAdapter(Context context) {
        super(context);
    }

    public HeaderGroupAdapter(Context context, List<List<String>> items) {
        super(context, items);
    }

    public HeaderGroupAdapter(Context context, String[][] items) {
        super(context, items);
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
    public void onBindHeaderViewHolder(GroupViewHolder holder, String item, int groupPosition) {
        holder.setText(R.id.tv_title, item);
    }

    @Override
    public void onBindChildViewHolder(GroupViewHolder holder, String item, int groupPosition, int childPosition) {
        holder.setText(R.id.tv_title, item);
    }

}
