package me.jcala.xmarket.data.realm;

import io.realm.RealmObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RealmTrade extends RealmObject {
    private String id;//商品ID
    private String title;//商品名字
    private long price;
    private String img;
    private String authorName;
    private String authorImg;
    private String authorId;
}
