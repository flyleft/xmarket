package me.jcala.xmarket.conf;

public interface ApiConf {
    int DEFAULT_TIMEOUT = 5;

    String BASE_URL="http://127.0.0.1:8090/xmarket/";
    String login_url="user/login";
    String register_url="user/register";
    String school_url="items/school";
    String sort_url="items/sort";


    String common_err="网络错误";
    int action_success=1;
    int action_fail=-1;

    int login_um_err=2;
    int login_pass_err=3;

    int register_um_exist=2;
    int register_phone_exist=3;
}
