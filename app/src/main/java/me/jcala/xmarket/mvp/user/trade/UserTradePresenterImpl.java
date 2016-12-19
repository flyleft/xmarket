package me.jcala.xmarket.mvp.user.trade;

import android.content.Context;

import java.util.List;

import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Trade;

public class UserTradePresenterImpl implements UserTradePresenter,
        UserTradeModel.OnUserTradeListener {

    private Context context;
    private UserTradeView view;
    private UserTradeModel model;

    public UserTradePresenterImpl(Context context, UserTradeView view) {
        this.context = context;
        this.view = view;
        this.model=new UserTradeModelImpl();
    }

    @Override
    public void onCompleteListener(Result<List<Trade>> result) {

    }

    @Override
    public void initViewList(int type) {

    }

}
