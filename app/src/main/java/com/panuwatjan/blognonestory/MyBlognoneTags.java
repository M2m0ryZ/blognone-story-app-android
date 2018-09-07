package com.panuwatjan.blognonestory;

import com.panuwatjan.blognonestory.service.blognone.web.MyBlognoneManager;

import java.util.ArrayList;

/**
 * Created by benznest on 18-Mar-18.
 */

public class MyBlognoneTags {
    private static String BASE_URL_ICON = "http://benzneststudios.com/blognonestory/app/img/";
    private static ArrayList<BlognoneTags> listTag;

    public static BlognoneTags create(String name, String icon) {
        return create(name, icon, false);
    }

    public static BlognoneTags create(String name, String icon, boolean favorite) {
        BlognoneTags tag = new BlognoneTags();
        tag.setName(name);
        tag.setIcon(BASE_URL_ICON + "/" + icon);
        tag.setEndpoint(MyBlognoneManager.convertToEndPoint(name));
        tag.setFavorite(favorite);
        return tag;
    }

    public static ArrayList<BlognoneTags> getTagDefault() {
        listTag = new ArrayList<>();
        // Company
        listTag.add(create("Apple", "apple.png", true));
        listTag.add(create("Google", "google.png", true));
        listTag.add(create("Microsoft", "microsoft.png", true));
        listTag.add(create("Samsung", "samsung.jpg", true));
        listTag.add(create("Intel", "intel.png"));
        listTag.add(create("Nokia", "nokia.png"));
        listTag.add(create("IBM", "ibm.png"));

        // Os
        listTag.add(create("iOS", "ios.png", true));
        listTag.add(create("Android", "android.png", true));
        listTag.add(create("Windows", "windows.png"));

        // App
        listTag.add(create("LINE", "line.png"));
        listTag.add(create("Facebook", "facebook.png"));
        listTag.add(create("Instagram", "instragram.png"));
        listTag.add(create("Wordpress", "wordpress.png"));
        listTag.add(create("Twitter", "twitter.png"));
        listTag.add(create("Uber", "uber.png"));
        listTag.add(create("Youtube", "youtube.png"));
        listTag.add(create("Java", "java.png"));
        listTag.add(create("Netflix", "netflix.png"));
        listTag.add(create("Visual Studio", "visual-studio.png"));

        //
//        listTag.add(create("Machine Learning"));
        listTag.add(create("Artificial Intelligence", "ai.png"));
//        listTag.add(create("Deep Learning"));
//        listTag.add(create("Facial Recognition"));
//        listTag.add(create("Augmented Reality","vr.png"));
        listTag.add(create("Virtual Reality", "vr.png"));

//        listTag.add(create("Cryptocurrency"));
//        listTag.add(create("Blockchain"));
        listTag.add(create("Bitcoin", "bitcoin.png"));
        listTag.add(create("Thailand", "thailand.png"));

        listTag.add(create("ICO", "ico.png"));
        listTag.add(create("China", "china.png"));
        return listTag;
    }
}
