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

    public static final String API_TOKEN = "3c57fb226edf7facf821501e4eba08d2";
    public static final String APP_ID = "5a80151e959d6949f2ecb2e8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recycler_view);

        checkVersion();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MainGroupAdapter mainAdapter = new MainGroupAdapter(recyclerView, DataSource.mainItems);
        recyclerView.setAdapter(mainAdapter);

        mainAdapter.setOnItemClickListener((adapter, data, groupPosition, childPosition) -> {
            if (null != data && null != data.intentClass) {
                startActivity(new Intent(this, data.intentClass));
            }
        });
    }

    private void checkVersion() {
        if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) &&
                PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            new FirUpdater(this, API_TOKEN, APP_ID).checkVersion();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100 && grantResults.length > 0) {
            new FirUpdater(this, API_TOKEN, APP_ID).checkVersion();
        }
    }

}
