package me.jcala.xmarket.mvp.trade.add;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import com.orhanobut.logger.Logger;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultSubscriber;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageRadioResultEvent;
import me.jcala.xmarket.R;
import me.jcala.xmarket.mvp.a_base.BaseActivity;
import me.jcala.xmarket.mvp.main.MainActivity;
import me.jcala.xmarket.view.CommonAdapter;
import me.jcala.xmarket.view.ViewHolder;

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

    private Unbinder unbinder;
    BaseAdapter adapter;
    MaterialDialog progress;
    TradeAddPresenter presenter;
    private List<String> tags;
    private LinkedList<String> picUrls=new LinkedList<>();
    private LinkedList<String> picUploadUrls=new LinkedList<>();
    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.trade_add_activity);
        unbinder=ButterKnife.bind(this);
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
        presenter.gainTagList();
        picUrls.add("res://drawable/"+R.drawable.trade_add_pic_plus);
        picSet();
    }

    @Override
    public void whenAddSuccess() {
        new SuperToast(TradeAddActivity.this)
                .setText("发布成功")
                .setDuration(Style.DURATION_LONG)
                .setColor(PaletteUtils.getTransparentColor(PaletteUtils.MATERIAL_GREEN))
                .setAnimations(Style.ANIMATIONS_POP)
                .show();
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
                .setAnimations(Style.ANIMATIONS_FLY)
                .show();
    }

    @Override
    public void whenGetTagListSuccess(final List<String> tagList) {
        tags=tagList;
    }

    @OnClick(R.id.trade_add_tag_select)
    public void clickSelectTag(){
        if (tags==null){
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
                .positiveText(R.string.choose)
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
                    .radio()
                    .crop()
                    .cropropCompressionQuality(40)
                    .cropWithAspectRatio(1,1)
                    .imageLoader(ImageLoaderType.FRESCO)
                    .subscribe(new RxBusResultSubscriber<ImageRadioResultEvent>() {
                        @Override
                        protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
                            String path=imageRadioResultEvent.getResult().getCropPath();
                             picUrls.addFirst("file://"+path);
                             picUploadUrls.add(path);
                             adapter.notifyDataSetChanged();
                        }
                    })
                    .openGallery();
        } catch (Exception e) {
            Logger.w("图片选择器出现异常:"+e.getLocalizedMessage());
        }
    }

    @OnClick(R.id.trade_add_submit)
    public void whenClickSubmit() {
        presenter.releaseTrade(picUploadUrls,tradeTitle,tradePrice,tradeDesc,selectTag);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}