package com.panuwatjan.blognonestory.dao.jobs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by benznest on 04-Sep-18.
 */

public class MessageJobDetailDao {
    @SerializedName("api")
    @Expose
    private ApiDao api;
    @SerializedName("r")
    @Expose
    private ResourceDao r;
    @SerializedName("ga")
    @Expose
    private String ga;

    public ApiDao getApi() {
        return api;
    }

    public void setApi(ApiDao api) {
        this.api = api;
    }

    public ResourceDao getR() {
        return r;
    }

    public void setR(ResourceDao r) {
        this.r = r;
    }

    public String getGa() {
        return ga;
    }

    public void setGa(String ga) {
        this.ga = ga;
    }
}
