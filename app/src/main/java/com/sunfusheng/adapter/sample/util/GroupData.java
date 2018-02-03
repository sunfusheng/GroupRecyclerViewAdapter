package com.sunfusheng.adapter.sample.util;

import com.sunfusheng.adapter.sample.FooterActivity;
import com.sunfusheng.adapter.sample.HeaderActivity;
import com.sunfusheng.adapter.sample.HeaderFooterActivity;
import com.sunfusheng.adapter.sample.R;

/**
 * @author sunfusheng on 2018/2/2.
 */
public class GroupData {

    public static String[][] items = {
            {"第一组", "1", "2", "3"},
            {"第二组", "1", "2"},
            {"第三组", "1", "2", "3", "4", "5"},
            {"第四组", "1", "2", "3", "4"},
            {"第五组", "1", "2"},
            {"第六组", "1", "2", "3", "4", "5", "6"},
            {"第七组", "1"},
            {"第八组", "1", "2", "3", "4"},
    };

    public static MainItemConfig[][] mainItems = {
            {MainItemConfig.NULL, MainItemConfig.HEADER, MainItemConfig.FOOTER, MainItemConfig.HEADER_FOOTER},
            {MainItemConfig.NULL, MainItemConfig.ADD_DELETE_UPDATE},
            {MainItemConfig.NULL, MainItemConfig.WECHAT_MINE}
    };

    public enum MainItemConfig {
        NULL(0, null),
        HEADER(R.string.header, HeaderActivity.class),
        FOOTER(R.string.footer, FooterActivity.class),
        HEADER_FOOTER(R.string.header_footer, HeaderFooterActivity.class),
        ADD_DELETE_UPDATE(R.string.add_delete_update, null),
        WECHAT_MINE(R.string.wechat_mine, null);

        public int titleId;
        public Class<?> intentClass;

        MainItemConfig(int resId, Class<?> cls) {
            this.titleId = resId;
            this.intentClass = cls;
        }
    }

    public enum ItemConfig {
        ;

        private int itemId;
        private int imgId;
        private int titleId;
        private int subtitleId;
        private int layoutId;
        private boolean showArrow;

        ItemConfig(int itemId, int imgId, int titleId, int subtitleId, int layoutId, boolean showArrow) {
            this.itemId = itemId;
            this.imgId = imgId;
            this.titleId = titleId;
            this.subtitleId = subtitleId;
            this.layoutId = layoutId;
            this.showArrow = showArrow;
        }
    }
}
