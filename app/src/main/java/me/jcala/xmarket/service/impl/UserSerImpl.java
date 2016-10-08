package me.jcala.xmarket.service.impl;

import me.jcala.xmarket.data.entity.get.Result;
import me.jcala.xmarket.domain.UserBean;
import me.jcala.xmarket.service.UserSer;

/**
 * Created by Administrator on 2016/10/8.
 */

public class UserSerImpl implements UserSer {
    @Override
    public Result login(UserBean bean) {
        return new Result(1,"");
    }

    @Override
    public Result register(UserBean bean) {
        return null;
    }
}
