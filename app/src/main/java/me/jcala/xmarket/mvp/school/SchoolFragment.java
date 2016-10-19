package me.jcala.xmarket.mvp.school;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.jcala.xmarket.R;
import me.jcala.xmarket.data.entity.DealItem;
import me.jcala.xmarket.mvp.a_base.BaseFragment;


public class SchoolFragment extends BaseFragment implements SchoolView{
    private List<DealItem> items;
    private Unbinder unbinder;
    @BindView(R.id.school_deal_list)
    private ListView dealList;

    @Override
    protected int getLayoutResId() {
        return R.layout.school_fragment;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        unbinder= ButterKnife.bind(this,view);
    }

    @Override
    public void whenLoadDataSuc(BaseAdapter adapter) {
       dealList.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
