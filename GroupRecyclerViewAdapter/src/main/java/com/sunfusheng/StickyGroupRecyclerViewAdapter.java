package com.sunfusheng;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * @author sunfusheng on 2018/3/7.
 */
abstract public class StickyGroupRecyclerViewAdapter<T> extends GroupRecyclerViewAdapter<T> {

    public StickyGroupRecyclerViewAdapter(RecyclerView recyclerView) {
        super(recyclerView);
        initStickyItemDecoration(recyclerView);
    }

    public StickyGroupRecyclerViewAdapter(RecyclerView recyclerView, T[][] groups) {
        super(recyclerView, groups);
        initStickyItemDecoration(recyclerView);
    }

    public StickyGroupRecyclerViewAdapter(RecyclerView recyclerView, List<List<T>> groups) {
        super(recyclerView, groups);
        initStickyItemDecoration(recyclerView);
    }

    private void initStickyItemDecoration(RecyclerView recyclerView) {
        StickyItemDecoration decoration = new StickyItemDecoration();
        recyclerView.addItemDecoration(decoration);
    }
}
