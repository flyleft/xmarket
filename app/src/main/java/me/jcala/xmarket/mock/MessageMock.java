package me.jcala.xmarket.mock;

import java.util.ArrayList;
import java.util.List;

import me.jcala.xmarket.data.dto.MsgDto;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Message;

public class MessageMock {

    public Result<MsgDto> gainMsg(){
        Result<MsgDto> result=new Result<>();
        MsgDto dto=new MsgDto();
        dto.setAllNum(100);
        List<Message> messages=new ArrayList<>();
        Message msg1=new Message("jcala","https://jcalaz.github.io/img/sort_avater_cluo.jpg","1856945","https://jcalaz.github.io/img/sort_book.jpg",0,"1");
        Message msg2=new Message("小明","https://jcalaz.github.io/img/sort_clothes.jpeg","","https://jcalaz.github.io/img/sort_life.jpg",1,"2");
        Message msg3=new Message("小王","https://jcalaz.github.io/img/sort_body.jpg","1856945","https://jcalaz.github.io/img/sort_phone.jpg",2,"3");
        Message msg4=new Message("Lili","https://jcalaz.github.io/img/sort_avater_cluo.jpg","1856945","https://jcalaz.github.io/img/sort_elec.jpg",0,"4");
        Message msg5=new Message("sun","https://jcalaz.github.io/img/sort_clothes.jpeg","","https://jcalaz.github.io/img/sort_parts.jpg",1,"5");
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
