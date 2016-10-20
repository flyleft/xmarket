package me.jcala.xmarket.mvp.school;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.jcala.xmarket.R;
import me.jcala.xmarket.di.components.DaggerSchoolComponent;
import me.jcala.xmarket.di.modules.SchoolModule;
import me.jcala.xmarket.mvp.a_base.BaseFragment;


public class SchoolFragment extends BaseFragment implements SchoolView{
    @Inject
    protected SchoolPresenter presenter;

    private Unbinder unbinder;
    @BindView(R.id.school_deal_list)
    protected ListView dealList;

    @Override
    protected int getLayoutResId() {
        return R.layout.school_fragment;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        unbinder= ButterKnife.bind(this,view);
        DaggerSchoolComponent.builder().schoolModule(new SchoolModule(getActivity(),this)).build().inject(this);
        presenter.getSchoolDealAgency();
    }

    @Override
    public void whenLoadDataSuc(BaseAdapter adapter,AdapterView.OnItemClickListener listener) {
       dealList.setAdapter(adapter);
        dealList.setOnItemClickListener(listener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
