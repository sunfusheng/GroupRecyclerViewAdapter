package com.sunfusheng.adapter.sample.util;

import com.sunfusheng.adapter.sample.ExpandCollapseActivity;
import com.sunfusheng.adapter.sample.InsertRemoveUpdateActivity;
import com.sunfusheng.adapter.sample.R;
import com.sunfusheng.adapter.sample.StickyActivity;
import com.sunfusheng.adapter.sample.WeChatMeActivity;
import com.sunfusheng.adapter.sample.WeChatNewMessageActivity;

/**
 * @author sunfusheng on 2018/2/2.
 */
public class DataSource {

    public static final String FOOTER_SUFFIX = " • footer";

    public static final int VIEW_TYPE_WECHAT_PROFILE = 0x0001;
    public static final int VIEW_TYPE_WECHAT_COMMON = 0x0002;
    public static final int VIEW_TYPE_WECHAT_SWITCH = 0x0003;
    public static final int VIEW_TYPE_WECHAT_TEXT = 0x0004;

    public static String[][] items = {
            {"第零组"},
            {"第一组", "1"},
            {"第二组", "1", "2"},
            {"第三组", "1", "2", "3"},
            {"第四组", "1", "2", "3", "4"},
            {"第五组", "1", "2", "3", "4", "5"},
            {"第六组", "1", "2", "3", "4", "5", "6"},
            {"第七组", "1", "2", "3", "4", "5", "6", "7"},
            {"第八组", "1", "2", "3", "4", "5", "6", "7", "8"},
    };

    public static MainItemConfig[][] mainItems = {
            {MainItemConfig.NULL, MainItemConfig.INSERT_REMOVE_UPDATE, MainItemConfig.EXPAND_COLLAPSE, MainItemConfig.STICKY},
            {MainItemConfig.NULL, MainItemConfig.WECHAT_ME, MainItemConfig.WECHAT_NEW_MESSAGE}
    };

    public enum MainItemConfig {
        NULL(0, null),
        INSERT_REMOVE_UPDATE(R.string.insert_remove_update, InsertRemoveUpdateActivity.class),
        EXPAND_COLLAPSE(R.string.expand_collapse, ExpandCollapseActivity.class),
        STICKY(R.string.sticky, StickyActivity.class),
        WECHAT_ME(R.string.wechat_me, WeChatMeActivity.class),
        WECHAT_NEW_MESSAGE(R.string.wechat_new_message, WeChatNewMessageActivity.class);

        public int titleId;
        public Class<?> intentClass;

        MainItemConfig(int resId, Class<?> intentClass) {
            this.titleId = resId;
            this.intentClass = intentClass;
        }
    }

    public static WeChatItemConfig[][] weChatMeItems = {
            {WeChatItemConfig.NULL, WeChatItemConfig.PROFILE},
            {WeChatItemConfig.NULL, WeChatItemConfig.WALLET},
            {WeChatItemConfig.NULL, WeChatItemConfig.COLLECT, WeChatItemConfig.PHOTO, WeChatItemConfig.CARD, WeChatItemConfig.SMILE},
            {WeChatItemConfig.NULL, WeChatItemConfig.SETTING}
    };

    public static WeChatItemConfig[][] weChatNewMessageItems = {
            {WeChatItemConfig.NULL, WeChatItemConfig.NEW_MESSAGE_NOTIFY, WeChatItemConfig.VOICE_VIDEO_NOTIFY},
            {WeChatItemConfig.NULL, WeChatItemConfig.NOTIFY_DETAIL},
            {WeChatItemConfig.NULL, WeChatItemConfig.VOICE, WeChatItemConfig.VIBRANT}
    };

    public static WeChatItemConfig[][] newMessageNotifyItemsHolder = {
            {WeChatItemConfig.NULL, WeChatItemConfig.NOTIFY_DETAIL},
            {WeChatItemConfig.NULL, WeChatItemConfig.VOICE, WeChatItemConfig.VIBRANT}
    };

    public enum WeChatItemConfig {
        NULL(0, 0, 0, 0, null),

        PROFILE(R.attr.key_profile, R.mipmap.ic_me_avatar, R.string.wechat_name, R.string.wechat_id, VIEW_TYPE_WECHAT_PROFILE, null),
        WALLET(R.attr.key_wallet, R.mipmap.ic_me_wallet, R.string.wallet, VIEW_TYPE_WECHAT_COMMON, null),
        COLLECT(R.attr.key_collect, R.mipmap.ic_me_collect, R.string.collect, VIEW_TYPE_WECHAT_COMMON, null),
        PHOTO(R.attr.key_photo, R.mipmap.ic_me_photo, R.string.photo, VIEW_TYPE_WECHAT_COMMON, null),
        CARD(R.attr.key_card, R.mipmap.ic_me_card, R.string.card, VIEW_TYPE_WECHAT_COMMON, null),
        SMILE(R.attr.key_smile, R.mipmap.ic_me_smile, R.string.smile, VIEW_TYPE_WECHAT_COMMON, null),
        SETTING(R.attr.key_setting, R.mipmap.ic_me_setting, R.string.setting, VIEW_TYPE_WECHAT_COMMON, null),

        NEW_MESSAGE_NOTIFY(R.attr.key_new_message_notify, R.string.new_message_notify, 0, VIEW_TYPE_WECHAT_SWITCH, true),
        VOICE_VIDEO_NOTIFY(R.attr.key_voice_video_notify, R.string.voice_video_notify, 0, VIEW_TYPE_WECHAT_SWITCH, true),
        NOTIFY_DETAIL(R.attr.key_notify_detail, R.string.notify_detail, R.string.notify_detail_subtitle, VIEW_TYPE_WECHAT_SWITCH, false),
        VOICE(R.attr.key_voice, R.string.voice, 0, VIEW_TYPE_WECHAT_SWITCH, false),
        NEW_MESSAGE_VOICE(R.attr.key_new_message_voice, R.string.new_message_voice, R.string.little_apple, VIEW_TYPE_WECHAT_TEXT, false),
        VIBRANT(R.attr.key_vibrant, R.string.vibrant, 0, VIEW_TYPE_WECHAT_SWITCH, false);

        public int key;
        public int imgId;
        public int titleId;
        public int subtitleId;
        public int viewType;
        public Class<?> intentClass;
        public boolean isChecked;

        WeChatItemConfig(int key, int titleId, int subtitleId, int viewType, boolean isChecked) {
            this(key, 0, titleId, subtitleId, viewType, null);
            this.isChecked = isChecked;
        }

        WeChatItemConfig(int key, int imgId, int titleId, int viewType, Class<?> intentClass) {
            this(key, imgId, titleId, 0, viewType, intentClass);
        }

        WeChatItemConfig(int key, int imgId, int titleId, int subtitleId, int viewType, Class<?> intentClass) {
            this.key = key;
            this.imgId = imgId;
            this.titleId = titleId;
            this.subtitleId = subtitleId;
            this.viewType = viewType;
            this.intentClass = intentClass;
        }
    }
}
