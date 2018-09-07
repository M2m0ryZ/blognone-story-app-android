package com.panuwatjan.blognonestory.service.blognone.web;

import android.os.Build;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by benznest on 17-Aug-16.
 */
public class MyBlognoneService {

    public static final int CONVERTER_GSON = 0;
    public static final int CONVERTER_SCALAR = 1;

    private static String BASE_URL = "https://www.blognone.com/";
    private static String BASE_URL_API_18 = "http://www.blognone.com/";
    private static MyBlognoneAPI service;
    private static String session = "";

    public static MyBlognoneAPI getService() {
        return getService(CONVERTER_GSON);
    }

    public static MyBlognoneAPI getService(int modeConverter) {
        Converter.Factory factory = null;
        if (modeConverter == CONVERTER_GSON) {
            factory = GsonConverterFactory.create();
        } else {
            factory = ScalarsConverterFactory.create();
        }

        return getService(factory);
    }

    public static MyBlognoneAPI getService(Converter.Factory factory) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
//                .addInterceptor(logging)
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        Request request = chain.request().newBuilder()
//                                .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36")
//                                .addHeader("X-Requested-With", "XMLHttpRequest")
//                                .build();
//                        return chain.proceed(request);
//                    }
//                })
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .client(okHttpClient)
                .addConverterFactory(factory)
                .build();

        service = retrofit.create(MyBlognoneAPI.class);

        return service;
    }

    public static String getBaseUrl() {
        if (Build.VERSION.SDK_INT >= 21) {
            return BASE_URL;
        } else {
            return BASE_URL_API_18;
        }
    }
}
