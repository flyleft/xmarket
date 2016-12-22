## MVP开发模式的交易市场APP

> 一个学校项目，校园交易市场，可以发布商品，购买商品，确认之后可看见对方手机号，可以创建志愿队，捐赠商品给志愿队等。因为消息实时性要求不高，采用了轮询方式。

![screen](screenshot/screen.png )

####[后端地址https://github.com/jcalaz/xmarket-server](https://github.com/jcalaz/xmarket-server)

> 后端基于springboot+spring mvc+swagger+Spring data mongo+mongoDB,采用restful架构


### APP代码中的小实现
- [fresco自定义ImagePipeline，使用OkHttp加载图片,并加入SSL访问证书](https://github.com/jcalaz/xmarket/blob/master/app/src/main/java/me/jcala/xmarket/app/App.java)
- [retrofit通过okHttp拦截器实现token验证，过期自动获取新token](https://github.com/jcalaz/xmarket/blob/master/app/src/main/java/me/jcala/xmarket/network/TokenInterceptor.java)
- [retrofit支持https访问](https://github.com/jcalaz/xmarket/blob/master/app/src/main/java/me/jcala/xmarket/network/ReqExecutor.java)
- [RecyclerView万能适配器](https://github.com/jcalaz/xmarket/blob/master/app/src/main/java/me/jcala/xmarket/view/RecyclerCommonAdapter.java)
- [retrofit http日志打印](https://github.com/jcalaz/xmarket/blob/master/app/src/main/java/me/jcala/xmarket/network/ReqExecutor.java)
- [结合RxJava实现的后台轮询](https://github.com/jcalaz/xmarket/blob/master/app/src/main/java/me/jcala/xmarket/mvp/message/MessageService.java)
- [MVP模式的实现](https://github.com/jcalaz/xmarket/tree/master/app/src/main/java/me/jcala/xmarket/mvp/school)
- [Dagger2实现简单依赖注入](https://github.com/jcalaz/xmarket/tree/master/app/src/main/java/me/jcala/xmarket/di)
- [Realm数据库实现页面数据存储](https://github.com/jcalaz/xmarket/blob/master/app/src/main/java/me/jcala/xmarket/mvp/sort/TradeTagPresenterImpl.java)
- [fresco加载gif，实现启动动画](https://github.com/jcalaz/xmarket/blob/master/app/src/main/java/me/jcala/xmarket/mvp/splash/SplashActivity.java)
- [fresco实现圆形头像](https://github.com/jcalaz/xmarket/blob/master/app/src/main/res/layout/main_slide.xml)
- [RxJava+retrofit实现HTTP访问](https://github.com/jcalaz/xmarket/blob/master/app/src/main/java/me/jcala/xmarket/mvp/school/SchoolModelImpl.java)
- [retrofit实现多图片和javabean同时上传](https://github.com/jcalaz/xmarket/blob/master/app/src/main/java/me/jcala/xmarket/mvp/trade/add/TradeAddModelImpl.java)

### Server代码中的小实现
- [spring data mongo使用MongoTemplate实现复杂数据操作](https://github.com/jcalaz/xmarket-server/blob/master/src/main/java/me/jcala/xmarket/server/repository/CustomRepositoryImpl.java)
- [Multipart接收多多图片存储，并生成图片获取链接](https://github.com/jcalaz/xmarket-server/blob/master/src/main/java/me/jcala/xmarket/server/utils/FileTool.java)
- [使用SpringMVC拦截器验证Token是否过期和合法](https://github.com/jcalaz/xmarket-server/blob/master/src/main/java/me/jcala/xmarket/server/interceptor/TokenInterceptor.java)
- [swagger配置，自动根据springmvc的控制器注解生成API文档](https://github.com/jcalaz/xmarket-server/blob/master/src/main/java/me/jcala/xmarket/server/conf/RestConfig.java)
- [MongoRepository设置从mongo读取列](https://github.com/jcalaz/xmarket-server/blob/master/src/main/java/me/jcala/xmarket/server/repository/TradeRepository.java)
- [jwt token的创建](https://github.com/jcalaz/xmarket-server/blob/master/src/main/java/me/jcala/xmarket/server/repository/TradeRepository.java)
- [spring boot配置https](https://github.com/jcalaz/xmarket-server/blob/master/src/main/resources/application-dev.yml)

### 所用技术及模式
- MVP开发模式
- Retrofit: HTTP框架
- Rxjava: 响应式编程
- Fresco: 图片处理
- Token验证: JWT
- Realm: 数据库
- Dagger2: 依赖注入
- ButterKnife: 资源绑定
- Logger: 日志工具
- lombok: 注解生成代码小工具
- Rxgalleryfnal: 图片选择器

## 注意事项
- APP使用Androidtudio开发，后端采用idea。由于都使用了lombok，两个ide都需要安装lombok插件。
- fresco自定义了ImagePipeline，并且访问时加上了证书，所以只能加载本服务器的图片。

### 其他配置

- 服务器端访问路径
  1. APP，在AppConf中配置BASE_URL为服务器访问路径
  2. server，在application.yml中配置xmarket.address为服务器访问路径
- 默认采用HTTPS协议，如果想使用http协议
  1. APP，将AppConf的enabled_ssl设置为false
  2. server，application.yml中将server.ssl.enabled设置为false

- 服务器图片存储物理路径：
- APP轮询频率：设置AppConf中的Message_Interval

- APP每页商品加载的条数：设置AppConf中的size

- 服务器图片存储路径： application.yml中设置xmarket.pic_home

### https证书使用keytool生成,生成命令
```
keytool -genkey -alias xmarketkey -keyalg RSA -keysize 1024 -keypass sdjkasl465sd -validity 365 -keystore g:\home\xmarket.keystore -storepass 546sdhjdf  //生成证书

keytool -list  -v -keystore g:\home\xmarket.keystore -storepass 546sdhjdf //查看证书

keytool -export -alias xmarketkey -keystore g:\home\xmarket.keystore -file g:\home\xmarket.crt -storepass 546sdhjdf //导出证书

keytool -printcert -file g:\home\xmarket.crt //查看证书

```

### API路径
```java
public interface ApiConf {
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
```
