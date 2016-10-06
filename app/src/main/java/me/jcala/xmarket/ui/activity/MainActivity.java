package me.jcala.xmarket.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import me.jcala.xmarket.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.jcala.xmarket.ui.base.BaseActivity;
import me.jcala.xmarket.ui.fragment.AboutFragment;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;

    private AboutFragment mAboutFragment;
    private FragmentManager fm;

    @Override
    protected void initVariables() {
        fm = getFragmentManager();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        try {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .addItem(new BottomNavigationItem(R.mipmap.ic_news_24dp, "本校").setActiveColorResource(R.color.red))
                .addItem(new BottomNavigationItem(R.mipmap.ic_photo_24dp, "分类").setActiveColorResource(R.color.red))
                .addItem(new BottomNavigationItem(R.mipmap.ic_video_24dp, "募捐").setActiveColorResource(R.color.red))
                .addItem(new BottomNavigationItem(R.mipmap.ic_about_me, "志愿队").setActiveColorResource(R.color.red))
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
                        toolbarTitle.setText(R.string.MainActivity_title_donation);
                        showFragment(2);
                        break;
                    case 3:
                        toolbarTitle.setText(R.string.MainActivity_title_team);
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
    protected void loadData() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            case 3 : if (mAboutFragment != null) {
                        ft.show(mAboutFragment);
                    } else {
                        mAboutFragment = new AboutFragment();
                        ft.add(R.id.frame_layout, mAboutFragment);
                    }
                break;
        }
        ft.commit();
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
        if (mAboutFragment != null) {
            ft.hide(mAboutFragment);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
    }
}
