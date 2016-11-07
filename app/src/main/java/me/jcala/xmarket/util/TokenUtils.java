package me.jcala.xmarket.util;

/**
 * 关于token的一些操作
 * 包括从token在sharepreference中的存取
 * 更新token
 */
public class TokenUtils {

    private String token="";//用于存储token的值

    private static class TokenHolder{
        private static TokenUtils tokenUtils=new TokenUtils();
    }
    public static TokenUtils instance=TokenHolder.tokenUtils;



}
