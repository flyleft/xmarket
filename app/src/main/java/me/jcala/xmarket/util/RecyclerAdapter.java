package me.jcala.xmarket.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.List;

public class RecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {

    private Context context;//上下文
    private List<T> list;//数据源
    private LayoutInflater inflater;//布局器
    private int itemLayoutId;//布局id
    private boolean isScrolling;//是否在滚动
    private AdapterView.OnItemClickListener listener;//点击事件监听器
    private AdapterView.OnItemLongClickListener longClickListener;//长按监听器
    private RecyclerView recyclerView;

    //在RecyclerView提供数据的时候调用
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }

    public interface OnItemClickListener {
        void onItemClick(RecyclerView parent, View view, int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(RecyclerView parent, View view, int position);
    }

    public void insert(T item, int position) {
        list.add(position, item);
        notifyItemInserted(position);
    }

    public void delete(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public RecyclerAdapter(Context context, List<T> list, int itemLayoutId) {
        this.context = context;
        this.list = list;
        this.itemLayoutId = itemLayoutId;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(itemLayoutId, parent, false);
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
