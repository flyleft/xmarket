package me.jcala.xmarket.mvp.message;

import java.util.List;

import io.realm.Realm;
import me.jcala.xmarket.data.dto.MsgDto;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Message;
import me.jcala.xmarket.mvp.team.add.TeamAddModel;

public interface MessageModel {

    interface OnMessageListener {
        void onConfirmComplete(Result<MsgDto> result,Message message);
        void onGetMsgSuccess(List<Message> messageList);
        void onHideRefresh();
    }
    void executeConfirmDealReq(OnMessageListener listener, Message newMsg,Message old);
    void executeMessageReq(OnMessageListener listener, int num,String userId, Realm realm);
}
