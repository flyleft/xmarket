package me.jcala.xmarket.mvp.trade;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jcala.xmarket.R;
import me.jcala.xmarket.mvp.a_base.BaseActivity;
import me.jcala.xmarket.mvp.main.MainActivity;
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


    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.trade_detail_activity);
        ButterKnife.bind(this);
        presenter=new TradeDetailPresenterImpl(this,this);
        initData();
    }

    @SuppressWarnings("unchecked")
    private void initData(){
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String tradeId=bundle.getString("tradeId");
        presenter.loadData(tradeId);
        List<String> imgList=new ArrayList<>();
        banner.setPlayDelay(2000);
        banner.setAnimationDurtion(500);
        banner.setAdapter(new BannerAdapter(imgList));
        banner.setHintView(new ColorPointHintView(this, Color.BLACK,Color.WHITE));
        //avatarImg.setImageURI(Uri.parse("https://jcalaz.github.io/img/sort_rent.jpg"));
    }

    @Override
    public void whenSuccess() {

    }

    @Override
    public void whenFail(String errorMsg) {

    }


    @OnClick(R.id.trade_detail_toolbar_back)
    void clickBack(){
        Intent intent=new Intent(TradeDetailActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
