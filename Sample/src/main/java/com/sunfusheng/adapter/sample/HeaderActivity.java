package com.sunfusheng.adapter.sample;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sunfusheng.adapter.sample.adapter.HeaderGroupAdapter;
import com.sunfusheng.adapter.sample.util.DataSource;
import com.sunfusheng.adapter.sample.util.Utils;

/**
 * @author sunfusheng on 2018/2/3.
 */
public class HeaderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recycler_view);

        initActionBar(R.string.header, true);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        HeaderGroupAdapter headerAdapter = new HeaderGroupAdapter(this, DataSource.items);
        recyclerView.setAdapter(headerAdapter);

        headerAdapter.setOnItemClickListener((adapter, holder, groupPosition, childPosition) -> {
            Utils.toast(this, "groupPosition: " + groupPosition + "\nchildPosition: " + childPosition);
        });
    }
}
