package me.jcala.xmarket.util;

import me.jcala.xmarket.data.entity.UserBean;

public class CheckUtils {
    public static boolean checkNotEmpty(String str){
        return !(str==null||str.length()<1);
    }
    public static boolean checkLoginBean(UserBean bean){
        return checkNotEmpty(bean.getUsername())&&checkNotEmpty(bean.getPassword());
    }
    public static boolean checkRegisterBean(UserBean bean){
        return checkLoginBean(bean)&&checkNotEmpty(bean.getPhone());
    }
}
