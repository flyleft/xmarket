package me.jcala.xmarket.mvp.sort;



import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jcala.xmarket.data.api.ReqExecutor;
import me.jcala.xmarket.data.entity.Result;
import me.jcala.xmarket.data.entity.SortTag;

public class SortTagModelImpl implements SortTagModel {
    @Override
    public void getSortTag(OnGetSortTagListener listener) {

    }
    private void getSortTag(){

    }
    private void excete(){
        ReqExecutor
                .INSTANCE()
                .allReq()
                .sortTag()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Result<List<SortTag>>>() {
                    @Override
                    public void accept(Result<List<SortTag>> listResult) throws Exception {

                    }
                });
    }

}
