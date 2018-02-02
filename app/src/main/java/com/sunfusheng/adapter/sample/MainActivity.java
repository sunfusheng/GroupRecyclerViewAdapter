package com.sunfusheng.adapter.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CommonGroupAdapter groupAdapter;

    private String[][] items = {
            {"第一组", "1", "2", "3"},
            {"第二组", "1", "2"},
            {"第三组", "1", "2", "3", "4", "5"},
            {"第四组", "1", "2", "3", "4"},
            {"第五组", "1", "2"},
            {"第六组", "1", "2", "3", "4", "5", "6"},
            {"第七组", "1"},
            {"第八组", "1", "2", "3", "4"},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<List<String>> lists = new ArrayList<>();
        for (String[] item : items) {
            List<String> list = new ArrayList<>();
            list.addAll(Arrays.asList(item));
            lists.add(list);
        }

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        groupAdapter = new CommonGroupAdapter(this, lists);
        recyclerView.setAdapter(groupAdapter);

        groupAdapter.setOnItemClickListener((adapter, holder, groupPosition, childPosition) -> {
            toast("content: " + groupAdapter.getItem(groupPosition, childPosition) +
                    "\ngroupPosition: " + groupPosition +
                    "\nchildPosition: " + childPosition);
        });

    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
