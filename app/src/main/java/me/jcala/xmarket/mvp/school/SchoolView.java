package me.jcala.xmarket.mvp.school;

import android.widget.AdapterView;
import android.widget.BaseAdapter;

public interface SchoolView {
    void whenLoadDataSuc(BaseAdapter adapter,AdapterView.OnItemClickListener listener);//当数据获取成功
}
