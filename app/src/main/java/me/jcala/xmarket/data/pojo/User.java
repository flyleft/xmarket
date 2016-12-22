package me.jcala.xmarket.data.pojo;


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
    private String username;//用户名
    private String password;//用户密码
    private String school;//所在学校名称
    private String phone;//手机号
    private String avatarUrl;//头像地址
}
