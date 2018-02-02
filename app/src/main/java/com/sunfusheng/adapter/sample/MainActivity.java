package com.sunfusheng.adapter.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.sunfusheng.adapter.sample.adapter.MainGroupAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MainGroupAdapter mainAdapter;

    private String[][] items = {
            {"", "组头、组项列表", "组项、组尾列表", "组头、组项、组尾列表"},
            {"", "增加、删除、刷新组项"},
            {"", "微信-我"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainAdapter = new MainGroupAdapter(this, items);
        recyclerView.setAdapter(mainAdapter);

        mainAdapter.setOnItemClickListener((adapter, holder, groupPosition, childPosition) -> {
            toast("groupPosition: " + groupPosition + "\nchildPosition: " + childPosition);
        });

    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
