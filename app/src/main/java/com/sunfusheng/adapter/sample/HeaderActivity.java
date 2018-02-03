package com.sunfusheng.adapter.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sunfusheng.adapter.sample.adapter.HeaderGroupAdapter;
import com.sunfusheng.adapter.sample.util.GroupData;
import com.sunfusheng.adapter.sample.util.Utils;

/**
 * @author sunfusheng on 2018/2/3.
 */
public class HeaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(R.string.header);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        HeaderGroupAdapter headerAdapter = new HeaderGroupAdapter(this, GroupData.items);
        recyclerView.setAdapter(headerAdapter);

        headerAdapter.setOnItemClickListener((adapter, holder, groupPosition, childPosition) -> {
            Utils.toast(this, "groupPosition: " + groupPosition + "\nchildPosition: " + childPosition);
        });

    }
}
