package me.jcala.xmarket.mvp.user.trades.add;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;
import java.util.ArrayList;
import java.util.LinkedList;
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
import me.jcala.xmarket.conf.Api;
import me.jcala.xmarket.data.pojo.Trade;
import me.jcala.xmarket.data.pojo.TradeTag;
import me.jcala.xmarket.mvp.a_base.BaseActivity;
import me.jcala.xmarket.mvp.a_base.CommonAdapter;
import me.jcala.xmarket.mvp.main.MainActivity;
import me.jcala.xmarket.util.ViewHolder;

public class TradeAddActivity extends BaseActivity implements TradeAddView{

    @BindView(R.id.trade_add_pics_select)
    GridView selectPics;
    @BindView(R.id.trade_add_tag_select)
    TextView selectTag;
    @BindView(R.id.trade_add_title_content)
    EditText tradeTitle;
    @BindView(R.id.trade_add_price_content)
    EditText tradePrice;
    @BindView(R.id.trade_add_desp_content)
    EditText tradeDesc;
    BaseAdapter adapter;
    MaterialDialog progress;
    TradeAddPresenter presenter;
    private List<TradeTag> tags;
    private LinkedList<String> picUrls=new LinkedList<>();
    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.trade_add_bar);
        ButterKnife.bind(this);
        initData();
    }

    private void initData(){
        progress=new MaterialDialog.Builder(TradeAddActivity.this)
                .content(R.string.trade_add_dialog_content)
                .progress(true, 0)
                .progressIndeterminateStyle(false)
                .title(R.string.dialog_wait)
                .build();
        presenter=new TradeAddPresenterImpl(this,this);
        picUrls.add("res://drawable/"+R.drawable.trade_add_pic_plus);
        picSet();
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

    @Override
    @OnClick(R.id.trade_add_tag_select)
    public void whenGetTagListSuccess() {
        if (tags==null){
            whenFail(Api.SERVER_ERROR.msg());
            return;
        }
        new MaterialDialog.Builder(this)
                .title(R.string.register_next_school_choose)
                .items(tags)
                .itemsCallbackSingleChoice(0,
                        (MaterialDialog dialog, View view, int which, CharSequence text)->{

                            selectTag.setText(text);
                            return true;
                        })
                .positiveText(R.string.register_next_choose)
                .show();
    }


   private void picSet(){
       adapter=new CommonAdapter<String>(TradeAddActivity.this,picUrls,R.layout.trade_add_pic_item) {
           @Override
           public void convert(ViewHolder viewHolder, String picUrl) {
               viewHolder.setFrescoImg(R.id.grid_iv, Uri.parse(picUrl));
           }
       };
       AdapterView.OnItemClickListener listener=(AdapterView<?> parent, View view, int position, long id)->{

         if (position == picUrls.size()-1){
             picSelector();
             return;
         }

       };
       selectPics.setAdapter(adapter);
       selectPics.setOnItemClickListener(listener);

   }

     void picSelector(){
         if (picUrls.size()>8){
             return;
         }
        try {
            RxGalleryFinal
                    .with(TradeAddActivity.this)
                    .image()
                    .multiple()
                    .maxSize(8)
                    .imageLoader(ImageLoaderType.FRESCO)
                    .subscribe(new RxBusResultSubscriber<ImageMultipleResultEvent>() {
                        @Override
                        protected void onEvent(ImageMultipleResultEvent imageMultipleResultEvent) throws Exception {
                            picUrls.removeLast();
                            for (MediaBean media:imageMultipleResultEvent.getResult()){

                                picUrls.add("file://"+media.getOriginalPath());
                            }
                            picUrls.addLast("res://drawable/"+R.drawable.trade_add_pic_plus);
                            adapter.notifyDataSetChanged();
                        }
                    })
                    .openGallery();
        } catch (Exception e) {
        }
    }

    @OnClick(R.id.trade_add_submit)
    public void whenClickSubmit() {
        String tradeTag=selectTag.getText().toString();
        String textViewContent=getResources().getString(R.string.trade_add_tag);
        if (tradeTag.isEmpty() || tradeTag.equals(textViewContent)){
            return;
        }
        presenter.releaseTrade(picUrls,tradeTitle,tradePrice,tradeDesc,selectTag);
    }

    @OnClick(R.id.trade_add_cancel)
    public void whenClickCancel(){
        Intent intent=new Intent(TradeAddActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void whenStartProgress() {
        progress.show();
    }

    @Override
    public void whenStopProgress() {
        progress.dismiss();
    }
}
