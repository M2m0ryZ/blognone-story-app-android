package com.benznestdeveloper.blognonestory.service.blognone.jobs;

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
public class MyBlognoneJobsService {

    public static final int MODE_BASE_URL_API = 0;
    public static final int MODE_BASE_URL_WEB = 1;
    private static int modeBaseUrl = MODE_BASE_URL_API;

    public static final int CONVERTER_GSON = 0;
    public static final int CONVERTER_SCALAR = 1;

    private static String BASE_URL_JOB = "https://jobs-api.blognone.com/";
    private static String BASE_URL_JOB_API_18 = "http://jobs-api.blognone.com/";

    private static String BASE_URL_JOB_WEB = "https://jobs.blognone.com";
    private static String BASE_URL_JOB_WEB_API_18 = "https://jobs.blognone.com";

    private static MyBlognoneJobsAPI service;
    private static String session = "";

    public static MyBlognoneJobsAPI getService() {
        return getService(CONVERTER_GSON);
    }

    public static MyBlognoneJobsAPI getService(int modeConverter) {
        Converter.Factory factory = null;
        if (modeConverter == CONVERTER_GSON) {
            factory = GsonConverterFactory.create();
        } else {
            factory = ScalarsConverterFactory.create();
        }

        return getService(factory);
    }

    public static MyBlognoneJobsAPI getService(int modeConverter, int modeBaseUrl) {
        Converter.Factory factory = null;
        MyBlognoneJobsService.modeBaseUrl = modeBaseUrl;
        if (modeConverter == CONVERTER_GSON) {
            factory = GsonConverterFactory.create();
        } else {
            factory = ScalarsConverterFactory.create();
        }

        return getService(factory);
    }

    public static MyBlognoneJobsAPI getService(Converter.Factory factory) {
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

        service = retrofit.create(MyBlognoneJobsAPI.class);

        return service;
    }

    public static String getBaseUrl() {
        if (modeBaseUrl == MODE_BASE_URL_API) {
            if (Build.VERSION.SDK_INT >= 21) {
                return BASE_URL_JOB;
            } else {
                return BASE_URL_JOB_API_18;
            }
        } else {
            if (Build.VERSION.SDK_INT >= 21) {
                return BASE_URL_JOB_WEB;
            } else {
                return BASE_URL_JOB_WEB_API_18;
            }
        }
    }
}
