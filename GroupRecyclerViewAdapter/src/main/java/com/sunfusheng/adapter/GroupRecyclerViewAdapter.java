package com.sunfusheng.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sunfusheng on 2018/2/1.
 */
abstract public class GroupRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "GroupAdapter";

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_CHILD = 1;
    public static final int TYPE_FOOTER = 2;

    private Context context;
    private LayoutInflater inflater;
    private List<List<T>> groups;
    private int itemPosition;

    private OnItemClickListener onItemClickListener;

    public GroupRecyclerViewAdapter(Context context) {
        this(context, new ArrayList<>());
    }

    public GroupRecyclerViewAdapter(Context context, T[][] groups) {
        GroupAdapterUtils.checkGroupsData(groups, minCountPerGroup());
        init(context, GroupAdapterUtils.convertGroupsData(groups));
    }

    public GroupRecyclerViewAdapter(Context context, List<List<T>> groups) {
        GroupAdapterUtils.checkGroupsData(groups, minCountPerGroup());
        init(context, groups);
    }

    private void init(Context context, List<List<T>> groups) {
        this.context = context;
        this.groups = groups;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setItems(T[][] groups) {
        GroupAdapterUtils.checkGroupsData(groups, minCountPerGroup());
        this.groups = GroupAdapterUtils.convertGroupsData(groups);
        notifyDataSetChanged();
    }

    public void setItems(List<List<T>> groups) {
        GroupAdapterUtils.checkGroupsData(groups, minCountPerGroup());
        this.groups = groups;
        notifyDataSetChanged();
    }

    public List<List<T>> getGroups() {
        return this.groups;
    }

    public T getItem(int groupPosition, int childPosition) {
        return this.groups.get(groupPosition).get(childPosition);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GroupViewHolder(inflater.inflate(getLayoutId(), parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GroupViewHolder viewHolder = (GroupViewHolder) holder;
        int viewType = confirmItemViewType(position);
        int groupPosition = getGroupPosition(position);
        int childPosition = getGroupChildPosition(groupPosition, position);
        T item = getItem(groupPosition, childPosition);
        Log.d(TAG, "position: " + position + " groupPosition: " + groupPosition + " childPosition: " + childPosition);

        if (null != onItemClickListener) {
            holder.itemView.setOnClickListener(view -> {
                if (null != onItemClickListener) {
                    onItemClickListener.onItemClick(this, viewHolder, groupPosition, childPosition);
                }
            });
        }

        if (TYPE_HEADER == viewType) {
            onBindHeaderViewHolder(viewHolder, item, groupPosition);
        } else if (TYPE_CHILD == viewType) {
            onBindChildViewHolder(viewHolder, item, groupPosition, childPosition);
        } else if (TYPE_FOOTER == viewType) {
            onBindFooterViewHolder(viewHolder, item, groupPosition);
        }
    }

    @Override
    public int getItemViewType(int position) {
        this.itemPosition = position;
        int viewType = confirmItemViewType(position);
        int groupPosition = getGroupPosition(position);

        if (TYPE_HEADER == viewType) {
            return getHeaderItemViewType(groupPosition);
        } else if (TYPE_CHILD == viewType) {
            int childPosition = getGroupChildPosition(groupPosition, position);
            return getChildItemViewType(groupPosition, childPosition);
        } else if (TYPE_FOOTER == viewType) {
            return getFooterItemViewType(groupPosition);
        }
        return super.getItemViewType(position);
    }

    public int confirmItemViewType(int itemPosition) {
        int itemCount = 0;
        for (int i = 0, groupsCount = groupsCount(); i < groupsCount; i++) {
            if (showHeader()) {
                itemCount += 1;
                if (itemPosition < itemCount) {
                    return TYPE_HEADER;
                }
            }

            itemCount += countGroupChildren(i);
            if (itemPosition < itemCount) {
                return TYPE_CHILD;
            }

            if (showFooter()) {
                itemCount += 1;
                if (itemPosition < itemCount) {
                    return TYPE_FOOTER;
                }
            }
        }
        throw new IndexOutOfBoundsException("Confirm item type failed, " + "itemPosition = " + itemPosition + ", itemCount = " + itemCount);
    }

    public int getLayoutId() {
        int viewType = confirmItemViewType(getItemPosition());
        if (TYPE_HEADER == viewType) {
            return getHeaderLayoutId();
        } else if (TYPE_CHILD == viewType) {
            return getChildLayoutId();
        } else if (TYPE_FOOTER == viewType) {
            return getFooterLayoutId();
        }
        return 0;
    }

    /**
     * @return 返回列表下标
     */
    public int getItemPosition() {
        return itemPosition;
    }

    /**
     * @param itemPosition 列表下标
     * @return 返回所在组
     */
    public int getGroupPosition(int itemPosition) {
        int itemCount = 0;
        for (int i = 0, groupsCount = groupsCount(); i < groupsCount; i++) {
            itemCount += countGroupItems(i);
            if (itemPosition < itemCount) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @param groupPosition 组下标
     * @return 返回指定组header的列表下标，如果没有header返回-1
     */
    public int getGroupHeaderPosition(int groupPosition) {
        if (showHeader() && groupPosition < groupsCount()) {
            return countGroupsItemsRange(0, groupPosition);
        }
        return -1;
    }

    /**
     * @param groupPosition 组下标
     * @param itemPosition  列表下标
     * @return 返回所在组的下标
     */
    public int getGroupChildPosition(int groupPosition, int itemPosition) {
        if (groupPosition < groupsCount()) {
            int position = itemPosition - countGroupsItemsRange(0, groupPosition);
            if (0 <= position) {
                return position;
            }
        }
        return -1;
    }

    /**
     * @param groupPosition 组下标
     * @return 返回指定组footer的列表下标，如果没有footer返回-1
     */
    public int getGroupFooterPosition(int groupPosition) {
        if (showFooter() && groupPosition < groupsCount()) {
            return countGroupsItemsRange(0, groupPosition + 1) - 1;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return countGroupsItemsRange(0, groupsCount());
    }

    public int groupsCount() {
        return GroupAdapterUtils.countGroups(groups);
    }

    public int countGroupItems(int groupPosition) {
        return GroupAdapterUtils.countGroupItems(groups, groupPosition);
    }

    public int countGroupChildren(int groupPosition) {
        return GroupAdapterUtils.countGroupChildren(groups, groupPosition, minCountPerGroup());
    }

    public int countGroupsItemsRange(int start, int count) {
        return GroupAdapterUtils.countGroupsItemsRange(groups, start, count);
    }

    public boolean insertGroup(int groupPosition, T[] group) {
        if (GroupAdapterUtils.checkGroupData(group, minCountPerGroup())) {
            List<T> list = Arrays.asList(group);
            return insertGroup(groupPosition, new ArrayList<>(list));
        }
        return false;
    }

    public boolean insertGroup(int groupPosition, List<T> group) {
        if (GroupAdapterUtils.checkGroupData(group, minCountPerGroup())) {
            this.groups.add(groupPosition, group);
            int positionStart = countGroupsItemsRange(0, groupPosition);
            notifyItemRangeInserted(positionStart, group.size());
            notifyItemRangeChanged(positionStart + group.size(), getItemCount() - positionStart - group.size());
            return true;
        }
        return false;
    }

    public boolean insertGroups(int groupPosition, T[][] groups) {
        if (GroupAdapterUtils.checkGroupsData(groups, minCountPerGroup())) {
            List<List<T>> lists = GroupAdapterUtils.convertGroupsData(groups);
            return insertGroups(groupPosition, lists);
        }
        return false;
    }

    public boolean insertGroups(int groupPosition, List<List<T>> groups) {
        if (GroupAdapterUtils.checkGroupsData(groups, minCountPerGroup())) {
            this.groups.addAll(groupPosition, groups);
            int groupItemCount = GroupAdapterUtils.countGroupsItemsRange(groups, 0, groups.size());
            int positionStart = countGroupsItemsRange(0, groupPosition);
            notifyItemRangeInserted(positionStart, groupItemCount);
            notifyItemRangeChanged(positionStart + groupItemCount, getItemCount() - positionStart - groupItemCount);
            return true;
        }
        return false;
    }

    public boolean insertItem(int groupPosition, int childPosition, T item) {
        if (null != item) {
            this.groups.get(groupPosition).add(childPosition, item);
            int positionStart = countGroupsItemsRange(0, groupPosition) + childPosition;
            notifyItemInserted(positionStart);
            notifyItemRangeChanged(positionStart + 1, getItemCount() - positionStart - 1);
            return true;
        }
        return false;
    }

    public boolean insertItems(int groupPosition, int childPosition, T[] items) {
        if (!GroupAdapterUtils.isEmpty(items)) {
            List<T> list = Arrays.asList(items);
            return insertItems(groupPosition, childPosition, new ArrayList<>(list));
        }
        return false;
    }

    public boolean insertItems(int groupPosition, int childPosition, List<T> items) {
        if (!GroupAdapterUtils.isEmpty(items)) {
            this.groups.get(groupPosition).addAll(childPosition, items);
            int positionStart = countGroupsItemsRange(0, groupPosition) + childPosition;
            notifyItemRangeInserted(positionStart, items.size());
            notifyItemRangeChanged(positionStart + items.size(), getItemCount() - positionStart - items.size());
            return true;
        }
        return false;
    }

    public boolean removeGroup(int groupPosition) {
        if (groupPosition < groupsCount()) {
            int positionStart = countGroupsItemsRange(0, groupPosition);
            int itemCount = countGroupItems(groupPosition);
            this.groups.remove(groupPosition);
            notifyItemRangeRemoved(positionStart, itemCount);
            notifyItemRangeChanged(positionStart, getItemCount() - positionStart);
            return true;
        }
        return false;
    }

    public boolean removeGroups(int groupPosition, int count) {
        if (0 == count || groupPosition >= groupsCount()) {
            return false;
        }

        int groupsCount = count;
        if (groupPosition + count > groupsCount()) {
            groupsCount = groupsCount() - groupPosition;
        }

        int positionStart = countGroupsItemsRange(0, groupPosition);
        int itemCount = countGroupsItemsRange(groupPosition, groupsCount);
        for (int i = 0; i < groupsCount; i++) {
            this.groups.remove(groupPosition);
        }
        notifyItemRangeRemoved(positionStart, itemCount);
        notifyItemRangeChanged(positionStart, getItemCount() - positionStart);
        return true;
    }

    public boolean removeItem(int groupPosition, int childPosition) {
        return removeItems(groupPosition, childPosition, 1);
    }

    public boolean removeItems(int groupPosition, int childPosition, int count) {
        if (groupPosition < groupsCount()) {
            int positionStart = countGroupsItemsRange(0, groupPosition);
            int itemCount = countGroupItems(groupPosition);
            if (childPosition >= itemCount) {
                return false;
            }

            int childCount = count;
            if (childPosition + count > itemCount) {
                childCount = itemCount - childPosition;
            }

            if (itemCount < minCountPerGroup() + childCount) {
                removeGroup(groupPosition);
            } else {
                for (int i = 0; i < childCount; i++) {
                    this.groups.get(groupPosition).remove(childPosition);
                }
                notifyItemRangeRemoved(positionStart + childPosition, childCount);
                notifyItemRangeChanged(positionStart, getItemCount() - positionStart);
            }
            return true;
        }
        return false;
    }

    public int getHeaderItemViewType(int groupPosition) {
        return TYPE_HEADER;
    }

    public int getChildItemViewType(int groupPosition, int childPosition) {
        return TYPE_CHILD;
    }

    public int getFooterItemViewType(int groupPosition) {
        return TYPE_FOOTER;
    }

    public int minCountPerGroup() {
        return (showHeader() ? 1 : 0) + (showFooter() ? 1 : 0);
    }

    public boolean showHeader() {
        return false;
    }

    public boolean showFooter() {
        return false;
    }

    abstract public int getHeaderLayoutId();

    abstract public int getChildLayoutId();

    abstract public int getFooterLayoutId();

    abstract public void onBindHeaderViewHolder(GroupViewHolder holder, T item, int groupPosition);

    abstract public void onBindChildViewHolder(GroupViewHolder holder, T item, int groupPosition, int childPosition);

    abstract public void onBindFooterViewHolder(GroupViewHolder holder, T item, int groupPosition);

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(GroupRecyclerViewAdapter adapter, GroupViewHolder holder, int groupPosition, int childPosition);
    }

}
