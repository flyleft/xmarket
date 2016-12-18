package me.jcala.xmarket.data.pojo;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Getter;
import lombok.Setter;

/**
 * 分类列表封装类
 */
@Setter
@Getter
public class TradeTag extends RealmObject{
    private int id;//分类的id
    private String name;//分类的名称
    private String bgPic;//分类的背景图片

    public TradeTag() {
    }
}
