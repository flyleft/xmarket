package me.jcala.xmarket.mvp.main;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.facebook.drawee.view.SimpleDraweeView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jcala.xmarket.AppConf;
import me.jcala.xmarket.R;
import me.jcala.xmarket.data.pojo.User;
import me.jcala.xmarket.data.storage.UserIntermediate;
import me.jcala.xmarket.mvp.message.MessageFragment;
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

public class MainPresenterImpl implements MainPresenter {

    private AppCompatActivity context;
    private TeamFragment teamFragment;
    private TradeTagFragment tradeTagFragment;
    private SchoolFragment schoolFragment;
    private MessageFragment messageFragment;
    private FragmentManager fm;
    TextView toolbarTitle;
    BottomNavigationBar mBottomNavigationBar;
    public MainPresenterImpl(AppCompatActivity context) {
        this.context = context;
        fm = context.getFragmentManager();
        toolbarTitle=(TextView)context.findViewById(R.id.toolbar_title);
        mBottomNavigationBar=(BottomNavigationBar)context.findViewById(R.id.bottom_navigation_bar);
    }

    @Override
    public void init(MaterialSearchView searchView, View header) {
        initHeader(header);
        initSearchView(searchView);
        initBottomMenu();
    }

    public void initSearchView(MaterialSearchView searchView) {
        searchView.setVoiceSearch(false);
        searchView.setCursorDrawable(R.drawable.color_cursor_white);
        searchView.setSuggestions(context.getResources().getStringArray(R.array.query_suggestions));
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Snackbar.make(context.findViewById(R.id.toolbar_container), "Query: " + query, Snackbar.LENGTH_LONG)
                        .show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });
    }

    public void initHeader(View headerLayout) {
        User user= UserIntermediate.instance.getUser(context);
        TextView username=(TextView) headerLayout.findViewById(R.id.info_username);
        TextView phone=(TextView) headerLayout.findViewById(R.id.info_phone);
        SimpleDraweeView avatar=(SimpleDraweeView) headerLayout.findViewById(R.id.info_avatar);
        username.setText(user.getUsername());
        phone.setText(user.getPhone());
        avatar.setImageURI(Uri.parse(AppConf.BASE_URL+user.getAvatarUrl()));
    }

    public void initBottomMenu() {
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);
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

    public void hideAllFragment(FragmentTransaction ft) {
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

    @Override
    public void slideJump(int id) {
        switch (id){
            case R.id.info_uncomplete:
                Intent unCompleteIntent=new Intent(context, TradeUnCompleteActivity.class);
                context.startActivity(unCompleteIntent);
                break;

            case R.id.info_sell:
                Intent sellIntent=new Intent(context, TradeSellActivity.class);
                context.startActivity(sellIntent);
                break;

            case R.id.info_bought:
                Intent boughtIntent=new Intent(context, TradeBoughtActivity.class);
                context.startActivity(boughtIntent);
                break;

            case R.id.info_sold:
                Intent soldIntent=new Intent(context, TradeSoldActivity.class);
                context.startActivity(soldIntent);
                break;

            case R.id.info_donation:
                Intent donateIntent=new Intent(context, TradeDonateActivity.class);
                context.startActivity(donateIntent);
                break;

            case R.id.info_team:
                Intent teamIntent=new Intent(context, UserTeamActivity.class);
                context.startActivity(teamIntent);
                break;

            case R.id.info_logout:
                UserIntermediate.instance.logOut(context);
                Intent loginIntent=new Intent(context, LoginRegisterActivity.class);
                context.startActivity(loginIntent);
                context.finish();
                break;
            case R.id.info_author:
                Uri uri=Uri.parse("https://github.com/jcalaz");
                Intent uriIntent=new Intent(Intent.ACTION_VIEW,uri);
                context.startActivity(uriIntent);
                break;
            default:break;
        }

    }
}
