package com.panuwatjan.blognonestory.service.blognone.jobs;

import com.panuwatjan.blognonestory.dao.jobs.MessageBlognoneCompaniesDao;
import com.panuwatjan.blognonestory.dao.jobs.MessageBlognoneJobsDao;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by benznest on 25/4/2559.
 */
public interface MyBlognoneJobsAPI {

    @GET("search")
    Call<MessageBlognoneJobsDao> getJobsList(
            @Query("page") long page,
            @Query("q") String keyword,
            @Query("type") String type,
            @Query("level") String level,
            @Query("func") String func);

    @GET("companies")
    Call<MessageBlognoneCompaniesDao> getCompaniesList(
            @Query("page") long page);


    @GET("company/{companySlug}/job/{jobSlug}")
    Call<String> getJobBody(
            @Path("companySlug") String companySlug,
            @Path("jobSlug") String jobSlug);

    @GET("company/{companySlug}")
    Call<String> getCompanyBody(
            @Path("companySlug") String companySlug);
}
