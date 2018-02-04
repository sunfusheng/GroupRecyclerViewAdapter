package com.sunfusheng.adapter;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author sunfusheng on 2018/2/4.
 */
public class GroupAdapterUtils {

    private static final String TAG = "GroupAdapterUtils";

    public static boolean isEmpty(Collection<?> collection) {
        return null == collection || collection.isEmpty();
    }

    public static <T> boolean isEmpty(T[] array) {
        return null == array || array.length == 0;
    }

    public static <T> boolean checkData(T[][] items, int minCountPerGroup) {
        if (isEmpty(items)) {
            return false;
        }

        return checkData(convertData(items), minCountPerGroup);
    }

    public static <T> boolean checkData(List<List<T>> items, int minCountPerGroup) {
        if (isEmpty(items)) {
            return false;
        }

        Iterator<List<T>> iterator = items.iterator();
        while (iterator.hasNext()) {
            List<T> item = iterator.next();
            if (isEmpty(item) || minCountPerGroup > item.size()) {
                iterator.remove();
                Log.w(TAG, "data illegal, already removed item " + item);
            }
        }
        return !isEmpty(items);
    }

    public static <T> List<List<T>> convertData(T[][] items) {
        List<List<T>> lists = new ArrayList<>();
        for (T[] item : items) {
            List<T> list = new ArrayList<>();
            list.addAll(Arrays.asList(item));
            lists.add(list);
        }
        return lists;
    }

    /**
     * 返回所有组的个数
     *
     * @param items
     * @param <T>
     * @return
     */
    public static <T> int getGroupCount(List<List<T>> items) {
        return isEmpty(items) ? 0 : items.size();
    }

    /**
     * 返回指定组所有项的个数，包括header、child、footer
     *
     * @param items
     * @param groupPosition 组下标
     * @param <T>
     * @return
     */
    public static <T> int getGroupItemCount(List<List<T>> items, int groupPosition) {
        if (isEmpty(items)) {
            return 0;
        }

        return null == items.get(groupPosition) ? 0 : items.get(groupPosition).size();
    }

    /**
     * 返回多个组所有项的个数
     *
     * @param items
     * @param start
     * @param count
     * @param <T>
     * @return
     */
    public static <T> int countGroupItemRange(List<List<T>> items, int start, int count) {
        int itemCount = 0;
        for (int i = start, groupCount = getGroupCount(items); i < start + count && i < groupCount; i++) {
            itemCount += getGroupItemCount(items, i);
        }
        return itemCount;
    }

}
