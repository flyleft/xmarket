package me.jcala.xmarket.util;


import android.content.Context;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import id.zelory.compressor.Compressor;
import me.jcala.xmarket.data.pojo.Trade;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FileUtils {

    public interface CompressListener{

        void compressMultiSuccess(List<File> files,Trade trade);
        void compressMultiFail();

    }
    /**
     * 使用RxJava异步批量压缩文件
     * 除去最后一张系统添加图片的图标
     */
    public static void compressMultiFilesExceptLast(Context context, CompressListener listener,
                                                    LinkedList<String> picUrls, Trade trade){
        final List<File> files=new ArrayList<>();
        Observable
                .from(picUrls)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        listener.compressMultiSuccess(files,trade);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.w("压缩图片时出错"+e.getLocalizedMessage());
                        listener.compressMultiFail();
                    }

                    @Override
                    public void onNext(String imgUrls) {
                        File oldFile=new File(imgUrls);
                        if (oldFile.exists()){
                            File newFile=Compressor
                                            .getDefault(context)
                                            .compressToFile(oldFile);
                            files.add(newFile);
                        }
                    }
                });

    }
}