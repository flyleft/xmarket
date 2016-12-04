package me.jcala.xmarket.mock;

import java.util.ArrayList;
import java.util.List;

import me.jcala.xmarket.data.dto.MsgDto;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Message;

public class MessageMock {
/*
   private String username;//交易对方用户的用户名
    private String phone;//交易对方用户的手机号。如果kind为0则手机号可见；如果kind为1则手机号不可见，设置默认值""
    private String tradeId;//商品id
    private String tradeImg;//商品的封面
    private int kind;//kind表示信息类型。0表示买到商品的消息；1表示收到购买请求的消息
 */
    public Result<MsgDto> gainMsg(){
        Result<MsgDto> result=new Result<>();
        MsgDto dto=new MsgDto();
        dto.setAllNum(100);
        List<Message> messages=new ArrayList<>();
        //String username,String userAvatar,String userPhone, String tradeImg,int kind
        Message msg1=new Message("jcala","https://jcalaz.github.io/img/sort_avater_cluo.jpg","1856945","https://jcalaz.github.io/img/sort_book.jpg",0);
        Message msg2=new Message("小明","https://jcalaz.github.io/img/sort_clothes.jpeg","","https://jcalaz.github.io/img/sort_life.jpg",1);
        Message msg3=new Message("jcala","https://jcalaz.github.io/img/sort_body.jpg","1856945","https://jcalaz.github.io/img/sort_phone.jpg",0);
        Message msg4=new Message("jcala","https://jcalaz.github.io/img/sort_avater_cluo.jpg","1856945","https://jcalaz.github.io/img/sort_elec.jpg",0);
        Message msg5=new Message("jcala","https://jcalaz.github.io/img/sort_clothes.jpeg","","https://jcalaz.github.io/img/sort_parts.jpg",1);
        messages.add(msg1);
        messages.add(msg2);
        messages.add(msg3);
        messages.add(msg4);
        messages.add(msg5);
        dto.setMsgs(messages);
        result.setCode(100);
        result.setMsg("");
        result.setData(dto);
        return result;
    }
}
