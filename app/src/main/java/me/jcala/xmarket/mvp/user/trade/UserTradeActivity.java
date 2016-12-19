package me.jcala.xmarket.mvp.user.trade;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.jcala.xmarket.R;
import me.jcala.xmarket.mvp.a_base.BaseActivity;
import me.jcala.xmarket.mvp.main.MainActivity;
import me.jcala.xmarket.view.RecyclerCommonAdapter;

public class UserTradeActivity extends BaseActivity implements UserTradeView{

    @BindView(R.id.user_trade_toolbar_title)
    TextView textView;
    @BindView(R.id.user_trade_list)
    private RecyclerView recyclerView;
    private Unbinder unbinder;
    private int type;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.user_trade_activity);
        unbinder=ButterKnife.bind(this);
        initData();
    }

    private void initData(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(UserTradeActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        type=bundle.getInt("type",0);
        switch (type){
            case 0:textView.setText("");break;
            case 1:textView.setText("");break;
            case 2:textView.setText("");break;
            case 3:textView.setText("");break;
            case 4:textView.setText("");break;
            default:
        }
    }

    @OnClick(R.id.user_trade_toolbar_back)
    void clickBack(){
        Intent intent=new Intent(UserTradeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void whenLoadDataSuccess(RecyclerCommonAdapter<?> adapter) {

    }

    @Override
    public void whenStartProgress() {

    }

    @Override
    public void whenStopProgress() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
