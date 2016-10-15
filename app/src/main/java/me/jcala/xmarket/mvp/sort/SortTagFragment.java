package me.jcala.xmarket.mvp.sort;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.Unbinder;
import me.jcala.xmarket.R;
import me.jcala.xmarket.data.entity.SortTag;
import me.jcala.xmarket.mvp.a_base.BaseFragment;
import me.jcala.xmarket.mvp.a_base.CommonAdapter;
import me.jcala.xmarket.mvp.main.MainActivity;
import me.jcala.xmarket.util.ViewHolder;

public class SortTagFragment extends BaseFragment implements SortTagView{
    @BindView(R.id.sort_grid)
    GridView gridView;
    private SortTagPre presenter;
    private Unbinder unbinder;
    @Override
    protected int getLayoutResId() {
        return R.layout.sort_fragment;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        unbinder=ButterKnife.bind(this,view);
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

        AdapterView.OnItemClickListener listener=(AdapterView<?> parent, View view, int position, long id)->{
            SortTag entity = tags.get(position);
            Intent intent=new Intent(getActivity(),MainActivity.class);
            intent.putExtra("sortId",entity.getId());
            startActivity(intent);
        };
        gridView.setOnItemClickListener(listener);
    }
    @Override
    public void whenFail(String msg) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
