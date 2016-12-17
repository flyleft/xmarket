package me.jcala.xmarket.mvp.team.add;

import java.util.List;

import me.jcala.xmarket.data.dto.Result;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface TeamAddModel {
    interface OnTeamAddListener{
        void onComplete(Result<String> result);
    }
    void executeTeamAddReq(OnTeamAddListener listener,RequestBody team,List<MultipartBody.Part> pics);
}
