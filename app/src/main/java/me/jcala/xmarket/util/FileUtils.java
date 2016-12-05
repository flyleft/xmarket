package me.jcala.xmarket.util;


import android.content.Context;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import id.zelory.compressor.Compressor;
import me.jcala.xmarket.data.dto.Result;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FileUtils {

    /**
     * 使用RxJava异步批量压缩文件
     * 除去最后一张系统添加图片的图标
     */
    public static List<File> compressMultiFilesExceptLast(Context context,LinkedList<String> picUrls){
        Logger.e("传入压缩方法的图片url数量:"+picUrls.size());
        final List<File> files=new ArrayList<>();

        for (int i=0;i< picUrls.size();i++){

            Logger.e(""+picUrls.get(i));
               File img=new File(picUrls.get(i));

            if (!img.exists()){
                continue;
            }

            Logger.e("j:"+picUrls.get(i));
                Compressor.getDefault(context)
                        .compressToFileAsObservable(img)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<File>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(File file) {

                            }
                        });
        }
        return files;
    }


}
