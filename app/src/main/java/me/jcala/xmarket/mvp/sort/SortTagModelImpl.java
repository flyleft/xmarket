package me.jcala.xmarket.mvp.sort;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import me.jcala.xmarket.conf.ApiConf;
import me.jcala.xmarket.conf.AppConf;
import me.jcala.xmarket.data.api.ReqExecutor;
import me.jcala.xmarket.data.entity.Result;
import me.jcala.xmarket.data.entity.SortTag;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SortTagModelImpl implements SortTagModel {
    @Override
    public void getSortTag(final OnGetSortTagListener listener) {
        if (AppConf.reqExcute==AppConf.reqExcuteNormal){
            excute(listener);
        }else {
            excuteLocal(listener);
        }

    }
    public void excute(final OnGetSortTagListener listener){
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
                        if (listResult.getCode()== ApiConf.req_success){
                            listener.onSuccess(listResult.getData());
                        }else {
                            listener.onFailure(listResult.getMsg());
                        }
                    }
                });

    }
    public void excuteLocal(final OnGetSortTagListener listener){
         String jsonStr="[" +
                "  {" +
                "    id: 26," +
                "    name: 学习资料," +
                "    bgPic: https://jcalaz.github.io/img/sort_book.jpg" +
                "  }," +
                "  {" +
                "    id: 28," +
                "    name: 生活用品," +
                "    bgPic: https://jcalaz.github.io/img/sort_life.jpg" +
                "  }," +
                "  {" +
                "    id: 34," +
                "    name: 衣物鞋帽," +
                "    bgPic: https://jcalaz.github.io/img/sort_clothes.jpeg" +
                "  }," +
                "  {" +
                "    id: 36," +
                "    name: 运动健身," +
                "    bgPic: https://jcalaz.github.io/img/sort_body.jpg" +
                "  }," +
                "  {" +
                "    id: 30," +
                "    name: 手机数码," +
                "    bgPic: https://jcalaz.github.io/img/sort_phone.jpg" +
                "  }," +
                "  {" +
                "    id: 32," +
                "    name: 电脑办公," +
                "    bgPic: https://jcalaz.github.io/img/sort_computer.jpg" +
                "  }," +
                "  {" +
                "    id: 38," +
                "    name: 电器," +
                "    bgPic: https://jcalaz.github.io/img/sort_elec.jpg" +
                "  }," +
                "  {" +
                "    id: 4," +
                "    name: 数码配件," +
                "    bgPic: https://jcalaz.github.io/img/sort_parts.jpg" +
                "  }," +
                "  {" +
                "    id: 8," +
                "    name: 租赁," +
                "    bgPic: https://jcalaz.github.io/img/sort_rent.jpg" +
                "  }," +
                "  {" +
                "    id: 14," +
                "    name: 其他," +
                "    bgPic: https://jcalaz.github.io/img/sort_other.jpg" +
                "  }" +
                "]";
        List<SortTag> entities = new Gson().fromJson(jsonStr, new TypeToken<List<SortTag>>(){}.getType());
        listener.onSuccess(entities);

    }
}
