package com.panuwatjan.blognonestory;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table BLOGNONE_FAVORITE_JOBS.
 */
public class BlognoneFavoriteJobs {

    private Long id;
    private String data;
    private Long datetime;

    public BlognoneFavoriteJobs() {
    }

    public BlognoneFavoriteJobs(Long id) {
        this.id = id;
    }

    public BlognoneFavoriteJobs(Long id, String data, Long datetime) {
        this.id = id;
        this.data = data;
        this.datetime = datetime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getDatetime() {
        return datetime;
    }

    public void setDatetime(Long datetime) {
        this.datetime = datetime;
    }

}
