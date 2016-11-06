package me.jcala.xmarket.conf;

public interface ApiConf {

    int DEFAULT_TIMEOUT = 5;
    int excute=0;
    String BASE_URL="http://127.0.0.1:80/api/v1";
    String auth="/auth";
    String register="/users/register";
    String get_school_list="/school_list";
    String update_user_school="/users/{user_id}/update_school";
    String update_user_avatar="/{user_id}/pass";
    String update_user_pass="/users/{user_id}/avatar";
    String update_user_team="/users/{user_id}/team";
    String get_trade_sort="/trades/sort";
    String get_school_trades="/schools/{school_name}/{page}/trade_list";

}
