package me.jcala.xmarket.mvp.team.trade;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import java.util.List;

import me.jcala.xmarket.AppConf;
import me.jcala.xmarket.R;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Trade;
import me.jcala.xmarket.mvp.trade.detail.TradeDetailActivity;
import me.jcala.xmarket.view.RecyclerCommonAdapter;
import me.jcala.xmarket.view.RecyclerViewHolder;

public class TeamTradePresenterImpl implements TeamTradePresenter,TeamTradeModel.OnTeamTradeListener{
    private Context context;
    private TeamTradeView view;
    private TeamTradeModel model;

    public TeamTradePresenterImpl(Context context, TeamTradeView view) {
        this.context = context;
        this.view = view;
        this.model=new TeamTradeModelImpl();
    }

    @Override
    public void initView(String teamName) {
       model.executeGetTradeReq(this,teamName);
    }

    @Override
    public void onComplete(Result<List<Trade>> result) {
       if (!resultHandler(result)){
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
        view.whenLoadDataSuc(adapter);
    }
    private boolean resultHandler(Result<?> result){
        if (result==null){
            return false;
        }

        switch (result.getCode()) {
            case 100:
                return true;
            case 99:
                return false;
            default:
                return false;
        }
    }
}
