package com.panuwatjan.blognonestory.dao;

/**
 * Created by benznest on 18-Mar-18.
 */

public class TagsDao {
    private String name;
    private int icon;

    public TagsDao(String name) {
        this.name = name;
    }

    public TagsDao(String name, int icon) {
        this.name = name;
        this.icon = icon;
    }
}
