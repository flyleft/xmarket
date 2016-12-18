package me.jcala.xmarket.mvp.school;

import me.jcala.xmarket.view.RecyclerCommonAdapter;

public interface SchoolView {
      void whenLoadDataSuc(RecyclerCommonAdapter<?> adapter);//当数据获取成功
      void whenHideRefresh();//隐藏下拉刷新
}
