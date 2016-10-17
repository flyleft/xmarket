package me.jcala.xmarket.mvp.sort;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import java.util.List;

import javax.inject.Inject;

import me.jcala.xmarket.R;
import me.jcala.xmarket.data.entity.SortTag;
import me.jcala.xmarket.mvp.a_base.CommonAdapter;
import me.jcala.xmarket.mvp.main.MainActivity;
import me.jcala.xmarket.util.ViewHolder;

public class SortTagPresenterImpl implements SortTagPresenter,SortTagModel.OnGetSortTagListener{
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
    public void onSuccess(List<SortTag> sortTagList) {
        BaseAdapter adapter=new CommonAdapter<SortTag>(mContext,sortTagList,R.layout.sort_grid_item) {
            @Override
            public void convert(ViewHolder viewHolder, SortTag dataEntity) {
                viewHolder.setText(R.id.grid_tv, dataEntity.getName());
                viewHolder.setImageResourcewithFresco(R.id.grid_iv, Uri.parse(dataEntity.getBgPic()));
            }
        };
        AdapterView.OnItemClickListener listener=(AdapterView<?> parent, View view, int position, long id)->{
            SortTag entity = sortTagList.get(position);
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
