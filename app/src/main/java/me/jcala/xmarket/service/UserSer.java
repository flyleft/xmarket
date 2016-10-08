package me.jcala.xmarket.service;

import dagger.Module;
import me.jcala.xmarket.data.entity.get.Result;
import me.jcala.xmarket.domain.UserBean;

/**
 * Created by Administrator on 2016/10/8.
 */
@Module
public interface UserSer {
    Result login(UserBean bean);
}
