package me.jcala.xmarket.mvp.main;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jcala.xmarket.AppConf;
import me.jcala.xmarket.R;
import me.jcala.xmarket.data.storage.UserIntermediate;
import me.jcala.xmarket.mvp.a_base.BaseActivity;
import me.jcala.xmarket.mvp.message.MessageFragment;
import me.jcala.xmarket.mvp.message.MessageService;
import me.jcala.xmarket.mvp.school.SchoolFragment;
import me.jcala.xmarket.mvp.sort.TradeTagFragment;
import me.jcala.xmarket.mvp.team.TeamFragment;
import me.jcala.xmarket.mvp.user.login.LoginRegisterActivity;
import me.jcala.xmarket.mvp.user.team.UserTeamActivity;
import me.jcala.xmarket.mvp.user.trades.bought.TradeBoughtActivity;
import me.jcala.xmarket.mvp.user.trades.donate.TradeDonateActivity;
import me.jcala.xmarket.mvp.user.trades.sell.TradeSellActivity;
import me.jcala.xmarket.mvp.user.trades.sold.TradeSoldActivity;
import me.jcala.xmarket.mvp.user.trades.uncomplete.TradeUnCompleteActivity;
import me.jcala.xmarket.util.PollingUtils;

public class MainActivity  extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,MainView{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.search_view)
    MaterialSearchView searchView;
    private TeamFragment teamFragment;
    private FragmentManager fm;
    private TradeTagFragment tradeTagFragment;
    private SchoolFragment schoolFragment;
    private MessageFragment messageFragment;
    private MainPresenter presenter;
    public static String ACTION_UPDATE_UI = "main.update.ui";
    private BroadcastReceiver receiver;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.main_activity);
        initService();
        ButterKnife.bind(this);
        presenter=new MainPresenterImpl(this,this);
        fm = getFragmentManager();
        initSlide();
        presenter.initBottomMenu(mBottomNavigationBar,toolbarTitle);
    }

    private void initService(){
        PollingUtils.startPollingService(this, 5, MessageService.class, MessageService.ACTION);
        IntentFilter filter = new IntentFilter(ACTION_UPDATE_UI);
        receiver=new MainBroadcastReceiver();
        registerReceiver(receiver, filter);
    }

    private void initSlide(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        View headerLayout= navigationView.inflateHeaderView(R.layout.main_slide);
        presenter.initHeader(headerLayout);
        presenter.initSearchView(searchView);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        presenter.slideJump(id);
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_top, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
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

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    searchView.setQuery(searchWrd, false);
                }
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showFragment(int position) {
        FragmentTransaction ft = fm.beginTransaction();
        hideAllFragment(ft);
        switch (position) {
            case 0 : if (schoolFragment != null) {
                ft.show(schoolFragment);
            } else {
                schoolFragment = new SchoolFragment();
                ft.add(R.id.frame_layout, schoolFragment);
            }
                break;
            case 1 : if (tradeTagFragment != null) {
                ft.show(tradeTagFragment);
            } else {
                tradeTagFragment = new TradeTagFragment();
                ft.add(R.id.frame_layout, tradeTagFragment);
            }
                break;
            case 2 : if (teamFragment != null) {
                        ft.show(teamFragment);
                    } else {
                teamFragment = new TeamFragment();
                        ft.add(R.id.frame_layout, teamFragment);
                    }
                break;
            case 3 : if (messageFragment != null) {
                ft.show(messageFragment);
            } else {
                messageFragment = new MessageFragment();
                ft.add(R.id.frame_layout, messageFragment);
            }
                break;
        }
        ft.commit();
    }

    private void hideAllFragment(FragmentTransaction ft) {
        if (schoolFragment != null) {
            ft.hide(schoolFragment);
        }
        if (tradeTagFragment != null) {
            ft.hide(tradeTagFragment);
        }
        if (teamFragment != null) {
            ft.hide(teamFragment);
        }
        if (messageFragment != null) {
            ft.hide(messageFragment);
        }
    }
    public class MainBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (AppConf.useMock){
                return;
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        PollingUtils.stopPollingService(this, MessageService.class, MessageService.ACTION);
        unregisterReceiver(receiver);
    }
}
