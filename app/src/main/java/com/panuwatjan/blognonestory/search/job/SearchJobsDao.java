package com.panuwatjan.blognonestory.search.job;

import java.util.ArrayList;

/**
 * Created by benznest on 29-Aug-18.
 */

public class SearchJobsDao {
    private String keyword = "";
    private ArrayList<JobsFilterDao> jobType = new ArrayList<>();
    private ArrayList<JobsFilterDao> jobLevel = new ArrayList<>();
    private ArrayList<JobsFilterDao> jobFunction = new ArrayList<>();

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public ArrayList<JobsFilterDao> getJobType() {
        return jobType;
    }

    public void setJobType(ArrayList<JobsFilterDao> jobType) {
        this.jobType = jobType;
    }

    public ArrayList<JobsFilterDao> getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(ArrayList<JobsFilterDao> jobLevel) {
        this.jobLevel = jobLevel;
    }

    public ArrayList<JobsFilterDao> getJobFunction() {
        return jobFunction;
    }

    public void setJobFunction(ArrayList<JobsFilterDao> jobFunction) {
        this.jobFunction = jobFunction;
    }

    public boolean contains(ArrayList<JobsFilterDao> list, JobsFilterDao filter) {
        if (list != null) {
            for (JobsFilterDao j : list) {
                if (j.getValue().equals(filter.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean constainInJobType(JobsFilterDao filter) {
        return contains(jobType, filter);
    }

    public boolean constainInJobLevel(JobsFilterDao filter) {
        return contains(jobLevel, filter);
    }

    public boolean constainInJobFunction(JobsFilterDao filter) {
        return contains(jobFunction, filter);
    }
}
