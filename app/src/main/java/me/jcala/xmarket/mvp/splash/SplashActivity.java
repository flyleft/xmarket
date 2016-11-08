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
import me.jcala.xmarket.data.api.ReqExecutor;
import me.jcala.xmarket.mvp.a_base.BaseActivity;
import me.jcala.xmarket.mvp.main.MainActivity;
import me.jcala.xmarket.mvp.user.login.LoginRegisterActivity;
import me.jcala.xmarket.util.TokenUtils;

public class SplashActivity extends BaseActivity {

    private SimpleDraweeView view;
    @Override
    protected void initVariables() {
        view=(SimpleDraweeView)findViewById(R.id.loading_gif);
        DraweeController builder = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                .setUri(Uri.parse("res://drawable/" + R.drawable.splash_loading))//设置uri
                .build();
        view.setController(builder);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_activity);
        splashAction();
    }

    private void splashAction(){

        String token=TokenUtils.instance.getToken(SplashActivity.this);//从SharedPreferences中获取token的值

        if(token==null||"".equals(token)){
            new Handler().postDelayed(() ->{
                Intent intent = new Intent(SplashActivity.this, LoginRegisterActivity.class);
                startActivity(intent);
                finish();
            }, 2500);
        }else {
            ReqExecutor.INSTANCE().setToken(token);//设置retrofit发起HTTP的x-access-token的头部值

            new Handler().postDelayed(() ->{//如果已经有token则直接进入MainActivity,否则跳转到登录界面
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }, 2500);
        }
    }
}
