package com.sunfusheng.adapter.sample;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sunfusheng.adapter.sample.adapter.MainGroupAdapter;
import com.sunfusheng.adapter.sample.util.DataSource;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_recycler_view);

//        FirUpdater.getInstance(this)
//                .apiToken("3c57fb226edf7facf821501e4eba08d2")
//                .appId("5a80151e959d6949f2ecb2e8")
//                .checkVersion();

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
