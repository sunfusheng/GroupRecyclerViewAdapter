package com.sunfusheng;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * @author sunfusheng on 2018/2/1.
 */
abstract public class FooterGroupRecyclerViewAdapter<T> extends GroupRecyclerViewAdapter<T> {

    public FooterGroupRecyclerViewAdapter(RecyclerView recyclerView) {
        super(recyclerView);
    }

    public FooterGroupRecyclerViewAdapter(RecyclerView recyclerView, List<List<T>> items) {
        super(recyclerView, items);
    }

    public FooterGroupRecyclerViewAdapter(RecyclerView recyclerView, T[][] items) {
        super(recyclerView, items);
    }

    @Override
    public boolean showHeader() {
        return false;
    }

    @Override
    public boolean showFooter() {
        return true;
    }

    @Override
    public int getHeaderLayoutId(int viewType) {
        return 0;
    }

    @Override
    public void onBindHeaderViewHolder(GroupViewHolder holder, T item, int groupPosition) {

    }
}
