package com.sunfusheng.adapter.sample;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sunfusheng.adapter.sample.adapter.StickyGroupAdapter;
import com.sunfusheng.adapter.sample.util.DataSource;
import com.sunfusheng.adapter.sample.util.Utils;

/**
 * @author sunfusheng on 2018/3/7.
 */
public class StickyActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recycler_view);

        initActionBar(R.string.sticky, true);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        StickyGroupAdapter stickyAdapter = new StickyGroupAdapter(recyclerView, DataSource.items);
        recyclerView.setAdapter(stickyAdapter);

        stickyAdapter.setOnItemClickListener((adapter, data, groupPosition, childPosition) -> {
            Utils.toast(this, "点击 data: " + data + "\ngroupPosition: " + groupPosition + "\nchildPosition: " + childPosition);
        });

        stickyAdapter.setOnItemLongClickListener((adapter, data, groupPosition, childPosition) -> {
            Utils.toast(this, "长按 data: " + data + "\ngroupPosition: " + groupPosition + "\nchildPosition: " + childPosition);
        });
    }
}
