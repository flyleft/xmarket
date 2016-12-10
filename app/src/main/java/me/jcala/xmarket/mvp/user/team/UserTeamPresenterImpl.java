package me.jcala.xmarket.mvp.user.team;

import android.content.Context;
import android.net.Uri;

import java.util.List;

import me.jcala.xmarket.R;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Team;
import me.jcala.xmarket.data.storage.UserIntermediate;
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
        if (result.getCode()!=100){
            return;
        }
        if (result.getData()==null){
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
        view.whenGetUserTeamSuccess(adapter);
    }

    @Override
    public void onFail(String errorMsg) {

    }
}
