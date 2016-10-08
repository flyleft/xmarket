package me.jcala.xmarket.service;

import me.jcala.xmarket.data.entity.get.Result;
import me.jcala.xmarket.domain.UserBean;

public interface UserSer {
    Result login(UserBean bean);
    Result register(UserBean bean);
}
