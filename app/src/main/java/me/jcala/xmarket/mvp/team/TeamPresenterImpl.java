package me.jcala.xmarket.mvp.team;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import java.util.List;

import me.jcala.xmarket.R;
import me.jcala.xmarket.data.pojo.Team;
import me.jcala.xmarket.mvp.a_base.CommonAdapter;
import me.jcala.xmarket.util.ViewHolder;

public class TeamPresenterImpl implements TeamPresenter,TeamModel.onGainTeamListener {
    private TeamModel model;
    private TeamView view;
    private Context context;

    public TeamPresenterImpl(Context context, TeamView view) {
        this.context = context;
        this.view = view;
        this.model = new TeamModelImpl();
    }

    @Override
    public void getTeams() {
        model.getTeams(this);
    }

    @Override
    public void onSuccess(List<Team> teamList) {
        BaseAdapter adapter=new CommonAdapter<Team>(context,teamList, R.layout.team_item) {
            @Override
            public void convert(ViewHolder viewHolder, Team item) {
                viewHolder.setFrescoImg(R.id.team_img, Uri.parse(item.getImg()));
                viewHolder.setText(R.id.team_name,item.getName());
                viewHolder.setText(R.id.team_description,item.getDescription());
            }
        };
        AdapterView.OnItemClickListener listener=(AdapterView<?> parent, View view, int position, long id)->{
        };
        view.whenGetTeamSuc(adapter,listener);

    }

    @Override
    public void onFailure() {

    }
}
