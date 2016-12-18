package me.jcala.xmarket.mvp.message;

import me.jcala.xmarket.data.pojo.Message;
import me.jcala.xmarket.view.RecyclerCommonAdapter;

public interface MessageView {

    void whenNeedUpdateMsgList(RecyclerCommonAdapter<?> adapter);

    void whenShowConfirmDialog(Message item);

    void whenHideRefresh();//隐藏下拉刷新
}
