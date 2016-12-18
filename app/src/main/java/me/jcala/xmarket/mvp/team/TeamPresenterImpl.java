package me.jcala.xmarket.mvp.team;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import me.jcala.xmarket.AppConf;
import me.jcala.xmarket.R;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Team;
import me.jcala.xmarket.data.pojo.Trade;
import me.jcala.xmarket.data.realm.RealmTrade;
import me.jcala.xmarket.data.storage.UserIntermediate;
import me.jcala.xmarket.view.CommonAdapter;
import me.jcala.xmarket.view.RecyclerCommonAdapter;
import me.jcala.xmarket.view.RecyclerViewHolder;
import me.jcala.xmarket.view.ViewHolder;

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
        if (!resultHandler(result)){
            return;
        }
        initList(result.getData());
        final RealmResults<Team> results = realmDefault.where(Team.class).findAll();
        realmDefault.executeTransactionAsync((Realm realm) -> results.deleteAllFromRealm());
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
        view.whenGetTeamSuc(adapter);
    }
    private boolean resultHandler(Result<?> result){
        if (result==null){
            return false;
        }
        if (result.getData()==null){
            return false;
        }

        switch (result.getCode()) {
            case 100:
                return true;
            case 99:
                return false;
            default:
                return false;
        }
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
