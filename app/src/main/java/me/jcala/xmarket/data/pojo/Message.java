package me.jcala.xmarket.data.pojo;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 封装消息的javabean
 */
@Setter
@Getter
@ToString
public class Message extends RealmObject{
    @PrimaryKey
    private String id;
    private String reqMsgId;
    private String belongId;//消息所属者的id
    private String userId;//交易对方用户的id
    private String username;//交易对方用户的用户名
    private String userAvatar;//交易对方用户的头像地址
    private String userPhone;//交易对方用户的手机号。如果kind为0则手机号可见；如果kind为1则手机号不可见，设置默认值""
    private String tradeId;//商品id
    private String tradeImg;//商品的封面
    private int kind;//kind表示信息类型。0表示买到商品的消息;1表示收到购买请求的消息;2表示已确认(交易完成)；3表示自己的志愿队收到捐赠商品

    public Message() {
    }

    public Message(String username, String userAvatar, String userPhone, String tradeImg, int kind, String id) {
        this.username=username;
        this.userAvatar=userAvatar;
        this.userPhone=userPhone;
        this.tradeImg=tradeImg;
        this.kind=kind;
        this.id=id;
    }
}
