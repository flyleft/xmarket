package me.jcala.xmarket.data.storage;

import me.jcala.xmarket.data.pojo.User;

/**
 * User,token信息存储的中介
 */
public enum  UserIntermediator {
    instance;
    public final User user=new User();

    public void refreshUser(){

    }



}
