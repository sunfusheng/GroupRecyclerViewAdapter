package com.sunfusheng.adapter.sample;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sunfusheng.adapter.sample.adapter.WeChatNewMessageGroupAdapter;
import com.sunfusheng.adapter.sample.util.DataSource;
import com.sunfusheng.adapter.sample.util.Utils;

/**
 * @author sunfusheng on 2018/2/12.
 */
public class WeChatNewMessageActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_recycler_view);

    initActionBar(R.string.wechat_new_message, true);

    RecyclerView recyclerView = findViewById(R.id.recyclerView);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    WeChatNewMessageGroupAdapter weChatAdapter = new WeChatNewMessageGroupAdapter(this, DataSource.weChatNewMessageItems);
    recyclerView.setAdapter(weChatAdapter);

    weChatAdapter.setOnItemClickListener((adapter, data, groupPosition, childPosition) -> {
      if (null == data || 0 == data.key) {
        return;
      }

      if (R.attr.key_new_message_voice == data.key) {
        Utils.toastLong(this, "你是我的小呀小苹果^_^");
      }
    });
  }
}
