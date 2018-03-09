package com.sunfusheng;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author sunfusheng on 2018/3/7.
 */
@SuppressWarnings("unchecked")
public class StickyItemDecoration extends RecyclerView.ItemDecoration {

    private GroupRecyclerViewAdapter groupAdapter;
    private GroupViewHolder viewHolder;
    private View currStickyView;
    private View nextStickyView;
    private int currGroupPosition;
    private Rect stickyRect = new Rect();
    private GestureDetector gestureDetector;

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        RecyclerView.Adapter adapter = parent.getAdapter();
        if (layoutManager == null || adapter == null) {
            return;
        }

        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
        groupAdapter = (GroupRecyclerViewAdapter) adapter;

        int itemCount = state.getItemCount();
        int currItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
        if (currItemPosition == RecyclerView.NO_POSITION) {
            return;
        }

        currGroupPosition = groupAdapter.getGroupPosition(currItemPosition);
        int currChildPosition = groupAdapter.getGroupChildPosition(currGroupPosition, currItemPosition);
        int nextGroupPosition = currGroupPosition + 1;
        int currStickyPosition = groupAdapter.getGroupHeaderPosition(currGroupPosition);
        int nextStickyPosition = groupAdapter.getGroupHeaderPosition(nextGroupPosition);
        if (nextStickyPosition >= itemCount) {
            nextStickyPosition = currStickyPosition;
        }

        RecyclerView.ViewHolder currViewHolder = parent.findViewHolderForAdapterPosition(currStickyPosition);
        if (currViewHolder != null && currViewHolder.itemView != null) {
            currStickyView = currViewHolder.itemView;
            currStickyView.setTag(currGroupPosition);
        }

        if (currStickyView == null) {
            return;
        }

        RecyclerView.ViewHolder nextViewHolder = parent.findViewHolderForLayoutPosition(nextStickyPosition);
        if (nextViewHolder != null && nextViewHolder.itemView != null) {
            nextStickyView = nextViewHolder.itemView;
            nextStickyView.setTag(nextGroupPosition);
        }

        int stickyViewWidth = currStickyView.getWidth();
        int stickyViewHeight = currStickyView.getHeight();
        int nextStickyViewTop = -1;
        if (nextStickyView != null) {
            nextStickyViewTop = nextStickyView.getTop();
        }

        if ((int) currStickyView.getTag() != currGroupPosition) {
            if (viewHolder == null) {
                viewHolder = new GroupViewHolder(groupAdapter.inflater.inflate(groupAdapter.getHeaderLayoutId(GroupRecyclerViewAdapter.TYPE_HEADER), parent, false));
            }
            groupAdapter.onBindViewHolder(viewHolder, currStickyPosition);
            View itemView = viewHolder.itemView;
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(stickyViewWidth, stickyViewHeight);
            itemView.setLayoutParams(layoutParams);
            itemView.measure(View.MeasureSpec.makeMeasureSpec(stickyViewWidth, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(stickyViewHeight, View.MeasureSpec.EXACTLY));
            itemView.layout(0, -stickyViewHeight, stickyViewWidth, 0);
            itemView.setTag(currGroupPosition);
            currStickyView = itemView;
        }

        int translateY = 0;
        if (nextStickyViewTop < stickyViewHeight) {
            translateY = nextStickyViewTop - stickyViewHeight;
            c.translate(0, translateY);
        }
        currStickyView.draw(c);

        stickyRect.left = 0;
        stickyRect.top = 0;
        stickyRect.right = stickyViewWidth;
        stickyRect.bottom = stickyViewHeight + translateY;

        if (gestureDetector == null) {
            gestureDetector = new GestureDetector(parent.getContext(), simpleOnGestureListener);
            parent.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {
                @Override
                public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                    if (currStickyView != null && currStickyView.isPressed() && e.getAction() == MotionEvent.ACTION_UP) {
                        currStickyView.setPressed(false);
                    }
                    return gestureDetector.onTouchEvent(e);
                }

                @Override
                public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                    super.onTouchEvent(rv, e);
                    if (currStickyView != null && currStickyView.isPressed() && e.getAction() == MotionEvent.ACTION_UP) {
                        currStickyView.setPressed(false);
                    }
                }
            });
        }
    }

    private GestureDetector.SimpleOnGestureListener simpleOnGestureListener = new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            boolean isValidTouch = isValidTouch(e);
            if (isValidTouch) {
                currStickyView.setPressed(true);
            }
            return isValidTouch;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            GroupRecyclerViewAdapter adapter = groupAdapter;
            if (isValidTouch(e) && adapter != null && adapter.onItemClickListener != null) {
                currStickyView.setPressed(false);
                adapter.onItemClickListener.onItemClick(adapter, adapter.getItem(currGroupPosition, 0), currGroupPosition, 0);
                return true;
            }
            return super.onSingleTapUp(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
            GroupRecyclerViewAdapter adapter = groupAdapter;
            if (isValidTouch(e) && adapter != null && adapter.onItemLongClickListener != null) {
                currStickyView.setPressed(false);
                adapter.onItemLongClickListener.onItemLongClick(adapter, adapter.getItem(currGroupPosition, 0), currGroupPosition, 0);
            }
        }

        private boolean isValidTouch(MotionEvent e) {
            Rect rect = stickyRect;
            float x = e.getX();
            float y = e.getY();
            return x > rect.left && x < rect.right && y > rect.top && y < rect.bottom;
        }
    };
}
