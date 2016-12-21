package me.jcala.xmarket.network.fresco;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocketFactory;

import me.jcala.xmarket.AppConf;
import me.jcala.xmarket.R;
import me.jcala.xmarket.app.App;
import me.jcala.xmarket.util.RetrofitUtils;
import okhttp3.OkHttpClient;

import static me.jcala.xmarket.network.ApiConf.DEFAULT_TIMEOUT;

public enum FrescoExecutor {
    instance;
    public OkHttpClient okHttpClient(){
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        if (AppConf.enabled_ssl){
            SSLSocketFactory sslSocketFactory = RetrofitUtils.getSSLSocketFactory(App.getInstance(), new int[]{R.raw.xmarket});
            httpClientBuilder.sslSocketFactory(sslSocketFactory)
                    .hostnameVerifier(RetrofitUtils.getHostnameVerifier(new String[]{AppConf.BASE_URL}));
        }
        /*if (BuildConfig.DEBUG) {//打印http日志
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addInterceptor(loggingInterceptor);
        }*/
        return httpClientBuilder
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }
}
