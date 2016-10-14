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
            "    \"name\": \"手机数码\",\n" +
            "    \"bgPic\": \"http://img.kaiyanapp.com/ac6971c1b9fc942c7547d25fc6c60ad2.jpeg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"28\",\n" +
            "    \"name\": \"电脑\",\n" +
            "    \"bgPic\": \"http://img.kaiyanapp.com/cd74ae49d45ab6999bcd55dbae6d550f.jpeg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"30\",\n" +
            "    \"name\": \"书籍\",\n" +
            "    \"bgPic\": \"http://img.kaiyanapp.com/2b7ac9d21ca06df7e39e80a3799a3fb6.jpeg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"32\",\n" +
            "    \"name\": \"裤子\",\n" +
            "    \"bgPic\": \"http://img.kaiyanapp.com/0117b9108c7cff43700db8af5e24f2bf.jpeg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"34\",\n" +
            "    \"name\": \"鞋\",\n" +
            "    \"bgPic\": \"http://img.kaiyanapp.com/d7186edff72b6a6ddd03eff166ee4cd8.jpeg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"36\",\n" +
            "    \"name\": \"被子\",\n" +
            "    \"bgPic\": \"http://img.kaiyanapp.com/924ebc6780d59925c8a346a5dafc90bb.jpeg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"38\",\n" +
            "    \"name\": \"杯子\",\n" +
            "    \"bgPic\": \"http://img.kaiyanapp.com/9f4c1559d54d4274e5463fba4262b284.jpeg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"4\",\n" +
            "    \"name\": \"本子\",\n" +
            "    \"bgPic\": \"http://img.kaiyanapp.com/5817f1bfdce61130204a24268160b619.jpeg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"8\",\n" +
            "    \"name\": \"JAVA\",\n" +
            "    \"bgPic\": \"http://img.kaiyanapp.com/003829087e85ce7310b2210d9575ce67.jpeg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"14\",\n" +
            "    \"name\": \"scala\",\n" +
            "    \"bgPic\": \"http://img.kaiyanapp.com/98beab66d3885a139b54f21e91817c4f.jpeg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"22\",\n" +
            "    \"name\": \"goLang\",\n" +
            "    \"bgPic\": \"http://img.kaiyanapp.com/a2fc6d32ac0b4f2842fb3d545d06f09b.jpeg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"24\",\n" +
            "    \"name\": \"oracle\",\n" +
            "    \"bgPic\": \"http://img.kaiyanapp.com/22192a40de238fe853b992ed57f1f098.jpeg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"sd\",\n" +
            "    \"name\": \"mysql\",\n" +
            "    \"bgPic\": \"http://img.kaiyanapp.com/fd56db2b929132b883e9981ab843dfca.jpeg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"23\",\n" +
            "    \"name\": \"mongo\",\n" +
            "    \"bgPic\": \"http://img.kaiyanapp.com/c746d56db089909b1cdd933fa7c98ef8.jpeg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"sdsd\",\n" +
            "    \"name\": \"日常用品\",\n" +
            "    \"bgPic\": \"http://img.kaiyanapp.com/3d874b72aaad089836f3cc4a25b64bb5.jpeg\"\n" +
            "  }\n" +
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
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SortTag entity = dataEntities.get(position);
                Intent intent=new Intent(getActivity(),MainActivity.class);
                intent.putExtra("name",entity.getName());
                startActivity(intent);
            }
        });
    }
    //设置适配器
    private void setAdapter(List<SortTag> dataEntities){
        gridView.setAdapter(new CommonAdapter<SortTag>(getActivity(),dataEntities,R.layout.sort_grid_item) {
            @Override
            public void convert(ViewHolder viewHolder, SortTag dataEntity) {
                viewHolder.setText(R.id.grid_tv, dataEntity.getName());
                viewHolder.setImageResourcewithFresco(R.id.grid_iv, Uri.parse(dataEntity.getBgPic()));
            }
        });
    }
    //解析json数据
    private void parseJson(String jsonData){
        List<SortTag> entities = new Gson().fromJson(jsonStr, new TypeToken<List<SortTag>>(){}.getType());
        dataEntities.addAll(entities);
    }
}
