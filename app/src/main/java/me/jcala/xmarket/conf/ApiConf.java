package me.jcala.xmarket.conf;

public interface ApiConf {

    int DEFAULT_TIMEOUT = 5;
    int execute=0;
    String auth="api/v1/auth";
    String get_school_list="api/v1/schools/names/get";
    String register="api/v1/users/register";
    String register_next="api/v1/users/{userId}/phoneSchool/update";
    String confirm_deal="api/v1/users/{userId}/deal/create";
    String update_user_avatar="api/v1/users/{userId}/pass/update";
    String update_user_pass="api/v1/users/{userId}/avatar/update";
    String update_user_team="api/v1/users/{userId}/team/update";
    String create_user_trade="api/v1/users/{userId}/trades/create";
    String get_school_trades="api/v1/schools/trades/{schoolName}/{page}/get";
    String get_trade_tag="api/v1/tags/get";
    String get_school_teams="/api/v1/teams/{schoolName}/get";
    String get_trade_detail="/api/v1/trades/{tradeId}/get";
}
