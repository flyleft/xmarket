package me.jcala.xmarket.mvp.sort;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import java.util.List;

import me.jcala.xmarket.R;
import me.jcala.xmarket.data.entity.SortTag;
import me.jcala.xmarket.mvp.a_base.BaseFragment;
import me.jcala.xmarket.mvp.a_base.CommonAdapter;
import me.jcala.xmarket.util.ViewHolder;

public class SortFrgment extends BaseFragment implements SortTagView{
    private GridView gridView;
    private SortTagPre presenter;
    @Override
    protected int getLayoutResId() {
        return R.layout.sort_fragment;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        gridView=(GridView)view.findViewById(R.id.sort_grid);
        presenter = new SortTagPreImpl(this);
        presenter.doGetSortTag();
    }

    @Override
    public void whenSuccess(List<SortTag> tags) {
        gridView.setAdapter(new CommonAdapter<SortTag>(getActivity(),tags,R.layout.sort_grid_item) {
            @Override
            public void convert(ViewHolder viewHolder, SortTag dataEntity) {
                viewHolder.setText(R.id.grid_tv, dataEntity.getName());
                viewHolder.setImageResourcewithFresco(R.id.grid_iv, Uri.parse(dataEntity.getBgPic()));
            }
        });
    }

    @Override
    public void whenFail(String msg) {
    }
}
