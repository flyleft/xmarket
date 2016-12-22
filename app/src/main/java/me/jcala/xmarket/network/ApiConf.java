package me.jcala.xmarket.network;

public interface ApiConf {

    int DEFAULT_TIMEOUT = 5;
    //-----------------------------用户相关---------------------------
    String login="api/v1/login";//用户登录+
    String auth="api/v1/auth";//用户获取token
    String register="api/v1/register";//用户注册+
    String register_next="api/v1/{userId}/phoneSchool/update";//用户注册下一步，设置学校，手机号+
    String update_user_pass="api/v1/users/{userId}/avatar/update";//修改用户密码
    String update_user_avatar="api/v1/users/{userId}/pass/update";//修改用户头像
    String get_user_team="api/v1/users/{userId}/teams/get";//获取用户志愿队
    String get_user_trades="api/v1/users/{userId}/trades/get";//获取用户在售，已卖，已买，捐赠，待确认的商品列表
    String get_user_messages="api/v1/users/{userId}/messages/get";//获取用户交易信息
    String donate_user_trade="api/v1/users/{userId}/trades/donate";//捐赠商品


    //----------------------------商品相关----------------------------
    String get_tag_trades="api/v1/trades/tag/{tagName}/get";//获取该分类下所有商品列表
    String get_school_trades="api/v1/trades/school/{schoolName}/get";//获取该学校的商品列表
    String get_team_trades="api/v1/trades/team/{teamName}/get";
    String get_trade="api/v1/trades/{tradeId}/get";//通过id获取商品的详细信息
    String create_trade="api/v1/trades/create";//发布商品


    //------------------------Hybrid 志愿队相关------------------------
    String create_team="api/v1/teams/create";//创建志愿队
    String get_school_teams="/api/v1/teams/{schoolName}/get";//？type=0获取该学校下的所有志愿队.0获取志愿队列表，1获取志愿队名称列表
    //------------------------Hybrid 学校相关--------------------------
    String get_schools="api/v1/schools/names/get";//获取学校名称列表
    //------------------------Hybrid 文件相关--------------------------
    String get_img="api/v1/file/img/{dir}/{picName:.+}";//获取图片资源
    //------------------------Hybrid 分类相关--------------------------
    String get_tags="api/v1/tags/get";//获取商品分类列表
    //------------------------Hybrid 交易相关--------------------------
    String create_deal="api/v1/deals/create";//创建交易
    String confirm_deal="api/v1/deals/{messageId}/update";//确认进行交易
}
