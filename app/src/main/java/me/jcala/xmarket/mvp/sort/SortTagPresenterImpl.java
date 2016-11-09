package me.jcala.xmarket.mvp.sort;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import java.util.List;
import me.jcala.xmarket.R;
import me.jcala.xmarket.data.pojo.TradeTag;
import me.jcala.xmarket.mvp.a_base.CommonAdapter;
import me.jcala.xmarket.mvp.main.MainActivity;
import me.jcala.xmarket.util.ViewHolder;

public class SortTagPresenterImpl implements SortTagPresenter,SortTagModel.onGainListener {
    private SortTagModel mModel;
    private SortTagView mView;
    private Context mContext;

    public SortTagPresenterImpl(Context context, SortTagView view){
        mContext=context;
        mView = view;
        mModel = new SortTagModelImpl();
    }

    @Override
    public void doGetSortTag() {
        mModel.getSortTag(this);
    }

    @Override
    public void onSuccess(List<TradeTag> tradeTagList) {
        BaseAdapter adapter=new CommonAdapter<TradeTag>(mContext, tradeTagList,R.layout.sort_grid_item) {
            @Override
            public void convert(ViewHolder viewHolder, TradeTag dataEntity) {
                viewHolder.setText(R.id.grid_tv, dataEntity.getName());
                viewHolder.setFrescoImg(R.id.grid_iv, Uri.parse(dataEntity.getBgPic()));
            }
        };
        AdapterView.OnItemClickListener listener=(AdapterView<?> parent, View view, int position, long id)->{
            TradeTag entity = tradeTagList.get(position);
            Intent intent=new Intent(mContext,MainActivity.class);
            intent.putExtra("sortId",entity.getId());
            mContext.startActivity(intent);
        };
        mView.whenSuccess(adapter,listener);
    }
    @Override
    public void onFailure(String errMsg) {
        mView.whenFail(errMsg);
    }
}
