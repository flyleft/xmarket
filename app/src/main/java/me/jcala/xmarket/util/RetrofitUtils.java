package me.jcala.xmarket.util;

import android.support.annotation.NonNull;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RetrofitUtils {


    /**
     * 根据多个文件生成retrofit多文件上传所需的MultipartBody.Part列表
     */
    public static List<MultipartBody.Part> filesToMultipartBodyParts(List<String> files) {
        List<MultipartBody.Part> parts = new ArrayList<>(files.size());
        for (String fileName : files) {
            File file=new File(fileName);
            if (!file.exists()){
                continue;
            }
            // TODO: 16-11-30  没有判断file的类型
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
            parts.add(part);
        }
        return parts;
    }

    @NonNull
    public static RequestBody createPartFromString(String data) {
        return RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"), data);
    }

}
