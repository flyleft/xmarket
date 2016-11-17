package me.jcala.xmarket.util;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;
    private int mPosition;
    private Context mContext;
    private RecyclerViewHolder(Context context, ViewGroup parent, int layoutId, int position, View view) {
        super(view);
        this.mPosition = position;
        mContext=context;
        this.mViews = new SparseArray<>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }

    public static RecyclerViewHolder get(Context context, View convertView,
                                         ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new RecyclerViewHolder(context, parent, layoutId, position,convertView);
        }else {
            RecyclerViewHolder holder = (RecyclerViewHolder) convertView.getTag();
            holder.mPosition=position;
            return holder;
        }
    }
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return mConvertView;
    }
    public RecyclerViewHolder setText(int viewId, String text){
        TextView tv =getView(viewId);
        tv.setText(text);
        return this;
    }
    public RecyclerViewHolder setFrescoImg(int viewId, Uri uri){
        SimpleDraweeView view=getView(viewId);
        view.setImageURI(uri);
        return this;
    }
}
