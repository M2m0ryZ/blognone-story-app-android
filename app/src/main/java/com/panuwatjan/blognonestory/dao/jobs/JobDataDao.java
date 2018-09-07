package com.panuwatjan.blognonestory.dao.jobs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by benznest on 04-Sep-18.
 */

public class JobDataDao {
    @SerializedName("job")
    @Expose
    private JobsDao job;
    @SerializedName("other_jobs")
    @Expose
    private List<JobsDao> otherJobs = null;
    @SerializedName("other_jobs_count")
    @Expose
    private Integer otherJobsCount;

    public JobsDao getJob() {
        return job;
    }

    public void setJob(JobsDao job) {
        this.job = job;
    }

    public List<JobsDao> getOtherJobs() {
        return otherJobs;
    }

    public void setOtherJobs(List<JobsDao> otherJobs) {
        this.otherJobs = otherJobs;
    }

    public Integer getOtherJobsCount() {
        return otherJobsCount;
    }

    public void setOtherJobsCount(Integer otherJobsCount) {
        this.otherJobsCount = otherJobsCount;
    }
}
