package me.jcala.xmarket.mvp.team.add;

import android.os.Bundle;

import butterknife.ButterKnife;
import me.jcala.xmarket.R;
import me.jcala.xmarket.mvp.a_base.BaseActivity;

public class TeamAddActivity extends BaseActivity implements TeamAddView{

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.team_add_activity);
        ButterKnife.bind(this);
    }

}
