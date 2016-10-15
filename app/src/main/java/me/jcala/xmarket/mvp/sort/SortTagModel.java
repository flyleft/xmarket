package me.jcala.xmarket.mvp.sort;

import java.util.List;

import me.jcala.xmarket.data.entity.SortTag;

public interface SortTagModel {
    interface OnGetSortTagListener{
        void onSuccess(List<SortTag> sortTagList);
        void onFailure(String errMsg);
    }
    void getSortTag(OnGetSortTagListener listener);
}
