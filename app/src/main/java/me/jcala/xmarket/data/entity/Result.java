package me.jcala.xmarket.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 返回给client的json对应的javabean
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private int code;//返回码
    private String msg;//返回信息
    private T data;//返回数据
}
