package me.jcala.xmarket.mvp.message;

import android.content.Context;

public class MessagePresenterImpl implements MessagePresenter,MessageModel.onMessageListener {

    private Context context;
    private MessageModel model;
    private MessageView view;

    public MessagePresenterImpl(Context context, MessageView view) {
        this.context = context;
        this.view = view;
        this.model=new MessageModelImpl();
    }
}
