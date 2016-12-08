package me.jcala.xmarket.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import java.util.List;


public class BannerAdapter extends StaticPagerAdapter {

    private List<String> imgList;

    public BannerAdapter(List<String> imgList) {
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