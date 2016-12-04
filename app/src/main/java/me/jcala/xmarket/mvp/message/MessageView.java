package me.jcala.xmarket.mvp.message;

import me.jcala.xmarket.view.RecyclerCommonAdapter;

public interface MessageView {

    void whenNeedUpdateMsgList(RecyclerCommonAdapter<?> adapter);

}
