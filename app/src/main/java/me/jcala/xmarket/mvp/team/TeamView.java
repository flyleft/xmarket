package me.jcala.xmarket.mvp.team;

import android.widget.AdapterView;
import android.widget.BaseAdapter;

public interface TeamView {
    void whenGetTeamSuc(BaseAdapter adapter, AdapterView.OnItemClickListener listener);//当获取team数据成功
}
