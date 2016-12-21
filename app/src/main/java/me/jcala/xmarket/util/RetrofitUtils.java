package me.jcala.xmarket.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.EditText;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

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

    public static SSLSocketFactory getSSLSocketFactory(Context context, int[] certificates) {

        if (context == null) {
            throw new NullPointerException("context == null");
        }

        CertificateFactory certificateFactory;
        SSLContext sslContext = null;
        try {
            certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);

            for (int i = 0; i < certificates.length; i++) {
                InputStream certificate = context.getResources().openRawResource(certificates[i]);
                keyStore.setCertificateEntry(String.valueOf(i), certificateFactory.generateCertificate(certificate));

                if (certificate != null) {
                    certificate.close();
                }
            }
            sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (sslContext!=null){
            return sslContext.getSocketFactory();
        }else {
            return null;
        }
    }
    public static HostnameVerifier getHostnameVerifier(final String[] hostUrls) {

        return (String hostname, SSLSession session) ->{
                boolean ret = false;
                for (String host : hostUrls) {
                    if (host.equalsIgnoreCase(hostname)) {
                        ret = true;
                    }else if (getHostHome(host).equalsIgnoreCase(hostname)){
                        ret = true;
                    }
                }
                return ret;
        };
    }
    private static String getHostHome(String home){
        if (home.startsWith("https://")){
           home= home.substring(8);
        }
        if (home.startsWith("http://")){
            home= home.substring(7);
        }
        int index=home.indexOf(":");
        return home.substring(0,index);
    }
}
