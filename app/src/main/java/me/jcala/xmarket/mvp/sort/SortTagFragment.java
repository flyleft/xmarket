package me.jcala.xmarket.mvp.sort;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.jcala.xmarket.R;
import me.jcala.xmarket.mvp.a_base.BaseFragment;

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
        presenter = new SortTagPreImpl(getActivity(),this);
        presenter.doGetSortTag();
    }
    @Override
    public void whenSuccess(BaseAdapter adapter,AdapterView.OnItemClickListener listener) {
        gridView.setAdapter(adapter);

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
