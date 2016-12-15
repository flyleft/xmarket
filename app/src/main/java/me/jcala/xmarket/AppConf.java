package me.jcala.xmarket;

public interface AppConf {

    boolean useMock=false;//是否使用本地离线数据，不与服务器进行交互；true表示使用离线，false使用服务器数据

    int Message_Interval=30;//轮询获取消息的时间间隔.(秒)

    String BASE_URL="http://192.168.10.6:80/";//服务器的xmarket-server地址及端口

}
