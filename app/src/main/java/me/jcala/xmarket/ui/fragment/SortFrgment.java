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
import me.jcala.xmarket.data.entity.ordinary.SortTag;
import me.jcala.xmarket.ui.activity.MainActivity;
import me.jcala.xmarket.ui.base.BaseFragment;
import me.jcala.xmarket.ui.base.CommonAdapter;
import me.jcala.xmarket.util.ViewHolder;

public class SortFrgment extends BaseFragment {
    private String jsonStr="[\n" +
            "  {\n" +
            "    \"id\": \"26\",\n" +
            "    \"name\": \"学习资料\",\n" +
            "    \"bgPic\": \"https://jcalaz.github.io/img/sort_book.jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"28\",\n" +
            "    \"name\": \"生活用品\",\n" +
            "    \"bgPic\": \"https://jcalaz.github.io/img/sort_life.jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"34\",\n" +
            "    \"name\": \"衣物鞋帽\",\n" +
            "    \"bgPic\": \"https://jcalaz.github.io/img/sort_clothes.jpeg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"36\",\n" +
            "    \"name\": \"运动健身\",\n" +
            "    \"bgPic\": \"https://jcalaz.github.io/img/sort_body.jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"30\",\n" +
            "    \"name\": \"手机数码\",\n" +
            "    \"bgPic\": \"https://jcalaz.github.io/img/sort_phone.jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"32\",\n" +
            "    \"name\": \"电脑办公\",\n" +
            "    \"bgPic\": \"https://jcalaz.github.io/img/sort_computer.jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"38\",\n" +
            "    \"name\": \"电器\",\n" +
            "    \"bgPic\": \"https://jcalaz.github.io/img/sort_elec.jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"4\",\n" +
            "    \"name\": \"数码配件\",\n" +
            "    \"bgPic\": \"https://jcalaz.github.io/img/sort_parts.jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"8\",\n" +
            "    \"name\": \"租赁\",\n" +
            "    \"bgPic\": \"https://jcalaz.github.io/img/sort_rent.jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"14\",\n" +
            "    \"name\": \"其他\",\n" +
            "    \"bgPic\": \"https://jcalaz.github.io/img/sort_other.jpg\"\n" +
            "  }" +
            "]";
    private GridView gridView;
    private List<SortTag> dataEntities=new ArrayList<>();
    @Override
    protected int getLayoutResId() {
        return R.layout.sort_fragment;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        gridView=(GridView)view.findViewById(R.id.sort_grid);
            parseJson(jsonStr);
            setAdapter(dataEntities);
            setListener();
    }

    @Override
    protected void loadData() {

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
    private void parseJson(String jsonData){
        List<SortTag> entities = new Gson().fromJson(jsonStr, new TypeToken<List<SortTag>>(){}.getType());
        dataEntities.addAll(entities);
    }
}
