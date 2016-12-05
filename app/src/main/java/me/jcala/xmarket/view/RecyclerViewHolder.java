package me.jcala.xmarket.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.orhanobut.logger.Logger;

import me.jcala.xmarket.R;
import me.jcala.xmarket.data.pojo.Message;
import me.jcala.xmarket.mvp.message.MessageView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;
    private View mConvertView;
    private Context mContext;

    public RecyclerViewHolder(Context context, View itemView, ViewGroup parent)
    {
        super(itemView);
        mContext = context;
        mConvertView = itemView;
        mViews = new SparseArray<>();
    }


    public static RecyclerViewHolder get(Context context, ViewGroup parent, int layoutId)
    {

        View itemView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        RecyclerViewHolder holder = new RecyclerViewHolder(context, itemView, parent);
        return holder;
    }


    /**
     * 通过viewId获取控件
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId)
    {
        View view = mViews.get(viewId);
        if (view == null)
        {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public RecyclerViewHolder setText(int viewId, String text)
    {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public RecyclerViewHolder setImageResource(int viewId, int resId)
    {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    public RecyclerViewHolder setOnClickListener(int viewId,
                                         View.OnClickListener listener)
    {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }
    public RecyclerViewHolder setFrescoImg(int viewId, Uri uri){
        SimpleDraweeView view=getView(viewId);
        view.setImageURI(uri);
        return this;
    }

    public RecyclerViewHolder setLineBgColor(int viewId,int resId){
        LinearLayout layout=getView(viewId);
        layout.setBackgroundColor(resId);
        return this;
    }

    public RecyclerViewHolder setSendMsgListener(int viewId, String phoneNumber,String username){
        LinearLayout layout=getView(viewId);
        layout.setOnClickListener((View v)->{
            Uri smsToUri = Uri.parse("smsto:"+phoneNumber);
            Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
            intent.putExtra("sms_body","你好"+username+",");
            mContext.startActivity(intent);
        });
        return this;
    }

    public RecyclerViewHolder setConfirmDialogListener(int viewId, MessageView view, final Message item){
        LinearLayout layout=getView(viewId);
        layout.setOnClickListener((View v)->{
            if (item.getKind()==1){
                view.whenShowConfirmDialog(item);
            }
        });
        return this;
    }
}
