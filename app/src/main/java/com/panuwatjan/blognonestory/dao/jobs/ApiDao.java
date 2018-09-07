package com.panuwatjan.blognonestory.dao.jobs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by benznest on 04-Sep-18.
 */

public class ApiDao {
    @SerializedName("endpoint")
    @Expose
    private String endpoint;
    @SerializedName("login")
    @Expose
    private String login;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
