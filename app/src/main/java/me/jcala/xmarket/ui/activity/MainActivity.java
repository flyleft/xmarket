package me.jcala.xmarket.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import me.jcala.xmarket.R;
import me.jcala.xmarket.ui.base.BaseActivity;
import me.jcala.xmarket.ui.fragment.SortFrgment;
import me.jcala.xmarket.ui.fragment.TeamFragment;

public class MainActivity  extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    Toolbar toolbar;
    TextView toolbarTitle;
    BottomNavigationBar mBottomNavigationBar;
    DrawerLayout drawer;
    NavigationView navigationView;
    private TeamFragment teamFragment;
    private FragmentManager fm;
    private SortFrgment sortFrgment;
    @Override
    protected void initVariables() {
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.main_activity);
        fm = getFragmentManager();
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbarTitle=(TextView)findViewById(R.id.toolbar_title);
        mBottomNavigationBar=(BottomNavigationBar)findViewById(R.id.bottom_navigation_bar);
        drawer=(DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView=(NavigationView)findViewById(R.id.nav_view);
        initSlide();
        initButtomMenu();
    }
    private void initSlide(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }
    private void initButtomMenu(){
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .addItem(new BottomNavigationItem(R.mipmap.menu_school, "本校").setActiveColorResource(R.color.black))
                .addItem(new BottomNavigationItem(R.mipmap.menu_sort, "分类").setActiveColorResource(R.color.black))
                .addItem(new BottomNavigationItem(R.mipmap.menu_team, "志愿队").setActiveColorResource(R.color.black))
                .addItem(new BottomNavigationItem(R.mipmap.menu_message, "消息").setActiveColorResource(R.color.black))
                .setFirstSelectedPosition(0)
                .initialise();
        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                switch (position) {
                    case 0:
                        toolbarTitle.setText(R.string.MainActivity_title_school);
                        showFragment(0);
                        break;
                    case 1:
                        toolbarTitle.setText(R.string.MainActivity_title_sort);
                        showFragment(1);
                        break;
                    case 2:
                        toolbarTitle.setText(R.string.MainActivity_title_team);
                        showFragment(2);
                        break;
                    case 3:
                        toolbarTitle.setText(R.string.MainActivity_title_message);
                        showFragment(3);
                        break;
                }

            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
        toolbarTitle.setText(R.string.MainActivity_title_school);
        showFragment(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_top, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showFragment(int position) {
        FragmentTransaction ft = fm.beginTransaction();
        hideAllFragment(ft);
        switch (position) {
            case 1 : if (sortFrgment != null) {
                ft.show(sortFrgment);
            } else {
                sortFrgment = new SortFrgment();
                ft.add(R.id.frame_layout, sortFrgment);
            }
                break;
            case 3 : if (teamFragment != null) {
                        ft.show(teamFragment);
                    } else {
                teamFragment = new TeamFragment();
                        ft.add(R.id.frame_layout, teamFragment);
                    }
                break;
        }
        ft.commit();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }

    private void hideAllFragment(FragmentTransaction ft) {
        /*if (mNewsListFragment != null) {
            ft.hide(mNewsListFragment);
        }
        if (mGirlsFragment != null) {
            ft.hide(mGirlsFragment);
        }
        if (mFeatureListFragment != null) {
            ft.hide(mFeatureListFragment);
        }*/
        if (sortFrgment != null) {
            ft.hide(sortFrgment);
        }
        if (teamFragment != null) {
            ft.hide(teamFragment);
        }
    }

}
