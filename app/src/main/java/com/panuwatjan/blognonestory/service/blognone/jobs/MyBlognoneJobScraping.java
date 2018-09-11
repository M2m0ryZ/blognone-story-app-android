package com.panuwatjan.blognonestory.service.blognone.jobs;

import com.panuwatjan.blognonestory.MyUtils;
import com.panuwatjan.blognonestory.dao.jobs.MessageCompanyDetailDao;
import com.panuwatjan.blognonestory.dao.jobs.MessageJobDetailDao;
import com.google.gson.Gson;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Created by benznest on 04-Sep-18.
 */

public class MyBlognoneJobScraping {

    public static MessageJobDetailDao getJobBody(String html) {
        Document doc = Jsoup.parse(html);
        try {
            Element elementContainer = doc.select("#state").first();
            String json = elementContainer.html();
            Gson gson = new Gson();
            MessageJobDetailDao msg = gson.fromJson(json, MessageJobDetailDao.class);
            return msg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static MessageCompanyDetailDao getCompanyBody(String html) {
        Document doc = Jsoup.parse(html);
        try {
            Element elementContainer = doc.select("script#state").first();
            String json = elementContainer.html();
            MyUtils.log(json);
            Gson gson = new Gson();
            MessageCompanyDetailDao msg = gson.fromJson(json, MessageCompanyDetailDao.class);
            return msg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
