package me.jcala.xmarket.mvp.team;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.realm.Realm;
import me.jcala.xmarket.R;
import me.jcala.xmarket.mvp.a_base.BaseFragment;
import me.jcala.xmarket.mvp.team.add.TeamAddActivity;
import me.jcala.xmarket.view.RecyclerCommonAdapter;

public class TeamFragment extends BaseFragment implements TeamView {
    private Unbinder unbinder;

    @BindView(R.id.team_list)
    protected RecyclerView recyclerView;
    @BindView(R.id.team_list_refresh)
    SwipeRefreshLayout refreshLayout;
    private TeamPresenter presenter;
    private Realm realm;

    @Override
    protected int getLayoutResId() {
        return R.layout.team_fragment;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        unbinder= ButterKnife.bind(this,view);
        realm=Realm.getDefaultInstance();
        presenter=new TeamPresenterImpl(getActivity(),this);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light,
                android.R.color.holo_orange_light, android.R.color.holo_green_light);
        refreshLayout.setOnRefreshListener(()->presenter.refreshView(realm));
        presenter.initView(realm);
    }

    @Override
    public void whenHideRefresh() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void whenGetTeamSuc(RecyclerCommonAdapter<?> adapter) {
       recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.school_deal_plus)
    void clickAddTeam(){
        Intent intent=new Intent(getActivity(), TeamAddActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        realm.close();
    }

}
