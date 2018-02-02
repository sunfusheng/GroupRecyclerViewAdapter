package com.sunfusheng.adapter.sample;

/**
 * @author sunfusheng on 2018/2/2.
 */
public class GroupConfig {

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
