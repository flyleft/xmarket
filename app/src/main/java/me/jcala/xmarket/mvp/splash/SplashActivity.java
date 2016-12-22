package me.jcala.xmarket.mvp.splash;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import me.jcala.xmarket.R;
import me.jcala.xmarket.data.pojo.User;
import me.jcala.xmarket.data.storage.UserIntermediate;
import me.jcala.xmarket.network.ReqExecutor;
import me.jcala.xmarket.mvp.a_base.BaseActivity;
import me.jcala.xmarket.mvp.main.MainActivity;
import me.jcala.xmarket.mvp.user.login.LoginRegisterActivity;
import me.jcala.xmarket.data.storage.SharedPreferencesStorage;

public class SplashActivity extends BaseActivity {

    private SimpleDraweeView view;


    @Override
    protected void initViews(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_activity);
        splashAction();
        initVariables();
    }
    protected void initVariables() {
        view=(SimpleDraweeView)findViewById(R.id.loading_gif);
        DraweeController builder = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                .setUri(Uri.parse("res://drawable/" + R.drawable.splash_loading))//设置uri
                .build();
        view.setController(builder);
    }
    private void splashAction(){

        User user= UserIntermediate.instance.getUser(SplashActivity.this);

        if(user==null){
            new Handler().postDelayed(() ->{
                Intent intent = new Intent(SplashActivity.this, LoginRegisterActivity.class);
                startActivity(intent);
                finish();
            }, 2500);
        }else {
            new Handler().postDelayed(() ->{//如果已经有token则直接进入MainActivity,否则跳转到登录界面
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }, 2500);
        }
    }
}
