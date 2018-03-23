package com.benzneststudios.blognonestory.dao;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by benznest on 18-Mar-18.
 */

public class BlognoneForumDao implements Parcelable {
    private String title;
    private String endpoint;

    public BlognoneForumDao(String title, String endpoint) {
        this.title = title;
        this.endpoint = endpoint;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.endpoint);
    }

    protected BlognoneForumDao(Parcel in) {
        this.title = in.readString();
        this.endpoint = in.readString();
    }

    public static final Parcelable.Creator<BlognoneForumDao> CREATOR = new Parcelable.Creator<BlognoneForumDao>() {
        @Override
        public BlognoneForumDao createFromParcel(Parcel source) {
            return new BlognoneForumDao(source);
        }

        @Override
        public BlognoneForumDao[] newArray(int size) {
            return new BlognoneForumDao[size];
        }
    };
}
