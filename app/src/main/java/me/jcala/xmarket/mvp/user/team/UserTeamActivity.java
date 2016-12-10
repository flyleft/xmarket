package me.jcala.xmarket.mvp.user.team;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.jcala.xmarket.R;
import me.jcala.xmarket.data.pojo.Team;
import me.jcala.xmarket.mvp.a_base.BaseActivity;
import me.jcala.xmarket.mvp.main.MainActivity;
import me.jcala.xmarket.view.RecyclerCommonAdapter;

public class UserTeamActivity extends BaseActivity implements UserTeamView{

    private Unbinder unbinder;
    @BindView(R.id.user_team_list)
    RecyclerView recyclerView;
    UserTeamPresenter presenter;

    @Override
    protected void initViews(Bundle savedInstanceState) {
          setContentView(R.layout.user_team_activity);
          unbinder= ButterKnife.bind(this);
          presenter=new UserTeamPresenterImpl(this,this);
          presenter.gainUserTeamList();
          LinearLayoutManager layoutManager = new LinearLayoutManager(this);
          recyclerView.setLayoutManager(layoutManager);
          recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void whenGetUserTeamSuccess(RecyclerCommonAdapter<?> adapter) {
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.user_team_back)
    void clickBack(){
        Intent intent=new Intent(UserTeamActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
