package com.benzneststudios.blognonestory;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table BLOGNONE_HISTORY_TOPIC.
 */
public class BlognoneHistoryTopic {

    private Long id;
    private Long nodeId;
    private String data;
    private Long datetime;
    private Integer countComment;

    public BlognoneHistoryTopic() {
    }

    public BlognoneHistoryTopic(Long id) {
        this.id = id;
    }

    public BlognoneHistoryTopic(Long id, Long nodeId, String data, Long datetime, Integer countComment) {
        this.id = id;
        this.nodeId = nodeId;
        this.data = data;
        this.datetime = datetime;
        this.countComment = countComment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
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

    public Integer getCountComment() {
        return countComment;
    }

    public void setCountComment(Integer countComment) {
        this.countComment = countComment;
    }

}
