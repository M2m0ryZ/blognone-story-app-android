package com.benznestdeveloper.blognonestory.service.blognone.jobs;

import com.benznestdeveloper.blognonestory.dao.jobs.MessageJobDetailDao;
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
}
