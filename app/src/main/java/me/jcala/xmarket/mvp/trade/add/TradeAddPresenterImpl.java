package me.jcala.xmarket.mvp.trade.add;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.List;

import me.jcala.xmarket.R;
import me.jcala.xmarket.network.Api;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Trade;
import me.jcala.xmarket.data.pojo.User;
import me.jcala.xmarket.data.storage.UserIntermediate;
import me.jcala.xmarket.util.ResultInterceptor;
import me.jcala.xmarket.util.RetrofitUtils;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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
       model.executeGetTagsReq(this);
    }

    @Override
    public void hasGotAddTradeResult(Result<String> result) {
        view.whenStopProgress();
        if (ResultInterceptor.instance.resultHandler(result)){
            view.whenAddSuccess();
        }else {
            view.whenFail(Api.SERVER_ERROR.msg());
        }
    }

    @Override
    public void hasGoTagsResult(Result<List<String>> result) {
        if (result==null || result.getData() == null){
            view.whenFail(Api.SERVER_ERROR.msg());
            return;
        }

        switch (result.getCode()) {
            case 100:
                view.whenGetTagListSuccess(result.getData());break;
            case 99:
                view.whenFail(result.getMsg());break;
            default:
        }
    }

    @Override
    public void releaseTrade(LinkedList<String> picUploadUrls, EditText title,
                             EditText price, EditText desc, TextView tag) {
        Trade trade=checkForm(picUploadUrls,title,price,desc,tag);

        if (!trade.isReleaseCheck()){
            return;
        }
        trade.setStatus(0);//状态,0代表商品待售
        trade.setCreateTime(System.currentTimeMillis());//设置商品发布时间
        view.whenStartProgress();
        List<MultipartBody.Part> parts= RetrofitUtils.filesToMultipartBodyParts(picUploadUrls);
        String tradeJsonStr=new Gson().toJson(trade);
        RequestBody tradeJson=RetrofitUtils.createPartFromString(tradeJsonStr);
        model.executeAddTradeReq(tradeJson,parts,this);
    }


    private Trade checkForm(List<String> picUploadUrls, EditText title, EditText price, EditText desc, TextView tag){
        Trade trade=new Trade();
        trade.setReleaseCheck(false);
        if (picUploadUrls.size() < 1){
            view.whenFail("请选择至少一张配图");
            return trade;
        }
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
        trade.setTagName(tagData);
        User authorOld= UserIntermediate.instance.getUser(context);
        if (authorOld==null || authorOld.getId()==null){
            return trade;
        }
        User author=new User();
        author.setId(authorOld.getId());
        author.setUsername(authorOld.getUsername());
        author.setAvatarUrl(authorOld.getAvatarUrl());
        trade.setSchoolName(authorOld.getSchool());
        trade.setAuthor(author);
        trade.setReleaseCheck(true);
        return trade;
    }
}
