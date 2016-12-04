package me.jcala.xmarket.data.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import me.jcala.xmarket.data.pojo.Message;

/**
 * 仅用消息传输
 * 对应Result的Data值
 */
@Setter
@Getter
public class MsgDto {
    /**
     * 用户所有消息的总量。
     * 客户端通过比对客户端存储的数值和此值是否相同，确认是否需要更新消息列表
     */
    private int allNum;

    /**
     * 客户端获取的消息列表
     * 并不是用户所有消息，已经被限定了数量
     */
    private List<Message> msgs;
}