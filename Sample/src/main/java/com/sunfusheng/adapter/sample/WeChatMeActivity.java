package com.sunfusheng.adapter.sample;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sunfusheng.adapter.sample.adapter.WeChatMeGroupAdapter;
import com.sunfusheng.adapter.sample.util.DataSource;
import com.sunfusheng.adapter.sample.util.Utils;

/**
 * @author sunfusheng on 2018/2/10.
 */
public class WeChatMeActivity extends BaseActivity {

    private int clickProfileCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recycler_view);

        initActionBar(R.string.wechat_me, true);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        WeChatMeGroupAdapter weChatAdapter = new WeChatMeGroupAdapter(this, DataSource.weChatMeItems);
        recyclerView.setAdapter(weChatAdapter);

        weChatAdapter.setOnItemClickListener((adapter, holder, groupPosition, childPosition) -> {
            DataSource.WeChatItemConfig item = weChatAdapter.getItem(groupPosition, childPosition);
            if (null == item || 0 == item.key) {
                return;
            }

            if (R.attr.key_profile == item.key) {
                Utils.toast(this, "惊喜 +" + (++clickProfileCount));
            } else {
                Utils.toast(this, item.titleId);
            }
        });
    }
}
