package me.jcala.xmarket.mvp.user.trade;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

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
    RecyclerView recyclerView;
    UserTradePresenter presenter;
    private MaterialDialog progress;
    private Unbinder unbinder;
    private int type;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.user_trade_activity);
        unbinder=ButterKnife.bind(this);
        presenter=new UserTradePresenterImpl(this,this);
        initData();
    }

    private void initData(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(UserTradeActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        progress=new MaterialDialog.Builder(UserTradeActivity.this)
                .content(R.string.user_trade_dialog_content)
                .progress(true, 0)
                .progressIndeterminateStyle(false)
                .title(R.string.dialog_wait)
                .build();

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        type=bundle.getInt("type",0);
        switch (type){
            case 0:textView.setText("待确认");break;
            case 1:textView.setText("待售");break;
            case 2:textView.setText("已买");break;
            case 3:textView.setText("已卖");break;
            case 4:textView.setText("捐赠");break;
            default:break;
        }
        presenter.initViewList(type);
    }

    @OnClick(R.id.user_trade_toolbar_back)
    void clickBack(){
        Intent intent=new Intent(UserTradeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void whenLoadDataSuccess(RecyclerCommonAdapter<?> adapter) {
       recyclerView.setAdapter(adapter);
    }

    @Override
    public void whenStartProgress() {
        progress.show();
    }

    @Override
    public void whenStopProgress() {
       progress.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
