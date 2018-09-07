package com.panuwatjan.blognonestory.dao.jobs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by benznest on 04-Sep-18.
 */

public class ResourceCompanyDao {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("path")
    @Expose
    private String path;

    @SerializedName("data")
    @Expose
    private List<CompanyDataDao> data = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<CompanyDataDao> getData() {
        return data;
    }

    public void setData(List<CompanyDataDao> data) {
        this.data = data;
    }
}
