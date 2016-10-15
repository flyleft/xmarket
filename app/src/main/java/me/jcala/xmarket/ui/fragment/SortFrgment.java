package me.jcala.xmarket.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import me.jcala.xmarket.R;
import me.jcala.xmarket.data.entity.SortTag;
import me.jcala.xmarket.ui.activity.MainActivity;
import me.jcala.xmarket.ui.base.BaseFragment;
import me.jcala.xmarket.ui.base.CommonAdapter;
import me.jcala.xmarket.util.ViewHolder;

public class SortFrgment extends BaseFragment {

    private GridView gridView;
    private List<SortTag> dataEntities=new ArrayList<>();
    @Override
    protected int getLayoutResId() {
        return R.layout.sort_fragment;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        gridView=(GridView)view.findViewById(R.id.sort_grid);
            setAdapter(dataEntities);
            setListener();
    }
    private void setListener(){
        AdapterView.OnItemClickListener listener=(AdapterView<?> parent, View view, int position, long id)->{
            SortTag entity = dataEntities.get(position);
            Intent intent=new Intent(getActivity(),MainActivity.class);
            intent.putExtra("sortId",entity.getId());
            startActivity(intent);
        };
        gridView.setOnItemClickListener(listener);
    }
    private void setAdapter(List<SortTag> dataEntities){
        gridView.setAdapter(new CommonAdapter<SortTag>(getActivity(),dataEntities,R.layout.sort_grid_item) {
            @Override
            public void convert(ViewHolder viewHolder, SortTag dataEntity) {
                viewHolder.setText(R.id.grid_tv, dataEntity.getName());
                viewHolder.setImageResourcewithFresco(R.id.grid_iv, Uri.parse(dataEntity.getBgPic()));
            }
        });
    }
}
