package me.jcala.xmarket.mvp.message;

import me.jcala.xmarket.data.dto.MsgDto;
import me.jcala.xmarket.data.dto.Result;

public interface MessageModel {

    interface onMessageListener {
      void onComplete(Result<MsgDto> result);
    }
    void executeMsgReq(onMessageListener listener,int num);
    void executeConfirmDealReq(onMessageListener listener,String userId,String tradeId);
}
