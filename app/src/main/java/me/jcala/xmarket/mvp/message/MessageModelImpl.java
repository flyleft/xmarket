package me.jcala.xmarket.mvp.message;

import me.jcala.xmarket.AppConf;
import me.jcala.xmarket.mock.MessageMock;

public class MessageModelImpl implements MessageModel{

    @Override
    public void executeMsgReq(onMessageListener listener, int num) {
       if (AppConf.useMock){
           listener.onComplete(new MessageMock().gainMsg());
           return;
       }
    }

}
