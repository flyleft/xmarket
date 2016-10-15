package me.jcala.xmarket.conf;

public class AppConf {
    /**
     * 为了方便本地开发的网络配置
     * 当reqExcute为reqExcuteLocal表示直接使用APP代码中的测试数据
     * 当reqExcute为reqExcuteNormal通过retrofit获取服务器数据
     */
    public static final int reqExcuteLocal=0;
    public static final int reqExcuteNormal=1;
    public static final int reqExcute=reqExcuteLocal;
}
