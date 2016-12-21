package me.jcala.xmarket.mvp.sort.trades;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import java.util.List;

import me.jcala.xmarket.AppConf;
import me.jcala.xmarket.R;
import me.jcala.xmarket.conf.Api;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Trade;
import me.jcala.xmarket.mvp.trade.detail.TradeDetailActivity;
import me.jcala.xmarket.util.Interceptor;
import me.jcala.xmarket.view.RecyclerCommonAdapter;
import me.jcala.xmarket.view.RecyclerViewHolder;

public class TradeTagDetailPresenterImpl implements TradeTagDetailPresenter,TradeTagDetailModel.onGainListener{
    private Context context;
    private TradeTagDetailView view;
    private TradeTagDetailModel model;
    private String localTagName;

    public TradeTagDetailPresenterImpl(Context context, TradeTagDetailView view) {
        this.context = context;
        this.view = view;
        this.model=new TradeTagDetailModelImpl();
    }

    @Override
    public void getTradeListByTag(String tagName) {
        this.localTagName=tagName;
        model.executeTagTradesReq(this,tagName,1);
    }

    @Override
    public void onComplete(Result<List<Trade>> result) {
        int status= Interceptor.instance.tokenResultHandler(result,context);
        
        if (status==2 && localTagName!=null){
            getTradeListByTag(localTagName);
        }
        if (status!=1){
            return;
        }
        RecyclerCommonAdapter<?> adapter=new RecyclerCommonAdapter<Trade>(context,result.getData(), R.layout.school_item) {
            @Override
            public void convert(RecyclerViewHolder viewHolder, Trade item) {
                viewHolder.setText(R.id.deal_title,item.getTitle());
                viewHolder.setFrescoImg(R.id.deal_img, Uri.parse(item.getImgUrls().get(0)));
                viewHolder.setFrescoImg(R.id.author_img,Uri.parse(AppConf.BASE_URL+item.getAuthor().getAvatarUrl()));
                viewHolder.setText(R.id.author_name,item.getAuthor().getUsername());
                viewHolder.setText(R.id.deal_price,"￥ "+item.getPrice());
            }
        };
        RecyclerCommonAdapter.OnItemClickListener listener=(View view, int position) ->{
            Trade item=result.getData().get(position);
            Intent intent=new Intent(context,TradeDetailActivity.class);
            intent.putExtra("tradeId",item.getId());
            intent.putExtra("userId",item.getAuthor().getId());
            context.startActivity(intent);
        };
        view.whenLoadDataSuc(adapter);
        adapter.setClickListener(listener);
    }
}
