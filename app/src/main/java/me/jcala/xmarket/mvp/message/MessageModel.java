package me.jcala.xmarket.mvp.message;

public interface MessageModel {

    interface onMessageListener {

    }
    void executeMsgReq(onMessageListener listener,int num);
}
