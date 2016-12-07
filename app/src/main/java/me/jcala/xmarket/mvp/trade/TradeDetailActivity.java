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
import me.jcala.xmarket.R;
import me.jcala.xmarket.mvp.a_base.BaseActivity;

public class TradeDetailActivity extends BaseActivity implements TradeDetailView{


    @BindView(R.id.trade_detail_banner)
    protected RollPagerView banner;

    @BindView(R.id.trade_detail_author_img)
    SimpleDraweeView avatarImg;
    @BindView(R.id.trade_detail_author_name)
    TextView avatarName;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.trade_detail_activity);
        ButterKnife.bind(this);
        initData();
    }

    @SuppressWarnings("unchecked")
    private void initData(){
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String tradeId=bundle.getString("tradeId");
        List<String> imgList=new ArrayList<>();
        avatarName.setText("jcala");
        imgList.add("https://jcalaz.github.io/img/sort_clothes.jpeg");
        imgList.add("https://jcalaz.github.io/img/sort_body.jpg");
        imgList.add("https://jcalaz.github.io/img/sort_computer.jpg");
        imgList.add("https://jcalaz.github.io/img/sort_book.jpg");
        banner.setPlayDelay(2000);
        banner.setAnimationDurtion(500);
        banner.setAdapter(new TestNormalAdapter(imgList));
        banner.setHintView(new ColorPointHintView(this, Color.BLACK,Color.WHITE));
        avatarImg.setImageURI(Uri.parse("https://jcalaz.github.io/img/sort_rent.jpg"));
    }

    @Override
    public void whenSuccess() {

    }

    @Override
    public void whenFail(String errorMsg) {

    }
    private class TestNormalAdapter extends StaticPagerAdapter {

        private List<String> imgList;

        public TestNormalAdapter(List<String> imgList) {
            this.imgList = imgList;
        }

        @Override
        public View getView(ViewGroup container, int position) {
            SimpleDraweeView view=new SimpleDraweeView(container.getContext());
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            view.setImageURI(imgList.get(position));
            return view;
        }

        @Override
        public int getCount() {
            return imgList.size();
        }
    }
}
