package me.jcala.xmarket.util;


import java.util.regex.Pattern;

public class CheckUtils {
    public static boolean isEmpty(String str){
        return (str==null||str.length()<1);
    }
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
