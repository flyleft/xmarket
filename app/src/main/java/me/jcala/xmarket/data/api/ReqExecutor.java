package me.jcala.xmarket.data.api;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static me.jcala.xmarket.conf.NetWorkConf.BASE_URL;
import static me.jcala.xmarket.conf.NetWorkConf.DEFAULT_TIMEOUT;

public class ReqExecutor {
    private AllReq allReq;
    private String token="";
    private ReqExecutor(){}
    private static class ReqExecutorBuilder {
        private static ReqExecutor instance = new ReqExecutor();
    }
    public static ReqExecutor INSTANCE() {
        return ReqExecutorBuilder.instance;
    }
    private Retrofit getRetrofit(){
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor((Interceptor.Chain chain) -> {
                Request request = chain.request();
                Request newReq = request.newBuilder()
                        .addHeader("Authorization",token)
                        .build();
                return chain.proceed(newReq);
            });
        return new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }
   public AllReq allReq(){
       if (allReq==null){
           allReq=getRetrofit().create(AllReq.class);
       }
      return allReq;
   }
}
