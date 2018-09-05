package com.benznestdeveloper.blognonestory.search.job;

/**
 * Created by benznest on 29-Aug-18.
 */

public class JobsFilterDao {
    private String title;
    private String value;

    public JobsFilterDao(String title, String value) {
        this.title = title;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
