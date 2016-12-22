package me.jcala.xmarket.network;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocketFactory;

import me.jcala.xmarket.AppConf;
import me.jcala.xmarket.BuildConfig;
import me.jcala.xmarket.R;
import me.jcala.xmarket.app.App;
import me.jcala.xmarket.util.RetrofitUtils;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static me.jcala.xmarket.network.ApiConf.DEFAULT_TIMEOUT;

public class ReqExecutor {
    private UserReq userReq;
    private TradeReq tradeTagReq;
    private HybridReq hybridReq;


    private ReqExecutor(){}

    private static class ReqExecutorBuilder {
        private static ReqExecutor instance = new ReqExecutor();
    }

    public static ReqExecutor INSTANCE() {
        return ReqExecutorBuilder.instance;
    }
    private Retrofit getRetrofit(Converter.Factory factory){
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {//打印http日志
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addInterceptor(loggingInterceptor);
        }

      if (AppConf.enabled_ssl){
          SSLSocketFactory sslSocketFactory = RetrofitUtils.getSSLSocketFactory(App.getInstance(), new int[]{R.raw.xmarket});
          httpClientBuilder.sslSocketFactory(sslSocketFactory)
                  .hostnameVerifier(RetrofitUtils.getHostnameVerifier(new String[]{AppConf.BASE_URL}));
      }

        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor((Interceptor.Chain chain)-> {
                    Request originalRequest = chain.request();
                    Request.Builder requestBuilder = originalRequest.newBuilder()
                            .header("Content-Type", "application/json;charset=UTF-8")
                            .header("Accept", "application/json")
                            .method(originalRequest.method(), originalRequest.body());
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }).addInterceptor(new TokenInterceptor());
        return new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(AppConf.BASE_URL)
                .build();
    }
    private Retrofit getGsonRetrofit(){
        return getRetrofit(GsonConverterFactory.create());
    }

   public UserReq userReq(){
       if (userReq==null){
           userReq=getGsonRetrofit().create(UserReq.class);
       }
      return userReq;
   }
    public TradeReq tradeReq(){
        if (tradeTagReq==null){
            tradeTagReq=getGsonRetrofit().create(TradeReq.class);
        }
        return tradeTagReq;
    }
    public HybridReq hybridReq(){
        if (hybridReq==null){
            hybridReq=getGsonRetrofit().create(HybridReq.class);
        }
        return hybridReq;
    }
}
