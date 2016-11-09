package me.jcala.xmarket.data.pojo;

/**
 * 封装消息的javabean
 */
public class Message {
    private String content;//消息内容
    private User fromUser;//消息发送方
    private User toUser;//消息接收方
    private Trade deal;//消息关注的商品
}
