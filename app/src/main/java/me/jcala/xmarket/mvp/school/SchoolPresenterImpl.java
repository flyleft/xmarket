package me.jcala.xmarket.mvp.school;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import java.util.List;

import me.jcala.xmarket.R;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Trade;
import me.jcala.xmarket.mvp.a_base.CommonAdapter;
import me.jcala.xmarket.mvp.main.MainActivity;
import me.jcala.xmarket.util.ViewHolder;

public class SchoolPresenterImpl implements SchoolModel.onGainListener,SchoolPresenter {
    private SchoolModel model;
    private SchoolView view;
    private Context context;

    public SchoolPresenterImpl(Context context,SchoolView view) {
        this.context = context;
        this.view = view;
        this.model=new SchoolModelImpl();
    }

    @Override
    public void onComplete(Result<List<Trade>> result) {
        if (!resultHandler(result)){
            return;
        }
        BaseAdapter adapter=new CommonAdapter<Trade>(context,result.getData(), R.layout.school_item) {
            @Override
            public void convert(ViewHolder viewHolder, Trade item) {
                viewHolder.setText(R.id.deal_title,item.getTitle());
                viewHolder.setFrescoImg(R.id.deal_img, Uri.parse(item.getImgUrls().get(0)));
                viewHolder.setFrescoImg(R.id.author_img,Uri.parse(item.getAuthor().getAvatarUrl()));
                viewHolder.setText(R.id.author_name,item.getAuthor().getUsername());
                viewHolder.setText(R.id.deal_price,"￥ "+item.getPrice());
            }
        };
        AdapterView.OnItemClickListener listener=(AdapterView<?> parent, View view, int position, long id)->{
            Trade item=result.getData().get(position);
            Intent intent=new Intent(context,MainActivity.class);
            intent.putExtra("sortId",item.getId());
            context.startActivity(intent);
        };
        view.whenLoadDataSuc(adapter,listener);

    }
    private boolean resultHandler(Result<?> result){
        if (result==null){
            return false;
        }
        switch (result.getCode()) {
            case 100:
                return true;
            case 99:
                return false;
            default:
                return false;
        }
    }

    @Override
    public void onFail() {

    }

    @Override
    public void getSchoolDealAgency() {
       model.getSchoolDeals(1,this);
    }
}
