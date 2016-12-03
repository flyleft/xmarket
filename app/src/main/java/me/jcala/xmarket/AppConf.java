package me.jcala.xmarket;

public interface AppConf {
    boolean useMock=true;//使用离线数据，不与服务器进行交互
    String BASE_URL="http://192.168.10.2:80/";//服务器的xmarket-server地址及端口
}
