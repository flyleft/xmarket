package me.jcala.xmarket.mvp.school;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
    @BindView(R.id.school_deal_plus)
    protected FloatingActionButton fab;

    @Override
    protected int getLayoutResId() {
        return R.layout.school_fragment;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        unbinder= ButterKnife.bind(this,view);
        DaggerSchoolComponent.builder().schoolModule(new SchoolModule(getActivity(),this)).build().inject(this);
        presenter.getSchoolDealAgency();
        fab.setOnClickListener((View v) ->{
                    Toast.makeText(getActivity(),"fab",Toast.LENGTH_LONG).show();
                }
        );
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
