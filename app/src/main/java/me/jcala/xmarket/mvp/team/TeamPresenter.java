package me.jcala.xmarket.mvp.team;

import io.realm.Realm;

public interface TeamPresenter {
    void initView(Realm realm);
    void refreshView(Realm realm);
}
