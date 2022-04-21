package com.sunfusheng.adapter.sample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.sunfusheng.StickyHeaderDecoration;
import com.sunfusheng.adapter.sample.adapter.StickyGroupAdapter;
import com.sunfusheng.adapter.sample.util.DataSource;

/**
 * @author sunfusheng on 2018/9/23.
 */
public class BottomSheetDialogActivity extends BaseActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bottom_sheet);

    initActionBar(R.string.bottom_sheet, true);

    View bottomSheetView = LayoutInflater.from(this).inflate(R.layout.layout_recycler_view, null, false);
    RecyclerView recyclerView = bottomSheetView.findViewById(R.id.recyclerView);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.addItemDecoration(new StickyHeaderDecoration());
    StickyGroupAdapter stickyAdapter = new StickyGroupAdapter(this, DataSource.items);
    recyclerView.setAdapter(stickyAdapter);

    BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
    bottomSheetDialog.setContentView(bottomSheetView);
    BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) bottomSheetView.getParent());
    int height = getWindowManager().getDefaultDisplay().getHeight();
    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) bottomSheetView.getLayoutParams();
    layoutParams.height = height / 2;
    bottomSheetView.setLayoutParams(layoutParams);
//        bottomSheetBehavior.setPeekHeight(height / 3);
    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    bottomSheetDialog.show();

    bottomSheetDialog.setOnDismissListener(dialog -> {
      bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    });

    findViewById(R.id.text).setOnClickListener(v -> {
      bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
      bottomSheetDialog.show();
    });
  }
}
