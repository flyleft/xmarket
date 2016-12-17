package me.jcala.xmarket.mvp.team.add;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.facebook.drawee.view.SimpleDraweeView;
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;
import com.orhanobut.logger.Logger;

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
import me.jcala.xmarket.mvp.trade.add.TradeAddActivity;

public class TeamAddActivity extends BaseActivity implements TeamAddView{

    private Unbinder unbinder;
    TeamAddPresenter presenter;
    @BindView(R.id.team_add_title)
    EditText teamTitle;
    @BindView(R.id.team_add_description)
    EditText teamDesc;
    @BindView(R.id.team_add_img)
    SimpleDraweeView teamImg;
    @BindView(R.id.team_id_img)
    SimpleDraweeView idImg;
    MaterialDialog progress;
    private String teamImgUrl;
    private String idImgUrl;
    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.team_add_activity);
        unbinder=ButterKnife.bind(this);
        presenter=new TeamAddPresenterImpl(this,this);
        progress=new MaterialDialog.Builder(TeamAddActivity.this)
                .content(R.string.trade_add_dialog_content)
                .progress(true, 0)
                .progressIndeterminateStyle(false)
                .title(R.string.dialog_wait)
                .build();
    }

    @OnClick(R.id.team_add_cancel)
    void clickBack(){
        Intent intent=new Intent(TeamAddActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.team_add_submit)
    void clickSubmit(){
       presenter.submit(teamTitle,teamDesc,teamImgUrl,idImgUrl);
    }

    @OnClick(R.id.team_add_img_click)
    void selectTeamImg(){
        selectPic(true);
    }

    @OnClick(R.id.team_add_id_click)
    void selectIdImg(){
        selectPic(false);
    }

    private void selectPic(boolean isTeam){
        try {
            RxGalleryFinal
                    .with(TeamAddActivity.this)
                    .image()
                    .radio()
                    .crop()
                    .cropropCompressionQuality(40)
                    .cropWithAspectRatio(16,9)
                    .imageLoader(ImageLoaderType.FRESCO)
                    .subscribe(new RxBusResultSubscriber<ImageRadioResultEvent>() {
                        @Override
                        protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
                            String path=imageRadioResultEvent.getResult().getCropPath();
                            if (isTeam){
                                teamImg.setImageURI("file://"+path);
                                teamImgUrl=path;
                            }else {
                                 idImg.setImageURI("file://"+path);
                                 idImgUrl=path;
                            }
                        }
                    })
                    .openGallery();
        } catch (Exception e) {
            Logger.w("图片选择器出现异常:"+e.getLocalizedMessage());
        }
    }

    @Override
    public void whenFail(String errorMsg) {
        new SuperToast(TeamAddActivity.this)
                .setText(errorMsg)
                .setDuration(Style.DURATION_LONG)
                .setColor(PaletteUtils.getTransparentColor(PaletteUtils.MATERIAL_RED))
                .setAnimations(Style.ANIMATIONS_FLY)
                .show();
    }

    @Override
    public void whenSuccess() {
        Intent intent=new Intent(TeamAddActivity.this,MainActivity.class);
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
