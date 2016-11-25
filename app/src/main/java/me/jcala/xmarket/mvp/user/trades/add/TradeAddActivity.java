package me.jcala.xmarket.mvp.user.trades.add;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jcala.xmarket.R;
import me.jcala.xmarket.data.pojo.Trade;
import me.jcala.xmarket.data.pojo.TradeTag;
import me.jcala.xmarket.mvp.a_base.BaseActivity;
import me.jcala.xmarket.mvp.main.MainActivity;

public class TradeAddActivity extends BaseActivity implements TradeAddView{

    TradeAddPresenter presenter;
    Trade trade;

    private List<TradeTag> tags;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.trade_add_activity);
        ButterKnife.bind(this);
        initData();
    }

    private void initData(){
        presenter=new TradeAddPresenterImpl(this,this);
       // presenter.tradeAdd(trade);
    }

    @Override
    public void whenAddSuccess() {
        Intent intent=new Intent(TradeAddActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void whenFail(String errorMsg) {
        new SuperToast(TradeAddActivity.this)
                .setText(errorMsg)
                .setDuration(Style.DURATION_LONG)
                .setColor(PaletteUtils.getTransparentColor(PaletteUtils.MATERIAL_RED))
                .setAnimations(Style.ANIMATIONS_POP)
                .show();
    }

    @OnClick(R.id.trade_add_tag_select)
    public void showSchoolChoice() {
        new MaterialDialog.Builder(this)
                .title(R.string.register_next_school_choose)
                .items(tags)
                .itemsCallbackSingleChoice(0,
                        (MaterialDialog dialog, View view, int which, CharSequence text)->{
                            //schoolName.setText(text);
                            return true;
                        })
                .positiveText(R.string.register_next_choose)
                .show();
    }

}
