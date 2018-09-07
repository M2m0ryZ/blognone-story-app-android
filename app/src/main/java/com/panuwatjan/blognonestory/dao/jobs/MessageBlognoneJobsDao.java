package com.panuwatjan.blognonestory.dao.jobs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by benznest on 28-Aug-18.
 */

public class MessageBlognoneJobsDao {
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("per_page")
    @Expose
    private Integer perPage;
    @SerializedName("jobs")
    @Expose
    private ArrayList<JobsDao> jobs = null;
    @SerializedName("jobs_count")
    @Expose
    private Integer jobsCount;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public ArrayList<JobsDao> getJobs() {
        return jobs;
    }

    public void setJobs(ArrayList<JobsDao> jobs) {
        this.jobs = jobs;
    }

    public Integer getJobsCount() {
        return jobsCount;
    }

    public void setJobsCount(Integer jobsCount) {
        this.jobsCount = jobsCount;
    }
}
