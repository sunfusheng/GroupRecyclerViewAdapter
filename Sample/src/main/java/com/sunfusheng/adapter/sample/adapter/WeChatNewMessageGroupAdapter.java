package com.sunfusheng.adapter.sample.adapter;

import android.content.Context;
import android.view.View;

import com.kyleduo.switchbutton.SwitchButton;
import com.sunfusheng.GroupAdapterUtils;
import com.sunfusheng.GroupViewHolder;
import com.sunfusheng.HeaderGroupRecyclerViewAdapter;
import com.sunfusheng.adapter.sample.R;
import com.sunfusheng.adapter.sample.util.DataSource;

import java.util.List;

/**
 * @author sunfusheng on 2018/2/12.
 */
public class WeChatNewMessageGroupAdapter extends HeaderGroupRecyclerViewAdapter<DataSource.WeChatItemConfig> {

    public WeChatNewMessageGroupAdapter(Context context) {
        super(context);
    }

    public WeChatNewMessageGroupAdapter(Context context, List<List<DataSource.WeChatItemConfig>> items) {
        super(context, items);
    }

    public WeChatNewMessageGroupAdapter(Context context, DataSource.WeChatItemConfig[][] items) {
        super(context, items);
    }

    @Override
    public int getChildItemViewType(int groupPosition, int childPosition) {
        return getItem(groupPosition, childPosition).viewType;
    }

    @Override
    public int getHeaderLayoutId(int viewType) {
        return R.layout.divider_20dp;
    }

    @Override
    public int getChildLayoutId(int viewType) {
        if (viewType == DataSource.VIEW_TYPE_WECHAT_SWITCH) {
            return R.layout.item_wechat_new_message_switch;
        }
        return R.layout.item_wechat_new_message_text;
    }

    @Override
    public void onBindHeaderViewHolder(GroupViewHolder holder, DataSource.WeChatItemConfig item, int groupPosition) {

    }

    @Override
    public void onBindChildViewHolder(GroupViewHolder holder, DataSource.WeChatItemConfig item, int groupPosition, int childPosition) {
        holder.setText(R.id.tv_title, item.titleId);
        holder.setText(R.id.tv_subtitle, item.subtitleId);
        holder.setVisible(R.id.tv_subtitle, item.subtitleId != 0);
        holder.setVisibility(R.id.divider, isGroupLastItem(groupPosition, childPosition) ? View.INVISIBLE : View.VISIBLE);

        int viewType = getChildItemViewType(groupPosition, childPosition);
        if (viewType == DataSource.VIEW_TYPE_WECHAT_SWITCH) {
            SwitchButton switchButton = holder.get(R.id.switch_button);
            switchButton.setCheckedImmediatelyNoEvent(item.isChecked);

            switchButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
                holder.itemView.post(() -> {
                    if (R.attr.key_new_message_notify == item.key) {
                        if (isChecked) {
                            insertGroups(groupPosition + 1, DataSource.newMessageNotifyItemsHolder);
                        } else {
                            removeGroups(groupPosition + 1, groupsCount());
                        }
                    } else if (R.attr.key_voice == item.key) {
                        if (isChecked) {
                            if (!hasItem(groupPosition, R.attr.key_new_message_voice)) {
                                insertItem(groupPosition, childPosition + 1, DataSource.WeChatItemConfig.NEW_MESSAGE_VOICE);
                            }
                        } else {
                            if (hasItem(groupPosition, R.attr.key_new_message_voice)) {
                                removeItem(groupPosition, childPosition + 1);
                            }
                        }
                    }
                });
            });
        }
    }

    private boolean hasItem(int groupPosition, int key) {
        List<DataSource.WeChatItemConfig> items = getGroups().get(groupPosition);
        if (!GroupAdapterUtils.isEmpty(items)) {
            for (DataSource.WeChatItemConfig item : items) {
                if (item.key == key) {
                    return true;
                }
            }
        }
        return false;
    }

}
