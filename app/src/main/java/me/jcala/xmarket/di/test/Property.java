package me.jcala.xmarket.di.test;

import javax.inject.Inject;
import lombok.Getter;

/**
 * Created by Administrator on 2016/10/9.
 */
public class Property {
    @Getter
    private String username;
    @Inject
    public Property(){
        username="张三";
    }
}
