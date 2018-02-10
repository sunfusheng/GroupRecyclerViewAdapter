package com.sunfusheng.adapter.sample.util;

import com.sunfusheng.adapter.sample.ExpandCollapseActivity;
import com.sunfusheng.adapter.sample.FooterActivity;
import com.sunfusheng.adapter.sample.HeaderActivity;
import com.sunfusheng.adapter.sample.HeaderFooterActivity;
import com.sunfusheng.adapter.sample.InsertRemoveUpdateActivity;
import com.sunfusheng.adapter.sample.R;
import com.sunfusheng.adapter.sample.WeChatMeActivity;

/**
 * @author sunfusheng on 2018/2/2.
 */
public class GroupData {

    public static String FOOTER_SUFFIX = "-footer";

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
            {MainItemConfig.NULL, MainItemConfig.HEADER, MainItemConfig.FOOTER, MainItemConfig.HEADER_FOOTER},
            {MainItemConfig.NULL, MainItemConfig.INSERT_REMOVE_UPDATE, MainItemConfig.EXPAND_COLLAPSE},
            {MainItemConfig.NULL, MainItemConfig.WECHAT_ME}
    };

    public enum MainItemConfig {
        NULL(0, null),
        HEADER(R.string.header, HeaderActivity.class),
        FOOTER(R.string.footer, FooterActivity.class),
        HEADER_FOOTER(R.string.header_footer, HeaderFooterActivity.class),
        INSERT_REMOVE_UPDATE(R.string.insert_remove_update, InsertRemoveUpdateActivity.class),
        EXPAND_COLLAPSE(R.string.expand_collapse, ExpandCollapseActivity.class),
        WECHAT_ME(R.string.wechat_me, WeChatMeActivity.class);

        public int titleId;
        public Class<?> intentClass;

        MainItemConfig(int resId, Class<?> intentClass) {
            this.titleId = resId;
            this.intentClass = intentClass;
        }
    }

    public static WeChatMeItemConfig[][] weChatMeItems = {
            {WeChatMeItemConfig.NULL, WeChatMeItemConfig.PROFILE},
            {WeChatMeItemConfig.NULL, WeChatMeItemConfig.WALLET},
            {WeChatMeItemConfig.NULL, WeChatMeItemConfig.COLLECT, WeChatMeItemConfig.PHOTO, WeChatMeItemConfig.CARD, WeChatMeItemConfig.SMILE},
            {WeChatMeItemConfig.NULL, WeChatMeItemConfig.SETTING}
    };

    public enum WeChatMeItemConfig {
        NULL(0, 0, 0, null),
        PROFILE(R.mipmap.ic_me_avatar, R.string.wechat_name, R.string.wechat_id, R.layout.item_wechat_me_profile, null),

        WALLET(R.mipmap.ic_me_wallet, R.string.wallet, R.layout.item_wechat_me, null),

        COLLECT(R.mipmap.ic_me_collect, R.string.collect, R.layout.item_wechat_me, null),
        PHOTO(R.mipmap.ic_me_photo, R.string.photo, R.layout.item_wechat_me, null),
        CARD(R.mipmap.ic_me_card, R.string.card, R.layout.item_wechat_me, null),
        SMILE(R.mipmap.ic_me_smile, R.string.smile, R.layout.item_wechat_me, null),

        SETTING(R.mipmap.ic_me_setting, R.string.setting, R.layout.item_wechat_me, null);

        public int imgId;
        public int titleId;
        public int subtitleId;
        public int layoutId;
        public Class<?> intentClass;

        WeChatMeItemConfig(int imgId, int titleId, int layoutId, Class<?> intentClass) {
            this(imgId, titleId, 0, layoutId, intentClass);
        }

        WeChatMeItemConfig(int imgId, int titleId, int subtitleId, int layoutId, Class<?> intentClass) {
            this.imgId = imgId;
            this.titleId = titleId;
            this.subtitleId = subtitleId;
            this.layoutId = layoutId;
            this.intentClass = intentClass;
        }
    }
}
