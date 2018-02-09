package com.sunfusheng.adapter.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sunfusheng.adapter.sample.adapter.MainGroupAdapter;
import com.sunfusheng.adapter.sample.util.GroupData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recycler_view);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MainGroupAdapter mainAdapter = new MainGroupAdapter(this, GroupData.mainItems);
        recyclerView.setAdapter(mainAdapter);

        mainAdapter.setOnItemClickListener((adapter, holder, groupPosition, childPosition) -> {
            GroupData.MainItemConfig item = mainAdapter.getItem(groupPosition, childPosition);
            if (null != item && null != item.intentClass) {
                startActivity(new Intent(this, item.intentClass));
            }
        });
    }

}
