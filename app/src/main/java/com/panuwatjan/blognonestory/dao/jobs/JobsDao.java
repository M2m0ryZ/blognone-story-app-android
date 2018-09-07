package com.panuwatjan.blognonestory.dao.jobs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by benznest on 28-Aug-18.
 */

public class JobsDao {
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("company")
    @Expose
    private CompanyDao company;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("province")
    @Expose
    private String province;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("salary_min")
    @Expose
    private String salaryMin;
    @SerializedName("salary_max")
    @Expose
    private String salaryMax;
    @SerializedName("skills")
    @Expose
    private ArrayList<String> skills = null;
    @SerializedName("premium")
    @Expose
    private Boolean premium;
    @SerializedName("updated")
    @Expose
    private String updated;
    @SerializedName("description")
    @Expose
    private String description;

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public CompanyDao getCompany() {
        return company;
    }

    public void setCompany(CompanyDao company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(String salaryMin) {
        this.salaryMin = salaryMin;
    }

    public String getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(String salaryMax) {
        this.salaryMax = salaryMax;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }

    public Boolean getPremium() {
        return premium;
    }

    public void setPremium(Boolean premium) {
        this.premium = premium;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
