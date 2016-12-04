package me.jcala.xmarket.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import me.jcala.xmarket.view.ViewHolder;

public abstract class CommonAdapter<T> extends BaseAdapter {
     LayoutInflater inflater;
     Context context;
     List<T> data;
    private int layoutId;
    public CommonAdapter(Context context, List<T> data,int layoutId) {
        inflater=LayoutInflater.from(context);
        this.context = context;
        this.data = data;
        this.layoutId=layoutId;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public  View getView(int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder = ViewHolder.get(context, convertView, parent,layoutId, position);
        convert(viewHolder,getItem(position));
        return viewHolder.getConvertView();
    }

    public abstract void  convert(ViewHolder viewHolder, T t);

}
