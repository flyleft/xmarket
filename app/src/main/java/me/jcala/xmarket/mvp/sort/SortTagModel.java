package me.jcala.xmarket.mvp.sort;

import java.util.List;

import me.jcala.xmarket.data.pojo.SortTag;

interface SortTagModel {
    interface onGainListener {
        void onSuccess(List<SortTag> sortTagList);
        void onFailure(String errMsg);
    }
    void getSortTag(final onGainListener listener);
}
