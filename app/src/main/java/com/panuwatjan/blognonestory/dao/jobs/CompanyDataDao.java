package com.panuwatjan.blognonestory.dao.jobs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by benznest on 06-Sep-18.
 */

public class CompanyDataDao {
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("name_en")
    @Expose
    private String nameEn;
    @SerializedName("name_th")
    @Expose
    private String nameTh;
    @SerializedName("industry")
    @Expose
    private String industry;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("province")
    @Expose
    private String province;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("facebook")
    @Expose
    private String facebook;
    @SerializedName("twitter")
    @Expose
    private Object twitter;
    @SerializedName("linkedin")
    @Expose
    private Object linkedin;
    @SerializedName("youtube")
    @Expose
    private String youtube;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("verified")
    @Expose
    private Boolean verified;
    @SerializedName("cover")
    @Expose
    private String cover;
    @SerializedName("rich_profile")
    @Expose
    private RichProfileDao richProfile;
    @SerializedName("premium")
    @Expose
    private Boolean premium;
    @SerializedName("publish")
    @Expose
    private Boolean publish;
    @SerializedName("brief")
    @Expose
    private String brief;
    @SerializedName("long_description")
    @Expose
    private String longDescription;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("per_page")
    @Expose
    private Integer perPage;
    @SerializedName("jobs")
    @Expose
    private List<JobsDao> jobs = null;
    @SerializedName("jobs_count")
    @Expose
    private Integer jobsCount;

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameTh() {
        return nameTh;
    }

    public void setNameTh(String nameTh) {
        this.nameTh = nameTh;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public Object getTwitter() {
        return twitter;
    }

    public void setTwitter(Object twitter) {
        this.twitter = twitter;
    }

    public Object getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(Object linkedin) {
        this.linkedin = linkedin;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public RichProfileDao getRichProfile() {
        return richProfile;
    }

    public void setRichProfile(RichProfileDao richProfile) {
        this.richProfile = richProfile;
    }

    public Boolean getPremium() {
        return premium;
    }

    public void setPremium(Boolean premium) {
        this.premium = premium;
    }

    public Boolean getPublish() {
        return publish;
    }

    public void setPublish(Boolean publish) {
        this.publish = publish;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public List<JobsDao> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobsDao> jobs) {
        this.jobs = jobs;
    }

    public Integer getJobsCount() {
        return jobsCount;
    }

    public void setJobsCount(Integer jobsCount) {
        this.jobsCount = jobsCount;
    }

    public CompanyDao convertToCompanyDao() {
        CompanyDao c = new CompanyDao();
        c.setNameEn(nameEn);
        c.setLogo(logo);
        c.setSlug(slug);
        c.setAddress(address);
        return c;
    }
}
