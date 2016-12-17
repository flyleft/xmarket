package me.jcala.xmarket.data.pojo;


import java.util.List;

import io.realm.RealmObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *用户信息封装类
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User extends RealmObject {
    private String id;
    private String token;//token值，只用于传输，不存储在数据库中
    private String username;//用户名
    private String password;//用户密码
    private String school;//所在学校名称
    private String phone;//手机号
    private String avatarUrl;//头像地址
    private List<String> sellTrades;//用户在售商品id列表
    private List<String> soldTrades;//用户已售商品id列表
    private List<String> donateTrades;//捐赠商品id列表
    private List<String> boughtTrades;//已买到商品id列表
    private List<String> toBeConfirmTrades;//待确认商品id列表
    private List<String> teams;//志愿队的id列表

    public User(String username,String password) {
        this.username=username;
        this.password=password;
    }
}
