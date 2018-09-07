package com.panuwatjan.blognonestory.dao.jobs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by benznest on 06-Sep-18.
 */

public class RichProfileDao {
    @SerializedName("cover_2")
    @Expose
    private String cover2;
    @SerializedName("cover_3")
    @Expose
    private String cover3;
    @SerializedName("youtube")
    @Expose
    private String youtube;
    @SerializedName("story_block")
    @Expose
    private List<StoryBlockDao> storyBlock = null;
    @SerializedName("team_block")
    @Expose
    private List<TeamBlockDao> teamBlock = null;

    public String getCover2() {
        return cover2;
    }

    public void setCover2(String cover2) {
        this.cover2 = cover2;
    }

    public String getCover3() {
        return cover3;
    }

    public void setCover3(String cover3) {
        this.cover3 = cover3;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public List<StoryBlockDao> getStoryBlock() {
        return storyBlock;
    }

    public void setStoryBlock(List<StoryBlockDao> storyBlock) {
        this.storyBlock = storyBlock;
    }

    public List<TeamBlockDao> getTeamBlock() {
        return teamBlock;
    }

    public void setTeamBlock(List<TeamBlockDao> teamBlock) {
        this.teamBlock = teamBlock;
    }
}
