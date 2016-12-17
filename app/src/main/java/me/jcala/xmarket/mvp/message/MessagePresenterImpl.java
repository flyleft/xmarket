package me.jcala.xmarket.mvp.message;

import android.content.Context;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import me.jcala.xmarket.AppConf;
import me.jcala.xmarket.R;
import me.jcala.xmarket.data.dto.MsgDto;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Message;
import me.jcala.xmarket.data.pojo.User;
import me.jcala.xmarket.data.storage.UserIntermediate;
import me.jcala.xmarket.mock.MessageMock;
import me.jcala.xmarket.view.RecyclerCommonAdapter;
import me.jcala.xmarket.view.RecyclerViewHolder;

public class MessagePresenterImpl implements MessagePresenter,MessageModel.onMessageListener {

    private Context context;
    private MessageModel model;
    private MessageView view;
    private volatile RecyclerCommonAdapter<?> adapter;

    public MessagePresenterImpl(Context context, MessageView view) {
        this.context = context;
        this.view = view;
        this.model=new MessageModelImpl();
    }

    @Override
    public void confirmDeal(Message message) {
        User user= UserIntermediate.instance.getUser(context);
        if (user==null || user.getId()==null){
            return;
        }

        Message newMsg=new Message();
        newMsg.setUsername(user.getUsername());
        newMsg.setUserId(user.getId());
        newMsg.setUserAvatar(user.getAvatarUrl());
        newMsg.setUserPhone(user.getPhone());
        newMsg.setBelongId(message.getUserId());
        newMsg.setTradeId(message.getTradeId());
        newMsg.setTradeImg(message.getTradeImg());

        model.executeConfirmDealReq(this,newMsg,message);
    }

    @Override
    public void onConfirmComplete(Result<MsgDto> result, Message message) {
        message.setKind(2);
        if (adapter!=null){
            adapter.notifyDataSetChanged();
        }
    }

    public void updateMessageList(){
        List<Message> messageList;
        if (AppConf.useMock){
           messageList = new MessageMock().gainMsg().getData().getMsgs();
        }else {
           messageList=MessageIntermediate.instance.getMessageList();
        }
        adapter=new RecyclerCommonAdapter<Message>(context,
                messageList, R.layout.message_item) {
            @Override
            public void convert(RecyclerViewHolder viewHolder, Message item) {
                if (item.getKind()==0){
                    viewHolder.setLineBgColor(R.id.message_kind_bg,context.getResources().getColor(R.color.md_red_300));
                    viewHolder.setText(R.id.message_kind_title,"卖家已确认，点击发送短信");
                    viewHolder.setSendMsgListener(R.id.message_item,item.getUserPhone(),item.getUsername());
                }else if (item.getKind() ==1){
                    viewHolder.setConfirmDialogListener(R.id.message_item,view,item);
                }else {
                    viewHolder.setLineBgColor(R.id.message_kind_bg,context.getResources().getColor(R.color.md_brown_300));
                    viewHolder.setText(R.id.message_kind_title,"已确认，等待对方联系");
                }
                viewHolder.setFrescoImg(R.id.message_user_avatar,Uri.parse(AppConf.BASE_URL+item.getUserAvatar()));
                viewHolder.setFrescoImg(R.id.message_trade_img,Uri.parse(AppConf.BASE_URL+item.getTradeImg()));
                viewHolder.setText(R.id.message_user_phone,item.getUserPhone());
                viewHolder.setText(R.id.message_user_name,item.getUsername());
                if (AppConf.useMock){
                    viewHolder.setFrescoImg(R.id.message_user_avatar,Uri.parse(item.getUserAvatar()));
                    viewHolder.setFrescoImg(R.id.message_trade_img,Uri.parse(item.getTradeImg()));
                }
            }
        };
        view.whenNeedUpdateMsgList(adapter);
    }

    private boolean resultHandler(Result<?> result){
        if (result==null){
            return false;
        }
        switch (result.getCode()) {
            case 100:
                return true;
            case 99:
                return false;
            default:
                return false;
        }
    }

}
