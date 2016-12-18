package me.jcala.xmarket.mvp.school;


import io.realm.Realm;

public interface SchoolPresenter {

    void initView(Realm realm);

    void refreshView(Realm realm);

}
