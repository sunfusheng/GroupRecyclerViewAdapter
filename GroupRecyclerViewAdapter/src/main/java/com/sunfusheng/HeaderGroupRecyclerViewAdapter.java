package com.sunfusheng;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * @author sunfusheng on 2018/2/1.
 */
abstract public class HeaderGroupRecyclerViewAdapter<T> extends GroupRecyclerViewAdapter<T> {

    public HeaderGroupRecyclerViewAdapter(RecyclerView recyclerView) {
        super(recyclerView);
    }

    public HeaderGroupRecyclerViewAdapter(RecyclerView recyclerView, List<List<T>> items) {
        super(recyclerView, items);
    }

    public HeaderGroupRecyclerViewAdapter(RecyclerView recyclerView, T[][] items) {
        super(recyclerView, items);
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
    public int getFooterLayoutId(int viewType) {
        return 0;
    }

    @Override
    public void onBindFooterViewHolder(GroupViewHolder holder, T item, int groupPosition) {

    }
}
