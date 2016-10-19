package me.jcala.xmarket.mvp.school;

import java.util.ArrayList;
import java.util.List;
import me.jcala.xmarket.conf.AppConf;
import me.jcala.xmarket.data.entity.DealItem;
import me.jcala.xmarket.data.entity.UserBean;

public class SchoolModelImpl implements SchoolModel{

    @Override
    public void getSchoolDeals(int page,final onGainListener listener) {
        if (AppConf.reqExcute==AppConf.reqExcuteNormal){
            execute(listener);
        }else {
            executeLocal(listener);
        }
    }

    private void executeLocal(final onGainListener listener){
        List<DealItem> itemList=new ArrayList<>();
        UserBean bean=new UserBean();
        bean.setAvatar_url("https://jcalaz.github.io/img/sort_book.jpg");
        bean.setUsername("jcala");
        DealItem item=new DealItem();
        item.setAuthor(bean);
        item.setPrice(999);
        item.setTitle("JAVA编程思想");
        item.setDescription("马勒戈壁的");
        List<String> imags=new ArrayList<>();
        imags.add("https://jcalaz.github.io/img/sort_life.jpg");
        item.setImags(imags);
        itemList.add(item);
        listener.success(itemList);
    }


    private void execute(final onGainListener listener){

    }
}
