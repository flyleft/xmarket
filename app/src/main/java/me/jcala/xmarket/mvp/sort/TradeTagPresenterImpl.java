package me.jcala.xmarket.mvp.sort;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import java.util.List;
import me.jcala.xmarket.R;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.TradeTag;
import me.jcala.xmarket.mvp.sort.trades.TradeTagDetailActivity;
import me.jcala.xmarket.view.CommonAdapter;
import me.jcala.xmarket.mvp.main.MainActivity;
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
    public void doGetSortTag() {
        mModel.getSortTag(this);
    }

    @Override
    public void onComplete(Result<List<TradeTag>> result) {
        if (!resultHandler(result)){
            return;
        }
        BaseAdapter adapter=new CommonAdapter<TradeTag>(mContext, result.getData(),R.layout.sort_grid_item) {
            @Override
            public void convert(ViewHolder viewHolder, TradeTag dataEntity) {
                viewHolder.setText(R.id.grid_tv, dataEntity.getName());
                viewHolder.setFrescoImg(R.id.grid_iv, Uri.parse(dataEntity.getBgPic()));
            }
        };
        AdapterView.OnItemClickListener listener=(AdapterView<?> parent, View view, int position, long id)->{
            TradeTag entity = result.getData().get(position);
            Intent intent=new Intent(mContext,TradeTagDetailActivity.class);
            intent.putExtra("tagName",entity.getName());
            mContext.startActivity(intent);
        };
        mView.whenSuccess(adapter,listener);
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

    @Override
    public void onFail(String msg) {
        mView.whenFail(msg);
    }
}
