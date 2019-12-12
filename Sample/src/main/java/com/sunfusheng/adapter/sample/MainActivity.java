package com.sunfusheng.adapter.sample;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sunfusheng.FirUpdater;
import com.sunfusheng.adapter.sample.adapter.MainGroupAdapter;
import com.sunfusheng.adapter.sample.util.DataSource;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recycler_view);

        FirUpdater.getInstance(this)
                .apiToken("3c57fb226edf7facf821501e4eba08d2")
                .appId("5a80151e959d6949f2ecb2e8")
                .checkVersion();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MainGroupAdapter mainAdapter = new MainGroupAdapter(this, DataSource.mainItems);
        recyclerView.setAdapter(mainAdapter);

        mainAdapter.setOnItemClickListener((adapter, data, groupPosition, childPosition) -> {
            if (null != data && null != data.intentClass) {
                startActivity(new Intent(this, data.intentClass));
            }
        });
    }

}
