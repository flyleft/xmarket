package me.jcala.xmarket.util;

public class StringUtils {
    public static boolean checkNotNull(String str){
        return !(str==null||str.length()<1);
    }
}
