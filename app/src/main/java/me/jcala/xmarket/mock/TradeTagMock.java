package me.jcala.xmarket.mock;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.TradeTag;

public class TradeTagMock {

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
}
