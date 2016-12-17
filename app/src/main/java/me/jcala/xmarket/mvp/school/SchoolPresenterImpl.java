package me.jcala.xmarket.mvp.school;

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
    public void onReqComplete(Result<List<Trade>> result) {
        if (!resultHandler(result)){
            return;
        }
        initList(result.getData());
        /*final RealmResults<Trade> results = realmDefault.where(Trade.class).findAll();
        realmDefault.executeTransactionAsync((Realm realm) -> results.deleteAllFromRealm());
        realmDefault.executeTransactionAsync((Realm realm) -> realm.copyFromRealm(result.getData()));*/
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

    public void initList(List<Trade> trades) {
        RecyclerCommonAdapter<?> adapter=new RecyclerCommonAdapter<Trade>(context,trades, R.layout.school_item) {
            @Override
            public void convert(RecyclerViewHolder viewHolder, Trade item) {
                viewHolder.setText(R.id.deal_title,item.getTitle());
                viewHolder.setFrescoImg(R.id.deal_img, Uri.parse(AppConf.BASE_URL+item.getImgUrls().get(0)));
                viewHolder.setFrescoImg(R.id.author_img,Uri.parse(AppConf.BASE_URL+item.getAuthor().getAvatarUrl()));
                viewHolder.setText(R.id.author_name,item.getAuthor().getUsername());
                viewHolder.setText(R.id.deal_price,"￥ "+item.getPrice());
                if (AppConf.useMock){
                    viewHolder.setFrescoImg(R.id.deal_img, Uri.parse(item.getImgUrls().get(0)));
                    viewHolder.setFrescoImg(R.id.author_img,Uri.parse(item.getAuthor().getAvatarUrl()));
                }
            }
        };
        RecyclerCommonAdapter.OnItemClickListener listener=(View view, int position) ->{
            Trade item=trades.get(position);
            Intent intent=new Intent(context,TradeDetailActivity.class);
            intent.putExtra("tradeId",item.getId());
            intent.putExtra("userId",item.getAuthor().getId());
            context.startActivity(intent);
        };
        view.whenLoadDataSuc(adapter);
        adapter.setClickListener(listener);
    }

    @Override
    public void initView() {
        String schoolName= UserIntermediate.instance.getUser(context).getSchool();
    }
}
