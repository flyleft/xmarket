package me.jcala.xmarket.conf;

public interface ApiConf {
    int DEFAULT_TIMEOUT = 5;

    String BASE_URL="http://127.0.0.1:8090/xmarket/";
    String login_url="user/login";
    String register_url="user/register";
    String school_url="deal/school";
    String sort_url="deal/sort";

    String common_err="网络错误";
    int req_success=1;

}
