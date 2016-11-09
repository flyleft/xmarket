package me.jcala.xmarket.mvp.sort;

import android.widget.AdapterView;
import android.widget.BaseAdapter;


public interface TradeTagView {
    void whenSuccess(BaseAdapter adapter, AdapterView.OnItemClickListener listener);
    void whenFail(String msg);
}
