package me.jcala.xmarket.conf;

public interface ApiConf {

    int DEFAULT_TIMEOUT = 5;
    int execute=0;
    String BASE_URL="http://192.168.0.105:80/api/v1/";
    String auth="auth";
    String get_school_list="schools/names/get";
    String register="users/register";
    String update_user_school="users/{user_id}/update_school";
    String update_user_avatar="{user_id}/pass";
    String update_user_pass="users/{user_id}/avatar";
    String update_user_team="users/{user_id}/team";
    String get_trade_sort="trades/sort";
    String get_school_trades="schools/trades/{school_name}/{page}/get";

}
