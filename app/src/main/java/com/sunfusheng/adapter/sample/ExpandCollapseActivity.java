package com.sunfusheng.adapter.sample;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.TextView;

import com.sunfusheng.adapter.sample.adapter.ExpandCollapseGroupAdapter;
import com.sunfusheng.adapter.sample.util.GroupData;
import com.sunfusheng.adapter.sample.util.Utils;

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
        ExpandCollapseGroupAdapter groupAdapter = new ExpandCollapseGroupAdapter(this, GroupData.items);
        recyclerView.setAdapter(groupAdapter);

        groupAdapter.setOnItemClickListener((adapter, holder, groupPosition, childPosition) -> {
            boolean isHeader = adapter.isHeader(groupPosition, childPosition);
            if (isHeader) {
                if (groupAdapter.isExpand(groupPosition)) {
                    groupAdapter.collapseGroup(groupPosition, withAnim);
                } else {
                    groupAdapter.expandGroup(groupPosition, withAnim);
                }
            } else {
                Utils.toast(this, "groupPosition: " + groupPosition + "\nchildPosition: " + childPosition);
            }
        });
    }
}
