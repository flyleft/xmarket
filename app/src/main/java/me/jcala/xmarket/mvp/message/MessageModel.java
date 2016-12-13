package me.jcala.xmarket.mvp.message;

import me.jcala.xmarket.data.dto.MsgDto;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Message;

public interface MessageModel {

    interface onMessageListener {
        void onGetMsgListComplete(Result<MsgDto> result);
        void onConfirmComplete(Result<MsgDto> result,Message message);
    }
    void executeMsgReq(onMessageListener listener,String userId,int num);

    void executeConfirmDealReq(onMessageListener listener, Message newMsg,Message old);
}
