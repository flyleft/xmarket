package me.jcala.xmarket.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private int resultCode;
    private String resultMsg;
    private T data;
}
