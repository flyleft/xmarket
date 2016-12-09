package me.jcala.xmarket.mock;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Trade;
import me.jcala.xmarket.data.pojo.TradeTag;
import me.jcala.xmarket.data.pojo.User;

public class TradeMock {

    public Result<List<TradeTag>> tradeTag(){
        String json="{\"code\":100,\"msg\":\"操作成功\",\"data\":[{\"id\":1,\"name\":\"学习资料\",\"bgPic\":" +
                "\"https://jcalaz.github.io/img/sort_book.jpg\"},{\"id\":2,\"name\":\"生活用品\",\"bgPic\":" +
                "\"https://jcalaz.github.io/img/sort_life.jpg\"},{\"id\":3,\"name\":\"衣物鞋帽\",\"bgPic\":" +
                "\"https://jcalaz.github.io/img/sort_clothes.jpeg\"},{\"id\":4,\"name\":\"运动健身\",\"bgPic\":" +
                "\"https://jcalaz.github.io/img/sort_body.jpg\"},{\"id\":5,\"name\":\"手机数码\",\"bgPic\":" +
                "\"https://jcalaz.github.io/img/sort_phone.jpg\"},{\"id\":6,\"name\":\"电脑办公\",\"bgPic\":" +
                "\"https://jcalaz.github.io/img/sort_computer.jpg\"},{\"id\":7,\"name\":\"电器\",\"bgPic\":" +
                "\"https://jcalaz.github.io/img/sort_elec.jpg\"},{\"id\":8,\"name\":\"数码配件\",\"bgPic\":" +
                "\"https://jcalaz.github.io/img/sort_parts.jpg\"},{\"id\":9,\"name\":\"租赁\",\"bgPic\":" +
                "\"https://jcalaz.github.io/img/sort_rent.jpg\"},{\"id\":9,\"name\":\"其他\",\"bgPic\":" +
                "\"https://jcalaz.github.io/img/sort_other.jpg\"}]}";
        return new Gson().fromJson(json,  new TypeToken<Result<List<TradeTag>>>() {}.getType());
    }

    public Result<List<String>> tradeTagStrings(){
        String json="{\"msg\":\"操作成功\",\"data\":[\"学习资料\",\"生活用品\",\"衣物鞋帽\",\"运动健身\"," +
                "\"手机数码\",\"电脑办公\",\"电器\",\"数码配件\",\"租赁\",\"其他\"],\"code\":100}";
        return new Gson().fromJson(json,  new TypeToken<Result<List<String>>>() {}.getType());
    }

    public Result<List<Trade>> gainSchoolTrades(){
        String jsonStr="[\n" +
                "  {\n" +
                "    \"author\": {\n" +
                "      \"avatarUrl\": \"https://jcalaz.github.io/img/sort_clothes.jpeg\",\n" +
                "      \"username\": \"安琪\"\n" +
                "    },\n" +
                "    \"imgUrls\": [\n" +
                "      \"https://jcalaz.github.io/img/sort_body.jpg\"\n" +
                "    ],\n" +
                "    \"price\": 9,\n" +
                "    \"status\": 0,\n" +
                "    \"title\": \"3瓶脉动饮料\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": {\n" +
                "      \"avatarUrl\": \"https://jcalaz.github.io/img/sort_avater_cluo.jpg\",\n" +
                "      \"username\": \"jcala\"\n" +
                "    },\n" +
                "    \"imgUrls\": [\n" +
                "      \"https://jcalaz.github.io/img/bg.jpg\"\n" +
                "    ],\n" +
                "    \"price\": 32,\n" +
                "    \"status\": 0,\n" +
                "    \"title\": \"盆栽\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": {\n" +
                "      \"avatarUrl\": \"https://jcalaz.github.io/img/sort_clothes.jpeg\",\n" +
                "      \"username\": \"nice\"\n" +
                "    },\n" +
                "    \"imgUrls\": [\n" +
                "      \"https://jcalaz.github.io/img/sort_computer.jpg\"\n" +
                "    ],\n" +
                "    \"price\": 3659,\n" +
                "    \"status\": 0,\n" +
                "    \"title\": \"三星笔记本\"\n" +
                "  }\n" +
                "]";
        List<Trade> trades= new Gson().fromJson(jsonStr, new TypeToken<List<Trade>>(){}.getType());
        Result<List<Trade>> result=new Result<>();
        result.setCode(100);
        result.setMsg("");
        result.setData(trades);
        return result;
    }

    public Result<Trade> gainTradeDetail(){
        Trade trade=new Trade();
        trade.setSchoolName("西南交通大学(犀浦校区)");
        trade.setCreateTime(1481293672727L);
        List<String> imgList=new ArrayList<>();
        imgList.add("https://jcalaz.github.io/img/sort_clothes.jpeg");
        imgList.add("https://jcalaz.github.io/img/sort_body.jpg");
        imgList.add("https://jcalaz.github.io/img/sort_computer.jpg");
        imgList.add("https://jcalaz.github.io/img/sort_book.jpg");
        trade.setImgUrls(imgList);
        User user=new User();
        user.setUsername("jcala");
        user.setAvatarUrl("https://jcalaz.github.io/img/sort_rent.jpg");
        trade.setAuthor(user);
        trade.setTitle("Mac 电脑");
        trade.setDesc("2014年在京东购买，还在保修期，I7CPU，独立显卡，8成新");
        trade.setPrice(55);
        Result<Trade> result=new Result<>();
        result.setCode(100);
        result.setMsg("");
        result.setData(trade);
        return result;
    }
}
