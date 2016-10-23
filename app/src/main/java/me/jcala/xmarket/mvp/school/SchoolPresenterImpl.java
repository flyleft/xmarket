package me.jcala.xmarket.mvp.school;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import java.util.List;

import me.jcala.xmarket.R;
import me.jcala.xmarket.data.pojo.DealItem;
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
    public void success(List<DealItem> itemList) {
        BaseAdapter adapter=new CommonAdapter<DealItem>(context,itemList, R.layout.school_item) {
            @Override
            public void convert(ViewHolder viewHolder, DealItem item) {
                viewHolder.setText(R.id.deal_title,item.getTitle());
                viewHolder.setImageResithFresco(R.id.deal_img, Uri.parse(item.getImgs().get(0)));
                viewHolder.setImageResithFresco(R.id.author_img,Uri.parse(item.getAuthor().getAvatar_url()));
                viewHolder.setText(R.id.author_name,item.getAuthor().getUsername());
                viewHolder.setText(R.id.deal_price,"￥ "+item.getPrice());
            }
        };
        AdapterView.OnItemClickListener listener=(AdapterView<?> parent, View view, int position, long id)->{
            DealItem item=itemList.get(position);
            Intent intent=new Intent(context,MainActivity.class);
            intent.putExtra("sortId",item.getId());
            context.startActivity(intent);
        };
        view.whenLoadDataSuc(adapter,listener);

    }

    @Override
    public void fail() {

    }

    @Override
    public void getSchoolDealAgency() {
       model.getSchoolDeals(1,this);
    }
}
