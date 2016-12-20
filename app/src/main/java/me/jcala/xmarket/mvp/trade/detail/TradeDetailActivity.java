package me.jcala.xmarket.mvp.trade.detail;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.facebook.drawee.view.SimpleDraweeView;
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.jcala.xmarket.AppConf;
import me.jcala.xmarket.R;
import me.jcala.xmarket.data.pojo.Trade;
import me.jcala.xmarket.data.storage.UserIntermediate;
import me.jcala.xmarket.mvp.a_base.BaseActivity;
import me.jcala.xmarket.mvp.main.MainActivity;
import me.jcala.xmarket.util.TimeUtils;
import me.jcala.xmarket.view.BannerAdapter;

public class TradeDetailActivity extends BaseActivity implements TradeDetailView{


    TradeDetailPresenter presenter;
    @BindView(R.id.trade_detail_banner)
    RollPagerView banner;
    @BindView(R.id.trade_detail_author_img_url)
    SimpleDraweeView avatarImg;
    @BindView(R.id.trade_detail_author_name)
    TextView avatarName;
    @BindView(R.id.trade_detail_toolbar_title)
    TextView tradeName;
    @BindView(R.id.trade_detail_toolbar_price)
    TextView tradePrice;
    @BindView(R.id.trade_detail_desc)
    TextView tradeDesc;
    @BindView(R.id.trade_detail_time)
    TextView time;
    @BindView(R.id.trade_detail_school)
    TextView school;
    @BindView(R.id.trade_detail_submit)
    TextView submit;

    private MaterialDialog progress;
    private String tradeImg;
    String tradeId;
    String userId;
    int status;
    private Unbinder unbinder;


    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.trade_detail_activity);
        unbinder=ButterKnife.bind(this);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        tradeId=bundle.getString("tradeId");
        userId=bundle.getString("userId");
        status=bundle.getInt("status",0);
        if (tradeId==null||userId==null){
            return;
        }

        presenter=new TradeDetailPresenterImpl(this,this);
        initData();
    }

    @SuppressWarnings("unchecked")
    private void initData(){
        presenter.loadData(tradeId);
        banner.setPlayDelay(2000);
        banner.setAnimationDurtion(500);
        banner.setHintView(new ColorPointHintView(this, Color.BLACK,Color.WHITE));

        String localUserId=UserIntermediate.instance.getUser(TradeDetailActivity.this).getId();
        if (userId.equals(localUserId)){
            if (status==0){
                submit.setText("我要捐赠");
            }else {
                submit.setVisibility(View.VISIBLE);
            }
        }

        progress=new MaterialDialog.Builder(TradeDetailActivity.this)
                .content(R.string.login_dialog_content)
                .progress(true, 0)
                .progressIndeterminateStyle(false)
                .title(R.string.dialog_wait)
                .build();

        submit.setOnClickListener((View v) ->{
                String myId= UserIntermediate.instance.getUser(TradeDetailActivity.this).getId();
            if (!userId.equals(myId)){
                new MaterialDialog.Builder(TradeDetailActivity.this)
                        .title(R.string.trade_detail_dialog_title)
                        .content(R.string.trade_detail_dialog_content)
                        .positiveText(R.string.trade_detail_dialog_agree)
                        .negativeText(R.string.trade_detail_dialog_disagree)
                        .onPositive((MaterialDialog dialog,DialogAction which) ->{
                            presenter.buyTrade(tradeId);
                        })
                        .show();
            }else if (status==0){
                presenter.loadTeamData();
            }
        });
    }

    @Override
    public void whenLoadTradeSuccess(Trade trade) {
        List<String> imgUrls=new ArrayList<>();
        for (String str:trade.getImgUrls()){
            imgUrls.add(AppConf.BASE_URL+str);
        }
        banner.setAdapter(new BannerAdapter(imgUrls));
        school.setText(trade.getSchoolName());
        time.setText(TimeUtils.timeDiff(trade.getCreateTime()));
        avatarName.setText(trade.getAuthor().getUsername());
        avatarImg.setImageURI(AppConf.BASE_URL+trade.getAuthor().getAvatarUrl());
        tradeName.setText(trade.getTitle());
        tradePrice.setText("￥ "+trade.getPrice());
        tradeDesc.setText("     "+trade.getDesc());
        tradeImg=trade.getImgUrls().get(0);
    }

    @Override
    public void whenLoadTeamSuccess(List<String> teams) {
        new MaterialDialog.Builder(this)
                .title(R.string.trade_detail_team_choose)
                .items(teams)
                .itemsCallbackSingleChoice(0,
                        (MaterialDialog dialog, View view, int which, CharSequence text)->{
                            presenter.donateTrade(tradeId,tradeImg,text.toString());
                            return true;
                        })
                .positiveText(R.string.choose)
                .show();
    }

    @Override
    public void whenFail(String errorMsg) {
        new SuperToast(TradeDetailActivity.this)
                .setText(errorMsg)
                .setDuration(Style.DURATION_LONG)
                .setColor(PaletteUtils.getTransparentColor(PaletteUtils.MATERIAL_RED))
                .setAnimations(Style.ANIMATIONS_FLY)
                .show();
    }

    @OnClick(R.id.trade_detail_toolbar_back)
    void clickBack(){
        Intent intent=new Intent(TradeDetailActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void whenShowProgress() {
            progress.show();
    }

    @Override
    public void whenHideProgress() {
           progress.dismiss();
    }

    @Override
    public void whenBuySuccess() {
        //toast购买成功
        Intent intent=new Intent(TradeDetailActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void whenDonateSuccess() {
        //toast捐赠成功
        Intent intent=new Intent(TradeDetailActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
