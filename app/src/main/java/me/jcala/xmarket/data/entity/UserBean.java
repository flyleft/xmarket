package me.jcala.xmarket.data.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *用户信息封装类
 */
@Getter
@Setter
@ToString
public class UserBean {
    private String username;//用户名
    private String password;//用户密码
    private String school;//所在学校名称
    private String phone;//手机号
    private String avatar_url;//头像地址
}
