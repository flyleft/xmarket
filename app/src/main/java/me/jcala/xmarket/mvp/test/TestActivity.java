package me.jcala.xmarket.mvp.test;

import android.net.Uri;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.GridView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.bean.MediaBean;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultSubscriber;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageMultipleResultEvent;
import me.jcala.xmarket.R;
import me.jcala.xmarket.mvp.a_base.BaseActivity;
import me.jcala.xmarket.mvp.a_base.CommonAdapter;
import me.jcala.xmarket.util.ViewHolder;

public class TestActivity extends BaseActivity{


    @BindView(R.id.trade_add_pics_select)
    GridView selectPics;

    private List<String> picUrls=new ArrayList<>();
    BaseAdapter adapter;


    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.test_activity);
        ButterKnife.bind(this);
        picUrls.add("res://drawable/"+R.drawable.trade_add_pic_plus);
        picSet();
    }

    private void picSet(){

        adapter=new CommonAdapter<String>(TestActivity.this,picUrls,R.layout.sort_grid_item) {
            @Override
            public void convert(ViewHolder viewHolder, String picUrl) {
                viewHolder.setFrescoImg(R.id.grid_iv, Uri.parse(picUrl));
            }
        };
        selectPics.setAdapter(adapter);

    }

    @OnClick(R.id.trade_add_photo_take)
    void picSelector(){
        try {
            RxGalleryFinal
                    .with(TestActivity.this)
                    .image()
                    .multiple()
                    .cropWithAspectRatio(500,500)
                    .maxSize(8)
                    .imageLoader(ImageLoaderType.FRESCO)
                    .subscribe(new RxBusResultSubscriber<ImageMultipleResultEvent>() {
                        @Override
                        protected void onEvent(ImageMultipleResultEvent imageMultipleResultEvent) throws Exception {
                            for (MediaBean media:imageMultipleResultEvent.getResult()){

                                picUrls.add("file://"+media.getOriginalPath());
                            }
                            adapter.notifyDataSetChanged();
                        }
                    })
                    .openGallery();
        } catch (Exception e) {
        }
    }


}
