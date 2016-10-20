package me.jcala.xmarket.mvp.team;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.jcala.xmarket.R;
import me.jcala.xmarket.mvp.a_base.BaseFragment;

public class TeamFragment extends BaseFragment implements TeamView {
    private Unbinder unbinder;

    @BindView(R.id.team_list)
    protected ListView teamList;

    private TeamPresenter presenter;
    @Override
    protected int getLayoutResId() {
        return R.layout.team_fragment;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        unbinder= ButterKnife.bind(this,view);
        presenter=new TeamPresenterImpl(getActivity(),this);
        presenter.getTeams();
    }

    @Override
    public void whenGetTeamSuc(BaseAdapter adapter, AdapterView.OnItemClickListener listener) {
        teamList.setAdapter(adapter);
        teamList.setOnItemClickListener(listener);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
