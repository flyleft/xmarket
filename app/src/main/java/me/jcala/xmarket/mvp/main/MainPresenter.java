package me.jcala.xmarket.mvp.main;

import android.view.View;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

public interface MainPresenter {
    void init(MaterialSearchView searchView, View header);//初始化
    void slideJump(int id);//侧边栏点击跳转
}
