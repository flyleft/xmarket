package me.jcala.xmarket.mvp.team;

import me.jcala.xmarket.view.RecyclerCommonAdapter;

public interface TeamView {
    void whenGetTeamSuc(RecyclerCommonAdapter<?> adapter);//当获取team数据成功
    void whenHideRefresh();
}
