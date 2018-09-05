package com.benznestdeveloper.blognonestory;

import android.app.Application;
import android.content.Context;

/**
 * Created by benznest on 16-Mar-18.
 */

public class MyContextor {
    private static Context context;
    private static Application sApplication;

    private MyContextor() {

    }

    public static void setContext(Context context) {
        MyContextor.context = context;
    }

    public static Context getInstance() {
        return MyContextor.context;
    }

    public static void setApplication(Application sApplication) {
        MyContextor.sApplication = sApplication;
    }

    public static Application getApplication() {
        return MyContextor.sApplication;
    }

}
