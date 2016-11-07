package me.jcala.xmarket.data.api;

import java.util.concurrent.TimeUnit;

import me.jcala.xmarket.BuildConfig;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static me.jcala.xmarket.conf.ApiConf.BASE_URL;
import static me.jcala.xmarket.conf.ApiConf.DEFAULT_TIMEOUT;

public class ReqExecutor {
    private NeedTokenReq needTokenReq;
    private static String token="";
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

        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor((Interceptor.Chain chain)-> {
                    Request originalRequest = chain.request();
                    Request.Builder requestBuilder = originalRequest.newBuilder()
                            .header("Content-Type", "application/json;charset=UTF-8")
                            .header("Accept", "application/json")
                            .addHeader("x-access-token",token)
                            .method(originalRequest.method(), originalRequest.body());
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                });
        return new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }
    private Retrofit getGsonRetrofit(){
        return getRetrofit(GsonConverterFactory.create());
    }
   public NeedTokenReq allReq(){
       if (needTokenReq==null){
           needTokenReq=getGsonRetrofit().create(NeedTokenReq.class);
       }
      return needTokenReq;
   }
}
