package com.sunfusheng.adapter.sample;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sunfusheng.adapter.sample.adapter.WeChatMeGroupAdapter;
import com.sunfusheng.adapter.sample.util.GroupData;
import com.sunfusheng.adapter.sample.util.Utils;

/**
 * @author sunfusheng on 2018/2/10.
 */
public class WeChatMeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recycler_view);

        initActionBar(R.string.wechat_me, true);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        WeChatMeGroupAdapter weChatAdapter = new WeChatMeGroupAdapter(this, GroupData.weChatMeItems);
        recyclerView.setAdapter(weChatAdapter);

        weChatAdapter.setOnItemClickListener((adapter, holder, groupPosition, childPosition) -> {
            Utils.toast(this, weChatAdapter.getItem(groupPosition, childPosition).titleId);
        });
    }
}
