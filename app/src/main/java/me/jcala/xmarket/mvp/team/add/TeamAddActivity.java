package me.jcala.xmarket.mvp.team.add;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.facebook.drawee.view.SimpleDraweeView;
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
    private String teamImgUrl;
    private String idImgUrl;
    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.team_add_activity);
        unbinder=ButterKnife.bind(this);
        presenter=new TeamAddPresenterImpl(this,this);
    }

    @OnClick(R.id.team_add_cancel)
    void clickBack(){
        Intent intent=new Intent(TeamAddActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.trade_add_submit)
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

    }

    @Override
    public void whenSuccess() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
