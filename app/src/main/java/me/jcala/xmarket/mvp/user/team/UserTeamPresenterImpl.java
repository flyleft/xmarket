package me.jcala.xmarket.mvp.user.team;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import java.util.List;

import me.jcala.xmarket.R;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Team;
import me.jcala.xmarket.data.storage.UserIntermediate;
import me.jcala.xmarket.mvp.team.trade.TeamTradeActivity;
import me.jcala.xmarket.util.ResultInterceptor;
import me.jcala.xmarket.view.RecyclerCommonAdapter;
import me.jcala.xmarket.view.RecyclerViewHolder;

public class UserTeamPresenterImpl
        implements UserTeamPresenter,UserTeamModel.onUserTeamListener{
    private Context context;
    private UserTeamView view;
    private UserTeamModel model;

    public UserTeamPresenterImpl(Context context, UserTeamView view) {
        this.context = context;
        this.view = view;
        this.model=new UserTeamModelImpl();
    }

    @Override
    public void gainUserTeamList() {
        String userId= UserIntermediate.instance.getUser(context).getId();
        model.executeUserTeamReq(this,userId);
    }

    @Override
    public void onComplete(Result<List<Team>> result) {
        if (!ResultInterceptor.instance.resultDataHandler(result)){
           return;
        }
        RecyclerCommonAdapter<?> adapter=new RecyclerCommonAdapter<Team>(context,result.getData(), R.layout.team_item) {
            @Override
            public void convert(RecyclerViewHolder viewHolder, Team item) {
                viewHolder.setFrescoImg(R.id.team_img, Uri.parse(item.getImg()));
                viewHolder.setText(R.id.team_name,item.getName());
                viewHolder.setText(R.id.team_description,item.getDescription());
            }
        };
        RecyclerCommonAdapter.OnItemClickListener listener=(View view, int position) ->{
            Team item=result.getData().get(position);
            Intent intent=new Intent(context,TeamTradeActivity.class);
            intent.putExtra("team",item.getName());
            context.startActivity(intent);
        };
        adapter.setClickListener(listener);
        view.whenGetUserTeamSuccess(adapter);
    }
}
