package me.jcala.xmarket.mvp.school;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import me.jcala.xmarket.AppConf;
import me.jcala.xmarket.R;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Trade;
import me.jcala.xmarket.data.pojo.RealmTrade;
import me.jcala.xmarket.data.storage.UserIntermediate;
import me.jcala.xmarket.mvp.trade.detail.TradeDetailActivity;
import me.jcala.xmarket.util.ResultInterceptor;
import me.jcala.xmarket.view.RecyclerCommonAdapter;
import me.jcala.xmarket.view.RecyclerViewHolder;

public class SchoolPresenterImpl implements SchoolModel.onGainListener,SchoolPresenter {
    private SchoolModel model;
    private SchoolView view;
    private Context context;

    public SchoolPresenterImpl(Context context,SchoolView view) {
        this.context = context;
        this.view = view;
        this.model=new SchoolModelImpl();
    }

    @Override
    public void onReqComplete(Result<List<Trade>> result,Realm realmDefault) {
        view.whenHideRefresh();
        if (!ResultInterceptor.instance.resultDataHandler(result)){
            return;
        }
        List<RealmTrade> tradeList=new ArrayList<>();
        for (Trade trade:result.getData()){
            RealmTrade newTrade=new RealmTrade();
            newTrade.setId(trade.getId());
            newTrade.setTitle(trade.getTitle());
            newTrade.setPrice(trade.getPrice());
            newTrade.setImg(AppConf.BASE_URL+trade.getImgUrls().get(0));
            newTrade.setAuthorId(trade.getAuthor().getId());
            newTrade.setAuthorImg(AppConf.BASE_URL+trade.getAuthor().getAvatarUrl());
            newTrade.setAuthorName(trade.getAuthor().getUsername());
            tradeList.add(newTrade);
        }
        initList(tradeList);
        final RealmResults<RealmTrade> results = realmDefault.where(RealmTrade.class).findAll();
        realmDefault.executeTransaction((Realm realm) -> results.deleteAllFromRealm());
        realmDefault.executeTransactionAsync((Realm realm) -> realm.copyToRealm(tradeList));
    }

    @Override
    public void refreshView(Realm realm) {
        String schoolName= UserIntermediate.instance.getUser(context).getSchool();
        model.executeGetTradesReq(this,schoolName,0,realm);
    }

    public void initList(List<RealmTrade> trades) {
        RecyclerCommonAdapter<?> adapter=new RecyclerCommonAdapter<RealmTrade>(context,trades, R.layout.school_item) {
            @Override
            public void convert(RecyclerViewHolder viewHolder, RealmTrade item) {
                viewHolder.setText(R.id.deal_title,item.getTitle());
                viewHolder.setFrescoImg(R.id.deal_img, Uri.parse(item.getImg()));
                viewHolder.setFrescoImg(R.id.author_img,Uri.parse(item.getAuthorImg()));
                viewHolder.setText(R.id.author_name,item.getAuthorName());
                viewHolder.setText(R.id.deal_price,"￥ "+item.getPrice());
            }
        };
        RecyclerCommonAdapter.OnItemClickListener listener=(View view, int position) ->{
            RealmTrade item=trades.get(position);
            Intent intent=new Intent(context,TradeDetailActivity.class);
            intent.putExtra("tradeId",item.getId());
            intent.putExtra("userId",item.getAuthorId());
            context.startActivity(intent);
        };
        view.whenLoadDataSuc(adapter);
        adapter.setClickListener(listener);
    }

    @Override
    public void initView(Realm realm) {
        RealmQuery<RealmTrade> query =  realm.where(RealmTrade.class);
        List<RealmTrade> data =  query.findAll();
        if (data.size()>0){
            initList(data);
        }else {
            String schoolName= UserIntermediate.instance.getUser(context).getSchool();
            model.executeGetTradesReq(this,schoolName,0,realm);
        }
    }
}
