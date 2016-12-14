package me.jcala.xmarket.mvp.message;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import me.jcala.xmarket.data.pojo.Message;

public enum MessageIntermediate {
    instance;
    @Setter
    @Getter
    private List<Message> messageList=new ArrayList<>();
}
