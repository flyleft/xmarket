package me.jcala.xmarket.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import me.jcala.xmarket.network.Api;

/**
 * 返回给client的json对应的javabean
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Result<T> {
    private int code;//返回码
    private String msg;//返回信息
    private T data;//返回数据

    public Result<T> api(Api api){
        this.code=api.code();
        this.msg=api.msg();
        return this;
    }
}
