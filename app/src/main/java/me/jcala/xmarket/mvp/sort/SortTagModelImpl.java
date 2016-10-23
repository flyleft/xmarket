package me.jcala.xmarket.mvp.sort;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import me.jcala.xmarket.conf.ApiConf;
import me.jcala.xmarket.conf.AppConf;
import me.jcala.xmarket.data.api.ReqExecutor;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.SortTag;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SortTagModelImpl implements SortTagModel {
    @Override
    public void getSortTag(final onGainListener listener) {
        if (AppConf.reqExcute==AppConf.reqExcuteNormal){
            execute(listener);
        }else {
            executeLocal(listener);
        }

    }
    public void execute(final onGainListener listener){
        ReqExecutor
                .INSTANCE()
                .allReq()
                .sortTag()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<List<SortTag>>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFailure(ApiConf.common_err);
                    }

                    @Override
                    public void onNext(Result<List<SortTag>> listResult) {
                        if (listResult.getCode()== ApiConf.action_success){
                            listener.onSuccess(listResult.getData());
                        }else {
                            listener.onFailure(listResult.getMsg());
                        }
                    }
                });

    }
    public void executeLocal(final onGainListener listener){
         String jsonStr="[\n" +
                "  {\n" +
                "    \"id\": \"26\",\n" +
                "    \"name\": \"学习资料\",\n" +
                "    \"bgPic\": \"https://jcalaz.github.io/img/sort_book.jpg\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"28\",\n" +
                "    \"name\": \"生活用品\",\n" +
                "    \"bgPic\": \"https://jcalaz.github.io/img/sort_life.jpg\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"34\",\n" +
                "    \"name\": \"衣物鞋帽\",\n" +
                "    \"bgPic\": \"https://jcalaz.github.io/img/sort_clothes.jpeg\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"36\",\n" +
                "    \"name\": \"运动健身\",\n" +
                "    \"bgPic\": \"https://jcalaz.github.io/img/sort_body.jpg\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"30\",\n" +
                "    \"name\": \"手机数码\",\n" +
                "    \"bgPic\": \"https://jcalaz.github.io/img/sort_phone.jpg\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"32\",\n" +
                "    \"name\": \"电脑办公\",\n" +
                "    \"bgPic\": \"https://jcalaz.github.io/img/sort_computer.jpg\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"38\",\n" +
                "    \"name\": \"电器\",\n" +
                "    \"bgPic\": \"https://jcalaz.github.io/img/sort_elec.jpg\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"4\",\n" +
                "    \"name\": \"数码配件\",\n" +
                "    \"bgPic\": \"https://jcalaz.github.io/img/sort_parts.jpg\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"8\",\n" +
                "    \"name\": \"租赁\",\n" +
                "    \"bgPic\": \"https://jcalaz.github.io/img/sort_rent.jpg\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"14\",\n" +
                "    \"name\": \"其他\",\n" +
                "    \"bgPic\": \"https://jcalaz.github.io/img/sort_other.jpg\"\n" +
                "  }" +
                "]";
        List<SortTag> entities = new Gson().fromJson(jsonStr, new TypeToken<List<SortTag>>(){}.getType());
        listener.onSuccess(entities);
    }
}
