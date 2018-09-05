package com.benznestdeveloper.blognonestory.dao.jobs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by benznest on 04-Sep-18.
 */

public class ResourceDao {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("meta")
    @Expose
    private MetaDao meta;
    @SerializedName("data")
    @Expose
    private JobDataDao data;

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

    public MetaDao getMeta() {
        return meta;
    }

    public void setMeta(MetaDao meta) {
        this.meta = meta;
    }

    public JobDataDao getData() {
        return data;
    }

    public void setData(JobDataDao data) {
        this.data = data;
    }
}
