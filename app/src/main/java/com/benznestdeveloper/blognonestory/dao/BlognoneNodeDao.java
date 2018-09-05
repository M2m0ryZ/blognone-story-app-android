package com.benznestdeveloper.blognonestory.dao;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by benznest on 16-Mar-18.
 */

public class BlognoneNodeDao implements Parcelable {
    private int id;
    private String title;
    private String info;
    private String writer;
    private String date;
    private String urlImage;
    private boolean sticky;
    private boolean workplace;
    private ArrayList<String> tags = new ArrayList<>();
    private int countComment;

    private String htmlContent;
    private String htmlComment;

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSticky() {
        return sticky;
    }

    public void setSticky(boolean sticky) {
        this.sticky = sticky;
    }

    public boolean isWorkplace() {
        return workplace;
    }

    public void setWorkplace(boolean workplace) {
        this.workplace = workplace;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public int getCountComment() {
        return countComment;
    }

    public void setCountComment(int countComment) {
        this.countComment = countComment;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public String getHtmlComment() {
        return htmlComment;
    }

    public void setHtmlComment(String htmlComment) {
        this.htmlComment = htmlComment;
    }

    public BlognoneNodeDao() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.info);
        dest.writeString(this.writer);
        dest.writeString(this.date);
        dest.writeString(this.urlImage);
        dest.writeByte(this.sticky ? (byte) 1 : (byte) 0);
        dest.writeByte(this.workplace ? (byte) 1 : (byte) 0);
        dest.writeStringList(this.tags);
        dest.writeInt(this.countComment);
        dest.writeString(this.htmlContent);
        dest.writeString(this.htmlComment);
    }

    protected BlognoneNodeDao(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.info = in.readString();
        this.writer = in.readString();
        this.date = in.readString();
        this.urlImage = in.readString();
        this.sticky = in.readByte() != 0;
        this.workplace = in.readByte() != 0;
        this.tags = in.createStringArrayList();
        this.countComment = in.readInt();
        this.htmlContent = in.readString();
        this.htmlComment = in.readString();
    }

    public static final Creator<BlognoneNodeDao> CREATOR = new Creator<BlognoneNodeDao>() {
        @Override
        public BlognoneNodeDao createFromParcel(Parcel source) {
            return new BlognoneNodeDao(source);
        }

        @Override
        public BlognoneNodeDao[] newArray(int size) {
            return new BlognoneNodeDao[size];
        }
    };
}
