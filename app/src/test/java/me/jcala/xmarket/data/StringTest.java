package me.jcala.xmarket.data;

import org.junit.Test;

public class StringTest {

    @Test
    public void test(){
        String host="http://192.168.10.4:808080:344/";
        String newHost=getHostHome(host);
        System.out.print(newHost);
    }

    private String getHostHome(String home){
        if (home.startsWith("https://")){
            home= home.substring(8);
        }
        if (home.startsWith("http://")){
            home= home.substring(7);
        }
        int index=home.indexOf(":");
        return home.substring(0,index);
    }
}
