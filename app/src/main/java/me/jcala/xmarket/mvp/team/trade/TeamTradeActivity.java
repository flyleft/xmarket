package me.jcala.xmarket.mvp.team.trade;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.jcala.xmarket.R;
import me.jcala.xmarket.mvp.a_base.BaseActivity;
import me.jcala.xmarket.view.RecyclerCommonAdapter;

public class TeamTradeActivity extends BaseActivity implements TeamTradeView{

    @BindView(R.id.team_trade_list)
    protected RecyclerView recyclerView;
    @BindView(R.id.team_trade_title)
    TextView title;

    private Unbinder unbinder;
    TeamTradePresenter presenter;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.team_trade_activity);
        unbinder=ButterKnife.bind(TeamTradeActivity.this);
        presenter=new TeamTradePresenterImpl(this,this);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String team=bundle.getString("team");
        if (team==null || team.isEmpty()){
            return;
        }
        title.setText(team);
        LinearLayoutManager layoutManager = new LinearLayoutManager(TeamTradeActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        presenter.initView(team.trim());
    }

    @Override
    public void whenLoadDataSuc(RecyclerCommonAdapter<?> adapter) {
       recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
