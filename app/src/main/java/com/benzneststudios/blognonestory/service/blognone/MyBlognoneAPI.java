package com.benzneststudios.blognonestory.service.blognone;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by benznest on 25/4/2559.
 */
public interface MyBlognoneAPI {

    @GET("node")
    Call<String> getNodeList(@Query("page") long page);

    @GET("feature")
    Call<String> getNodeFeature();

    @GET("topics/interview")
    Call<String> getNodeInterview(@Query("page") long page);

    @GET("upcoming")
    Call<String> getNodeUpcoming();

    @GET("topics/{tag}")
    Call<String> getNodeTag(@Path("tag") String tag, @Query("page") long page);


    @GET("node/{id}")
    Call<String> getNodeContent(@Path("id") int id);

    @GET("topics/blognone-workplace")
    Call<String> getNodeWorkplace();


    @GET("forums/{endPoint}")
    Call<String> getNodeForum(@Path("endPoint") String endPoint, @Query("page") long page);

}
