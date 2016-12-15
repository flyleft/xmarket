package me.jcala.xmarket.mvp.sort.trades;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jcala.xmarket.R;
import me.jcala.xmarket.mvp.a_base.BaseActivity;
import me.jcala.xmarket.view.RecyclerCommonAdapter;

public class TradeTagDetailActivity extends BaseActivity implements TradeTagDetailView{

    @BindView(R.id.trade_tag_detail_list)
    protected RecyclerView recyclerView;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.trade_tag_detail_activity);
        ButterKnife.bind(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(TradeTagDetailActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void whenLoadDataSuc(RecyclerCommonAdapter<?> adapter) {

    }

    @Override
    public void whenLoadDataFail(String errorMsg) {

    }
}
