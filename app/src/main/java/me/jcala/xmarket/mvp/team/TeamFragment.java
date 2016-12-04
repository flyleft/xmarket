package me.jcala.xmarket.mvp.team;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.jcala.xmarket.R;
import me.jcala.xmarket.mvp.a_base.BaseFragment;
import me.jcala.xmarket.view.RecyclerCommonAdapter;

public class TeamFragment extends BaseFragment implements TeamView {
    private Unbinder unbinder;

    @BindView(R.id.team_list)
    protected RecyclerView recyclerView;

    private TeamPresenter presenter;
    @BindView(R.id.school_deal_plus)
    protected FloatingActionButton fab;
    @Override
    protected int getLayoutResId() {
        return R.layout.team_fragment;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        unbinder= ButterKnife.bind(this,view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        presenter=new TeamPresenterImpl(getActivity(),this);
        presenter.getTeams();
        fab.setOnClickListener((View v) ->{
                Toast.makeText(getActivity(),"fab",Toast.LENGTH_LONG).show();
            }
        );
    }

    @Override
    public void whenGetTeamSuc(RecyclerCommonAdapter<?> adapter) {
       recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
