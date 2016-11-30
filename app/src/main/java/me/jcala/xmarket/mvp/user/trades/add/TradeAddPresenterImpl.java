package me.jcala.xmarket.mvp.user.trades.add;

import android.content.Context;
import android.widget.EditText;

import java.util.List;

import me.jcala.xmarket.conf.Api;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.TradeTag;
import me.jcala.xmarket.data.storage.UserIntermediator;
import okhttp3.MultipartBody;

public class TradeAddPresenterImpl
        implements TradeAddPresenter,TradeAddModel.onTradeAddListener{
    private TradeAddModel model;
    private TradeAddView view;
    private Context context;

    public TradeAddPresenterImpl(Context context, TradeAddView view) {
        this.context = context;
        this.view = view;
        this.model=new TradeAddModelImpl();
    }

    @Override
    public void gainTagList() {

    }

    @Override
    public void hasGotAddTradeResult(Result<?> result) {
        if (result==null){
            view.whenFail(Api.SERVER_ERROR.msg());
        }

        switch (result.getCode()) {
            case 100:
                view.whenAddSuccess();
            case 99:
                view.whenFail(Api.SERVER_ERROR.msg());
            default:
        }
    }

    @Override
    public void hasGoTagsResult(Result<List<TradeTag>> result) {
        if (result==null){
            view.whenFail(Api.SERVER_ERROR.msg());
        }

        switch (result.getCode()) {
            case 100:
                view.whenGetTagListSuccess();
            case 99:
                view.whenFail(Api.SERVER_ERROR.msg());
            default:
        }
    }

    @Override
    public void releaseTrade(List<MultipartBody.Part> parts,EditText titleContent,
                             EditText priceContent, EditText descContent, String tag) {
        String userId= UserIntermediator.instance.getUser(context).getId();

        model.executeAddTradeReq(userId,null,this);
    }

}
