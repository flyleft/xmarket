package me.jcala.xmarket.mvp.main;

import android.content.Context;

public class MainPresenterImpl
        implements MainPresenter,MainModel.onMianListener{
    private Context context;
    private MainModel model;
    private MainView view;

    public MainPresenterImpl(Context context, MainView view) {
        this.context = context;
        this.view = view;
        this.model=new MainModelImpl();
    }
}
