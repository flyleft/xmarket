package me.jcala.xmarket.data.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * 封装消息的javabean
 */
@Setter
@Getter
public class Message {
    private String belongId;//消息所属者的id
    private String userId;//交易对方用户的id
    private String username;//交易对方用户的用户名
    private String phone;//交易对方用户的手机号。如果kind为0则手机号可见；如果kind为1则手机号不可见，设置默认值""
    private String tradeId;//商品id
    private String tradeImg;//商品的封面
    private int kind;//kind表示信息类型。0表示买到商品的消息；1表示收到购买请求的消息
}
