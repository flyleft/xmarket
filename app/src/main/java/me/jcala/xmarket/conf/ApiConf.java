package me.jcala.xmarket.conf;

public interface ApiConf {

    int DEFAULT_TIMEOUT = 5;
    int execute=0;
    String BASE_URL="http://192.168.0.105:80/api/v1/";
    String auth="auth";
    String get_school_list="schools/names/get";
    String register="users/register";
    String register_next="users/{userId}/phoneSchool/update";
    String update_user_avatar="{userId}/pass";
    String update_user_pass="users/{userId}/avatar";
    String update_user_team="users/{userId}/team";
    String get_trade_sort="trades/sort";
    String get_school_trades="schools/trades/{school_name}/{page}/get";

}
