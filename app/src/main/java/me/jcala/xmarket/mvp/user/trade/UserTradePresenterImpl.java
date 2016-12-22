package me.jcala.xmarket.mvp.user.trade;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import java.util.List;

import me.jcala.xmarket.AppConf;
import me.jcala.xmarket.R;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Trade;
import me.jcala.xmarket.data.storage.UserIntermediate;
import me.jcala.xmarket.mvp.trade.detail.TradeDetailActivity;
import me.jcala.xmarket.util.ResultInterceptor;
import me.jcala.xmarket.view.RecyclerCommonAdapter;
import me.jcala.xmarket.view.RecyclerViewHolder;

public class UserTradePresenterImpl implements UserTradePresenter,
        UserTradeModel.OnUserTradeListener {

    private Context context;
    private UserTradeView view;
    private UserTradeModel model;

    public UserTradePresenterImpl(Context context, UserTradeView view) {
        this.context = context;
        this.view = view;
        this.model=new UserTradeModelImpl();
    }

    @Override
    public void onCompleteListener(Result<List<Trade>> result) {
        view.whenStopProgress();
        if (!ResultInterceptor.instance.resultDataHandler(result)){
            return;
        }
        initList(result.getData());
    }
   private void initList(List<Trade> trades){
       RecyclerCommonAdapter<?> adapter=new RecyclerCommonAdapter<Trade>(context,trades, R.layout.school_item) {
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
           Trade item=trades.get(position);
           Intent intent=new Intent(context,TradeDetailActivity.class);
           intent.putExtra("tradeId",item.getId());
           intent.putExtra("userId",item.getAuthor().getId());
           intent.putExtra("status",item.getStatus());
           context.startActivity(intent);
       };
       adapter.setClickListener(listener);
       view.whenLoadDataSuccess(adapter);
   }

    @Override
    public void initViewList(int type) {
        view.whenStartProgress();
        String userId= UserIntermediate.instance.getUser(context).getId();
        model.executeGetTradesReq(this,userId,type);
    }

}
