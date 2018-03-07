package com.sunfusheng.adapter.sample;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sunfusheng.adapter.sample.adapter.HeaderFooterGroupAdapter;
import com.sunfusheng.adapter.sample.util.DataSource;
import com.sunfusheng.adapter.sample.util.Utils;

/**
 * @author sunfusheng on 2018/2/3.
 */
public class HeaderFooterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recycler_view);

        initActionBar(R.string.header_footer, true);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        HeaderFooterGroupAdapter headerFooterAdapter = new HeaderFooterGroupAdapter(this, DataSource.items);
        recyclerView.setAdapter(headerFooterAdapter);

        headerFooterAdapter.setOnItemClickListener((adapter, holder, groupPosition, childPosition) -> {
            Utils.toast(this, "groupPosition: " + groupPosition + "\nchildPosition: " + childPosition);
        });
    }
}
