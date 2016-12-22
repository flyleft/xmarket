package me.jcala.xmarket.mvp.team;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import me.jcala.xmarket.AppConf;
import me.jcala.xmarket.R;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Team;
import me.jcala.xmarket.data.storage.UserIntermediate;
import me.jcala.xmarket.mvp.team.trade.TeamTradeActivity;
import me.jcala.xmarket.util.ResultInterceptor;
import me.jcala.xmarket.view.RecyclerCommonAdapter;
import me.jcala.xmarket.view.RecyclerViewHolder;

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
    public void onComplete(Result<List<Team>> result,Realm realmDefault) {
        view.whenHideRefresh();
        if (!ResultInterceptor.instance.resultDataHandler(result)){
            return;
        }
        initList(result.getData());
        final RealmResults<Team> results = realmDefault.where(Team.class).findAll();
        realmDefault.executeTransaction((Realm realm) -> results.deleteAllFromRealm());
        realmDefault.executeTransactionAsync((Realm realm) -> realm.copyToRealm(result.getData()));
    }
    private void initList(List<Team> teams){
        RecyclerCommonAdapter<?> adapter=new RecyclerCommonAdapter<Team>(context,teams, R.layout.team_item) {
            @Override
            public void convert(RecyclerViewHolder viewHolder, Team item) {
                viewHolder.setFrescoImg(R.id.team_img, Uri.parse(AppConf.BASE_URL+item.getImg()));
                viewHolder.setText(R.id.team_name,item.getName());
                viewHolder.setText(R.id.team_description,item.getDescription());
                if (AppConf.useMock){
                    viewHolder.setFrescoImg(R.id.team_img, Uri.parse(item.getImg()));
                }
            }
        };
        RecyclerCommonAdapter.OnItemClickListener listener=(View view, int position) ->{
            Team item=teams.get(position);
            Intent intent=new Intent(context,TeamTradeActivity.class);
            intent.putExtra("team",item.getName());
            context.startActivity(intent);
        };
        adapter.setClickListener(listener);
        view.whenGetTeamSuc(adapter);
    }

    @Override
    public void refreshView(Realm realm) {
        String schoolName= UserIntermediate.instance.getUser(context).getSchool();
        model.executeGetTeamsReq(this,schoolName,0,realm);
    }

    @Override
    public void initView(Realm realm) {
        RealmQuery<Team> query=realm.where(Team.class);
        List<Team> data=query.findAll();
        if (data.size()>0){
            initList(data);
        }else {
            String schoolName= UserIntermediate.instance.getUser(context).getSchool();
            model.executeGetTeamsReq(this,schoolName,0,realm);
        }
    }
}
