package me.jcala.xmarket.mvp.sort;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.orhanobut.logger.Logger;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import me.jcala.xmarket.R;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.TradeTag;
import me.jcala.xmarket.mvp.sort.trades.TradeTagDetailActivity;
import me.jcala.xmarket.view.CommonAdapter;
import me.jcala.xmarket.view.ViewHolder;

public class TradeTagPresenterImpl implements TradeTagPresenter,TradeTagModel.onGainListener {
    private TradeTagModel mModel;
    private TradeTagView mView;
    private Context mContext;

    public TradeTagPresenterImpl(Context context, TradeTagView view){
        mContext=context;
        mView = view;
        mModel = new TradeTagModelImpl();
    }

    @Override
    public void initView(final Realm realm) {
        RealmQuery<TradeTag> query =  realm.where(TradeTag.class);
        List<TradeTag> data =  query.findAll();
        if (data.size()>0){
            initList(data);
        }else {
            mModel.executeGetTagReq(this,realm);
        }
    }

    private void initList(List<TradeTag> tagList){
        BaseAdapter adapter=new CommonAdapter<TradeTag>(mContext, tagList,R.layout.sort_grid_item) {
            @Override
            public void convert(ViewHolder viewHolder, TradeTag dataEntity) {
                viewHolder.setText(R.id.grid_tv, dataEntity.getName());
                viewHolder.setFrescoImg(R.id.grid_iv, Uri.parse(dataEntity.getBgPic()));
            }
        };
        AdapterView.OnItemClickListener listener=(AdapterView<?> parent, View view, int position, long id)->{
            TradeTag entity = tagList.get(position);
            Intent intent=new Intent(mContext,TradeTagDetailActivity.class);
            intent.putExtra("tagName",entity.getName());
            mContext.startActivity(intent);
        };
        mView.whenSuccess(adapter,listener);
    }

    @Override
    public void onComplete(Result<List<TradeTag>> result,Realm realmDefault) {
        if (!resultHandler(result)){
            return;
        }
        initList(result.getData());
        final RealmResults<TradeTag> results = realmDefault.where(TradeTag.class).findAll();
        realmDefault.executeTransaction((Realm realm) -> results.deleteAllFromRealm());
        realmDefault.executeTransactionAsync((Realm realm) -> realm.copyToRealm(result.getData()));
    }

    private boolean resultHandler(Result<?> result){
        if (result==null){
            return false;
        }
        switch (result.getCode()) {
            case 100:
                return true;
            case 99:
                mView.whenFail(result.getMsg());
                return false;
            default:
                return false;
        }
    }

}
