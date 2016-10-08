package me.jcala.xmarket.ui.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import me.jcala.xmarket.util.ViewHolder;

/**
 * Created by Administrator on 2016/10/9.
 */

public abstract class CommonAdapter<T> extends BaseAdapter {
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    private int layoutId;
    public CommonAdapter(Context context, List<T> mDatas,int layoutId) {
        mInflater=LayoutInflater.from(context);
        this.mContext = context;
        this.mDatas = mDatas;
        this.layoutId=layoutId;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public  View getView(int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder = ViewHolder.get(mContext, convertView, parent,layoutId, position);
        convert(viewHolder,getItem(position));
        return viewHolder.getConvertView();
    }

    public abstract void  convert(ViewHolder viewHolder, T t);

}
