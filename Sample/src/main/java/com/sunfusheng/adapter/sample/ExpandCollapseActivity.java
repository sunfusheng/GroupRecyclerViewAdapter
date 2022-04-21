package com.sunfusheng.adapter.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sunfusheng.GroupRecyclerViewAdapter;
import com.sunfusheng.StickyHeaderDecoration;
import com.sunfusheng.adapter.sample.adapter.ExpandCollapseGroupAdapter;
import com.sunfusheng.adapter.sample.util.DataSource;

/**
 * @author sunfusheng on 2018/2/9.
 */
public class ExpandCollapseActivity extends BaseActivity {

  private boolean withAnim = true;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_recycler_view);

    View view = setCustomActionBarLayout(R.layout.layout_switch_actionbar);
    if (null != view) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      TextView tvTitle = view.findViewById(R.id.tv_title);
      tvTitle.setText(R.string.expand_collapse);
      SwitchCompat switchCompat = view.findViewById(R.id.switchCompat);
      switchCompat.setOnCheckedChangeListener((buttonView, isChecked) -> {
        withAnim = isChecked;
      });
      switchCompat.setChecked(withAnim);
    }

    RecyclerView recyclerView = findViewById(R.id.recyclerView);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.addItemDecoration(new StickyHeaderDecoration());

    ExpandCollapseGroupAdapter expandableAdapter = new ExpandCollapseGroupAdapter(this, DataSource.items);
    recyclerView.setAdapter(expandableAdapter);
    expandableAdapter.setOnItemClickListener((adapter, data, groupPosition, childPosition) -> {
      if (adapter.isHeader(groupPosition, childPosition)) {
        if (expandableAdapter.isExpand(groupPosition)) {
          expandableAdapter.collapseGroup(groupPosition, withAnim);
        } else {
          expandableAdapter.expandGroup(groupPosition, withAnim);
        }
        if (withAnim) {
          expandableAdapter.updateItem(groupPosition, childPosition, expandableAdapter.getItem(groupPosition, childPosition));
        }
      }
    });
    expandableAdapter.setOnItemLongClickListener((adapter, data, groupPosition, childPosition) -> {
      Toast.makeText(this, "LongClick groupPosition:" + groupPosition + ", childPosition:" + childPosition, Toast.LENGTH_SHORT).show();
    });
  }

}
