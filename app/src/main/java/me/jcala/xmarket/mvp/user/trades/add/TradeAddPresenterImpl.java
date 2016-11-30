package me.jcala.xmarket.mvp.user.trades.add;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import me.jcala.xmarket.R;
import me.jcala.xmarket.conf.Api;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Trade;
import me.jcala.xmarket.data.pojo.TradeTag;
import me.jcala.xmarket.data.pojo.User;
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
    public void releaseTrade(List<MultipartBody.Part> parts,EditText title,
                             EditText price, EditText desc, TextView tag) {
        User author= UserIntermediator.instance.getUser(context);
        Trade trade=checkForm(title,price,desc,tag);
        if (trade.isReleaseCheck()){
            trade.setAuthor(author);
            model.executeAddTradeReq(trade,this);
        }

    }

    private Trade checkForm(EditText title,EditText price,EditText desc,TextView tag){
        Trade trade=new Trade();
        String titleData=title.getText().toString().trim();
        if (titleData.isEmpty()){
            view.whenFail("标题不可以为空");
            return trade;
        }
        trade.setTitle(titleData);
        String priceData=price.getText().toString().trim();
        if (titleData.isEmpty()){
            view.whenFail("价格不可以为空");
            return trade;
        }

        long priceValue=Long.parseLong(priceData);
        if (priceValue < 0){
            view.whenFail("价格不可以为负值");
            return trade;
        }
        trade.setPrice(priceValue);
        String descData=desc.getText().toString().trim();
        if (descData.isEmpty()){
            view.whenFail("描述不可以为空");
            return trade;
        }
        trade.setDesc(descData);
        String tagData=tag.getText().toString().trim();
        String textViewValue=context.getResources().getString(R.string.trade_add_tag);
        if (tagData.equals(textViewValue)){
            view.whenFail("请选择商品分类");
            return trade;
        }
        trade.setTagId(tagData);
        trade.setReleaseCheck(true);
        return trade;
    }
}
