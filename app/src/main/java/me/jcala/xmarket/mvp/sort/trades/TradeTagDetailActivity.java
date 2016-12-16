package me.jcala.xmarket.mvp.sort.trades;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jcala.xmarket.R;
import me.jcala.xmarket.mvp.a_base.BaseActivity;
import me.jcala.xmarket.mvp.main.MainActivity;
import me.jcala.xmarket.view.RecyclerCommonAdapter;

public class TradeTagDetailActivity extends BaseActivity implements TradeTagDetailView{

    @BindView(R.id.trade_tag_detail_list)
    protected RecyclerView recyclerView;
    TradeTagDetailPresenter presenter;
    private String tagName;
    @BindView(R.id.trade_tag_title)
    TextView title;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.trade_tag_detail_activity);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        tagName=bundle.getString("tagName");
        title.setText(tagName);
        LinearLayoutManager layoutManager = new LinearLayoutManager(TradeTagDetailActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        presenter=new TradeTagDetailPresenterImpl(this,this);
        if (tagName!=null){
            presenter.getTradeListByTag(tagName);
        }
    }

    @Override
    public void whenLoadDataSuc(RecyclerCommonAdapter<?> adapter) {
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.trade_tag_back)
    void clickBack(){
        Intent intent=new Intent(TradeTagDetailActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void whenLoadDataFail(String errorMsg) {
        new SuperToast(TradeTagDetailActivity.this)
                .setText(errorMsg)
                .setDuration(Style.DURATION_LONG)
                .setColor(PaletteUtils.getTransparentColor(PaletteUtils.MATERIAL_RED))
                .setAnimations(Style.ANIMATIONS_POP)
                .show();
    }
}
