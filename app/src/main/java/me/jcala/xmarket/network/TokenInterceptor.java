package me.jcala.xmarket.network;

import com.orhanobut.logger.Logger;

import java.io.IOException;

import me.jcala.xmarket.app.App;
import me.jcala.xmarket.data.pojo.User;
import me.jcala.xmarket.data.storage.UserIntermediate;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;

public class TokenInterceptor implements Interceptor{

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);

        if (isTokenExpired(response)) {//根据和服务端的约定判断token过期
            Logger.d("token过期,即将重新获取");
            //获取最新的Token
            User user= UserIntermediate.instance.getUser(App.getInstance());
            Call<String> call=ReqExecutor.INSTANCE().userReq().auth(user.getUsername(),user.getPassword());
            String token=call.execute().body();
            //使用新的Token，创建新的请求
            Request newRequest = chain.request()
                    .newBuilder()
                    .header("x-access-token",token)
                    .build();
            return chain.proceed(newRequest);
        }
        return response;
    }

    private boolean isTokenExpired(Response response) {
        if (response.code() == 210) {
            return true;
        }
        return false;
    }
}