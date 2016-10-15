package me.jcala.xmarket.mvp.sort;

import java.util.List;

import me.jcala.xmarket.data.entity.SortTag;

public class SortTagPreImpl implements SortTagPre,SortTagModel.OnGetSortTagListener{
    private SortTagModel mModel;
    private SortTagView mView;
    public SortTagPreImpl(SortTagView view){
        mView = view;
        mModel = new SortTagModelImpl();
    }

    @Override
    public void doGetSortTag() {
        mModel.getSortTag(this);
    }

    @Override
    public void onSuccess(List<SortTag> sortTagList) {
        mView.whenSuccess(sortTagList);
    }

    @Override
    public void onFailure(String errMsg) {
        mView.whenFail(errMsg);
    }
}
