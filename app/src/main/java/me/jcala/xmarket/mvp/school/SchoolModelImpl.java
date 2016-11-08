package me.jcala.xmarket.mvp.school;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;

import me.jcala.xmarket.conf.ApiConf;
import me.jcala.xmarket.data.pojo.DealItem;

public class SchoolModelImpl implements SchoolModel{

    @Override
    public void getSchoolDeals(int page,final onGainListener listener) {
        if (ApiConf.execute==1){
            execute(listener);
        }else {
            executeLocal(listener);
        }
    }

    private void executeLocal(final onGainListener listener){
        String jsonStr="[\n" +
                "  {\n" +
                "    \"author\": {\n" +
                "      \"avatar_url\": \"https://jcalaz.github.io/img/sort_clothes.jpeg\",\n" +
                "      \"username\": \"安琪\"\n" +
                "    },\n" +
                "    \"imgs\": [\n" +
                "      \"https://jcalaz.github.io/img/sort_body.jpg\"\n" +
                "    ],\n" +
                "    \"price\": 9,\n" +
                "    \"status\": 0,\n" +
                "    \"title\": \"3瓶脉动饮料\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": {\n" +
                "      \"avatar_url\": \"https://jcalaz.github.io/img/sort_avater_cluo.jpg\",\n" +
                "      \"username\": \"jcala\"\n" +
                "    },\n" +
                "    \"imgs\": [\n" +
                "      \"https://jcalaz.github.io/img/bg.jpg\"\n" +
                "    ],\n" +
                "    \"price\": 32,\n" +
                "    \"status\": 0,\n" +
                "    \"title\": \"盆栽\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": {\n" +
                "      \"avatar_url\": \"https://jcalaz.github.io/img/sort_clothes.jpeg\",\n" +
                "      \"username\": \"nice\"\n" +
                "    },\n" +
                "    \"imgs\": [\n" +
                "      \"https://jcalaz.github.io/img/sort_computer.jpg\"\n" +
                "    ],\n" +
                "    \"price\": 3659,\n" +
                "    \"status\": 0,\n" +
                "    \"title\": \"三星笔记本\"\n" +
                "  }\n" +
                "]";
        List<DealItem> itemList=new Gson().fromJson(jsonStr, new TypeToken<List<DealItem>>(){}.getType());
        listener.success(itemList);
    }


    private void execute(final onGainListener listener){

    }
}
