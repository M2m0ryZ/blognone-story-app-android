package com.panuwatjan.blognonestory.dao.jobs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by benznest on 28-Aug-18.
 */

public class MessageBlognoneCompaniesDao {
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("per_page")
    @Expose
    private Integer perPage;
    @SerializedName("companies")
    @Expose
    private ArrayList<CompanyDao> companies = null;
    @SerializedName("count")
    @Expose
    private Integer count;

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

    public ArrayList<CompanyDao> getCompanies() {
        return companies;
    }

    public void setCompanies(ArrayList<CompanyDao> jobs) {
        this.companies = jobs;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
