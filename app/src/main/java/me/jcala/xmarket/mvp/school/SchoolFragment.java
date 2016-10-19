package me.jcala.xmarket.mvp.school;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
    private RecyclerView dealList;

    @Override
    protected int getLayoutResId() {
        return R.layout.school_fragment;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        unbinder= ButterKnife.bind(this,view);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        manager.setOrientation(OrientationHelper.VERTICAL);
        dealList.setLayoutManager(manager);
    }

    @Override
    public void whenLoadDataSuc() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
