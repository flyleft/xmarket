package me.jcala.xmarket.mvp.school;

import android.content.Context;

public class SchoolPresenterImpl implements SchoolModel.onGainListener,SchoolPresenter {
    private SchoolModel model;
    private SchoolView view;
    private Context context;

    public SchoolPresenterImpl(Context context,SchoolView view) {
        this.context = context;
        this.view = view;
        this.model=new SchoolModelImpl();
    }

    @Override
    public void success() {

    }

    @Override
    public void fail() {

    }

    @Override
    public void getSchoolDealAgency() {
       model.getSchoolDeals(1,this);
    }
}
