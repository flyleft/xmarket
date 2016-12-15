package me.jcala.xmarket.mvp.main;

import android.view.View;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

public interface MainPresenter {
    void  initSearchView(MaterialSearchView searchView);
    void  initHeader(View headerLayout);
    void initBottomMenu(BottomNavigationBar bar,TextView toolbarTitle);
    void slideJump(int id);
}
