package com.sunfusheng.adapter.sample;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sunfusheng.adapter.sample.adapter.HeaderFooterGroupAdapter;
import com.sunfusheng.adapter.sample.util.DataSource;
import com.sunfusheng.adapter.sample.util.Utils;

/**
 * @author sunfusheng on 2018/2/3.
 */
public class InsertRemoveUpdateActivity extends BaseActivity {

  private HeaderFooterGroupAdapter mAdapter;

  private String[] new_insert_group = {"❖ 插入的新组", "A", "B", "C"};
  private String[][] new_insert_groups = {
      {"❖ 插入的新组1", "A", "B", "C"},
      {"❖ 插入的新组2", "a", "b", "c"}
  };
  private String new_insert_item = "• 插入的新组项";
  private String[] new_insert_items = {"• 插入的新组项1", "• 插入的新组项2", "• 插入的新组项3"};

  private String[] new_update_group = {"❖ 更新的新组", "A", "B", "C", "D", "E"};
  private String[][] new_update_groups = {
      {"❖ 更新的新组1", "A", "B", "C"},
      {"❖ 更新的新组2", "a", "b", "c"}
  };
  private String new_update_item = "• 更新的新组项";
  private String[] new_update_items = {"• 更新的新组项1", "• 更新的新组项2", "• 更新的新组项3"};

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_recycler_view);

    initActionBar(R.string.insert_remove_update, true);

    RecyclerView recyclerView = findViewById(R.id.recyclerView);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    mAdapter = new HeaderFooterGroupAdapter(this, DataSource.items);
    recyclerView.setAdapter(mAdapter);

    mAdapter.setOnItemClickListener((adapter, data, groupPosition, childPosition) -> {
      String header = mAdapter.getItem(groupPosition, 0);
      Utils.toast(this, header + " : " + data +
          "\ngroupPosition: " + groupPosition +
          "\nchildPosition: " + childPosition);
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_insert_remove_update, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    boolean isSuccess = true;
    int insertPosition = 1;
    int removePosition = 0;
    int updatePosition = 1;

    switch (item.getItemId()) {
      // todo insert
      case R.id.insert_group:
        isSuccess = mAdapter.insertGroup(insertPosition, new_insert_group, true);
        break;
      case R.id.insert_groups:
        isSuccess = mAdapter.insertGroups(insertPosition, new_insert_groups, true);
        break;
      case R.id.insert_item:
        isSuccess = mAdapter.insertItem(insertPosition, 1, new_insert_item);
        break;
      case R.id.insert_items:
        isSuccess = mAdapter.insertItems(insertPosition, 1, new_insert_items);
        break;

      //todo remove
      case R.id.remove_group:
        isSuccess = mAdapter.removeGroup(removePosition);
        break;
      case R.id.remove_groups:
        isSuccess = mAdapter.removeGroups(removePosition, 2);
        break;
      case R.id.remove_item:
        isSuccess = mAdapter.removeItem(removePosition, 0);
        break;
      case R.id.remove_items:
        isSuccess = mAdapter.removeItems(removePosition, 0, 2);
        break;

      //todo update
      case R.id.update_group:
        isSuccess = mAdapter.updateGroup(updatePosition, new_update_group);
        break;
      case R.id.update_groups:
        isSuccess = mAdapter.updateGroups(updatePosition, new_update_groups);
        break;
      case R.id.update_item:
        isSuccess = mAdapter.updateItem(updatePosition, 1, new_update_item);
        break;
      case R.id.update_items:
        isSuccess = mAdapter.updateItems(updatePosition, 0, new_update_items);
        break;
    }

    if (!isSuccess) {
      Utils.toast(this, "操作失败，请检查Data或Position");
    }
    return super.onOptionsItemSelected(item);
  }
}
