package me.jcala.xmarket.mvp.team.add;

import android.content.Intent;
import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.jcala.xmarket.R;
import me.jcala.xmarket.mvp.a_base.BaseActivity;
import me.jcala.xmarket.mvp.main.MainActivity;

public class TeamAddActivity extends BaseActivity implements TeamAddView{

    private Unbinder unbinder;
    TeamAddPresenter presenter;
    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.team_add_activity);
        unbinder=ButterKnife.bind(this);
        presenter=new TeamAddPresenterImpl(this,this);
    }

    @OnClick(R.id.team_add_cancel)
    void clickBack(){
        Intent intent=new Intent(TeamAddActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.trade_add_submit)
    void clickSubmit(){

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
