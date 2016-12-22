package me.jcala.xmarket.mvp.message;

import android.content.Context;
import android.net.Uri;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import me.jcala.xmarket.AppConf;
import me.jcala.xmarket.R;
import me.jcala.xmarket.data.dto.MsgDto;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Message;
import me.jcala.xmarket.data.pojo.User;
import me.jcala.xmarket.data.storage.UserIntermediate;
import me.jcala.xmarket.util.ResultInterceptor;
import me.jcala.xmarket.view.RecyclerCommonAdapter;
import me.jcala.xmarket.view.RecyclerViewHolder;

public class MessagePresenterImpl implements MessagePresenter,MessageModel.OnMessageListener {

    private Context context;
    private MessageModel model;
    private MessageView view;
    private Realm realmDefault;
    private volatile RecyclerCommonAdapter<?> adapter;

    public MessagePresenterImpl(Context context, MessageView view, Realm realm) {
        this.context = context;
        this.view = view;
        this.realmDefault = realm;
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
        newMsg.setReqMsgId(message.getId());
        newMsg.setKind(0);

        model.executeConfirmDealReq(this,newMsg,message);
    }

    @Override
    public void onConfirmComplete(Result<MsgDto> result, Message message) {
        if (!ResultInterceptor.instance.resultDataHandler(result)){
            return;
        }

        message.setKind(2);
        realmDefault.executeTransaction((Realm realm) ->{
               Message messageData= realm.where(Message.class).equalTo("id",message.getId()).findFirst();
                if (messageData==null){
                    return;
                }
                messageData.setKind(2);
        });

        if (adapter!=null){
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void refreshView() {
        String userId=UserIntermediate.instance.getUser(context).getId();
        model.executeMessageReq(this,0,userId,realmDefault);
    }

    @Override
    public void onHideRefresh() {
        view.whenHideRefresh();
    }

    @Override
    public void onGetMsgSuccess(List<Message> messageList) {
       initList(messageList);
    }

    @Override
    public void initView(){
        RealmQuery<Message> query =  realmDefault.where(Message.class);
        List<Message> data =  query.findAll();
        if (data.size()>0){
            initList(data);
        } else {
            String userId=UserIntermediate.instance.getUser(context).getId();
            model.executeMessageReq(this,0,userId,realmDefault);
        }
    }

    private void initList(List<Message> messageList){
        adapter=new RecyclerCommonAdapter<Message>(context,
                messageList, R.layout.message_item) {
            @Override
            public void convert(RecyclerViewHolder viewHolder, Message item) {
                if (item.getKind()==0){
                    viewHolder.setLineBgColor(R.id.message_kind_bg,context.getResources().getColor(R.color.md_red_300));
                    viewHolder.setText(R.id.message_kind_title,"卖家已确认，点击发送短信");
                    viewHolder.setSendMsgListener(R.id.message_item,item.getUserPhone(),item.getUsername());
                }else if (item.getKind()==3){
                    viewHolder.setLineBgColor(R.id.message_kind_bg,context.getResources().getColor(R.color.md_purple_300));
                    viewHolder.setText(R.id.message_kind_title,"收到捐赠商品，点击给捐赠者发送短信");
                    viewHolder.setSendMsgListener(R.id.message_item,item.getUserPhone(),item.getUsername());
                } else if (item.getKind() ==1){
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
}
