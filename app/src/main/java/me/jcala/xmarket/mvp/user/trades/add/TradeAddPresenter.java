package me.jcala.xmarket.mvp.user.trades.add;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import java.util.List;

import okhttp3.MultipartBody;

public interface TradeAddPresenter {
    void gainTagList();//获取商品分类列表

    void releaseTrade(List<MultipartBody.Part> parts, TextInputLayout title,
                      TextInputLayout price, TextInputLayout desc,
                      EditText titleContent,EditText priceContent,
                      EditText descContent,String tag);//调用该方法正式发布商品
}
