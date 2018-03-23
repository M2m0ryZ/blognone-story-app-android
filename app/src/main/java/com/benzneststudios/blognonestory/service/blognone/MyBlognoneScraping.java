package com.benzneststudios.blognonestory.service.blognone;

import android.util.Log;

import com.benzneststudios.blognonestory.MyUtils;
import com.benzneststudios.blognonestory.dao.BlognoneNodeDao;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by benznest on 29-Sep-17.
 */

public class MyBlognoneScraping {

    public static ArrayList<BlognoneNodeDao> getNodeList(String html) {
        ArrayList<BlognoneNodeDao> listNode = new ArrayList<>();
        Document doc = Jsoup.parse(html);
        try {
            Element elementContainer = doc.select(".content-container").first();
            Elements elements = elementContainer.select("div[id^=node-]");
            MyUtils.log("elements=" + elements.size());
            for (int i = 0; i < elements.size(); i++) {
                BlognoneNodeDao node = new BlognoneNodeDao();
                Element e = elements.get(i);

                String strNodeId = e.attr("id").split("-")[1];
                int nodeId = Integer.parseInt(strNodeId);
                node.setId(nodeId);
                MyUtils.log("nodeId = " + nodeId);

                String writer = e.select("span.username").first().text();
                node.setWriter(writer);
                MyUtils.log( "writer = " + writer);

                Element nodeDate = e.select("span.submitted").first();
                nodeDate.select("span.username").remove();
                nodeDate.select("div.user_badges").remove();
                String strDate = nodeDate.text().replace("By", "").trim();
                node.setDate(strDate);
                MyUtils.log("strDate = " + strDate);

                boolean eSticky = e.attr("class").contains("sticky");
                if (eSticky) {
                    node.setSticky(true);
                    node.setWriter("sponsored");
                }

                boolean eWorkplace = e.attr("class").contains("workplace");
                if (eWorkplace) {
                    node.setWorkplace(true);
                }

                Elements elementTag = e.select("span.terms-label").select(".field-item");
                ArrayList<String> listTag = new ArrayList<>();
                for (Element et : elementTag) {
                    String str = et.text().replace("Tags", "")
                            .replace("Topics", "")
//                            .replace(":", "")
                            .replace("\u00A0", "")
                            .trim();

                    if(!str.isEmpty()){
                        listTag.add(str);
                    }
                }
                node.setTags(listTag);

                String title = e.select("h2>a").first().text();
                node.setTitle(title);
                MyUtils.log( "title = " + title);

                try {
                    String urlImage = e.select("div.node-image>img").first().attr("src");
                    node.setUrlImage(urlImage);
                    MyUtils.log( "urlImage = " + urlImage);
                } catch (Exception e1) {

                }


                String info = e.select("div.node-content").first().text();
                node.setInfo(info);
                MyUtils.log("info = " + info);

                int countComment = 0;
                try {
                    String strCountComment = e.select("li.comment-comments>a").first().text().split(" ")[0];

                    MyUtils.log("strCountComment = " + strCountComment);
                    countComment = Integer.parseInt(strCountComment);
                    node.setCountComment(countComment);
                } catch (Exception e2) {

                }
                MyUtils.log( "countComment = " + countComment);

                listNode.add(node);
            }
            return listNode;
        } catch (Exception e) {
            MyUtils.log("getNodeList ERROR ");
        }
        return null;
    }

    public static ArrayList<BlognoneNodeDao> getNodeForumList(String html) {
        ArrayList<BlognoneNodeDao> listNode = new ArrayList<>();
        Document doc = Jsoup.parse(html);
        try {
            Element elementContainer = doc.select("tbody").first();
            Elements elements = elementContainer.select("tr");
            for (int i = 0; i < elements.size(); i++) {
                BlognoneNodeDao node = new BlognoneNodeDao();
                Element e = elements.get(i);

                String[] arrNodeId = e.select("td.title>div>a").attr("href").split("/");
                String strNodeId = arrNodeId[arrNodeId.length - 1];
                int nodeId = Integer.parseInt(strNodeId);
                node.setId(nodeId);
                Log.d("BENZNEST LOG", "nodeId = " + nodeId);

                String writer = e.select("span.submitted>span.username").first().text();
                node.setWriter(writer);
                Log.d("BENZNEST LOG", "writer = " + writer);

                Element nodeDate = e.select("span.submitted").first();
                nodeDate.select("span.username").remove();
                String strDate = nodeDate.text().replace("By", "").trim();
                node.setDate(strDate);
                Log.d("BENZNEST LOG", "strDate = " + strDate);

                boolean eSticky = e.select("td.icon").first().html().contains("sticky");
                if (eSticky) {
                    node.setSticky(true);
                }

                String title = e.select("td.title>div>a").first().text();
                node.setTitle(title);
                Log.d("BENZNEST LOG", "title = " + title);

//                try {
//                    String urlImage = e.select("div.node-image>img").first().attr("src");
//                    node.setUrlImage(urlImage);
//                    Log.d("BENZNEST LOG", "urlImage = " + urlImage);
//                }catch (Exception e1){
//
//                }
//
//
//                String info = e.select("div.node-content").first().text();
//                node.setInfo(info);
//                Log.d("BENZNEST LOG", "info = " + info);
//
                int countComment = 0;
                try {
                    String strCountComment = e.select("td.replies").first().text().trim();

                    Log.d("BENZNEST LOG", "strCountComment = " + strCountComment);
                    countComment = Integer.parseInt(strCountComment);
                    node.setCountComment(countComment);
                } catch (Exception e2) {

                }
                Log.d("BENZNEST LOG", "countComment = " + countComment);

                listNode.add(node);
            }
            return listNode;
        } catch (Exception e) {
            Log.d("BENZNEST LOG", "getNodeList ERROR ");
        }
        return null;
    }


    public static BlognoneNodeDao getNodeContent(String html) {
        BlognoneNodeDao nodeContent = new BlognoneNodeDao();
        Document doc = Jsoup.parse(html);
        try {
            Element elementNode = doc.select("div[id^=node-]").first();

            String strNodeId = elementNode.attr("id").split("-")[1];
            int nodeId = Integer.parseInt(strNodeId);
            nodeContent.setId(nodeId);
            Log.d("BENZNEST LOG", "nodeId = " + nodeId);

            try {
                String urlImage = doc.select("div.node-image>img").first().attr("src");
                nodeContent.setUrlImage(urlImage);
                Log.d("BENZNEST LOG", "urlImage = " + urlImage);
            } catch (Exception e1) {

            }


            String bg = "white";
            boolean eSticky = elementNode.attr("class").contains("sticky");
            if (eSticky) {
                nodeContent.setSticky(true);
                nodeContent.setWriter("sponsored");
                bg = "#ffeed5";
            }

            boolean eWorkplace = elementNode.attr("class").contains("workplace");
            if (eWorkplace) {
                nodeContent.setWorkplace(true);
                bg = "#f0fbfb";
            }

            Elements elementTag = elementNode.select("span.terms-label").select(".field-item");
            ArrayList<String> listTag = new ArrayList<>();
            for (Element et : elementTag) {
                String str = et.text().replace("Tags", "")
                        .replace("Topics", "")
//                        .replace(":", "")
                        .replace("\u00A0", "")
                        .trim();

                if(!str.isEmpty()){
                    listTag.add(str);
                }
            }
            nodeContent.setTags(listTag);

            String title = elementNode.select("h2>a").first().text();
            nodeContent.setTitle(title);

            String writer = elementNode.select("span.username").first().text();
            nodeContent.setWriter(writer);

            Element nodeDate = elementNode.select("span.submitted").first();
            nodeDate.select("span.username").remove();
            nodeDate.select("div.user_badges").remove();
            String strDate = nodeDate.text().replace("By", "").trim();
            nodeContent.setDate(strDate);

            // get content
            Element elementContent = elementNode.select("div.content").first();
            elementContent.select("div.social-sharing").remove();
            String htmlBody = elementContent.html();

            htmlBody = "<div class=\"content\"  style=\"background:" + bg + "\">" + htmlBody + "</div>";
            nodeContent.setHtmlContent(htmlBody);
            nodeContent.setInfo(elementContent.text());

            Element elementCommentArea = doc.select("#comment-area").first();
            elementCommentArea.select("h2").remove();
            elementCommentArea.select("#comments").removeAttr("class");
            String htmlComment = elementCommentArea.html();
            nodeContent.setHtmlComment(htmlComment);

            int countComment = doc.select("div[id^=cid-]").size();
            nodeContent.setCountComment(countComment);
            Log.d("BENZNEST LOG", "countComment = " + countComment);

            return nodeContent;
        } catch (Exception e) {
            Log.d("BENZNEST LOG", "getNodeList ERROR ");
        }
        return null;
    }
}
