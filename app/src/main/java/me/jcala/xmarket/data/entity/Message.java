package me.jcala.xmarket.data.entity;

/**
 * 封装消息的javabean
 */
public class Message {
    private String content;//消息内容
    private UserBean fromUser;//消息发送方
    private UserBean toUser;//消息接收方
    private DealItem deal;//消息关注的商品
}
