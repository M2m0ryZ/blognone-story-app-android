package com.benznestdeveloper.blognonestory.search.job;

import com.benznestdeveloper.blognonestory.view.MyRowSwitchSearchView;

import java.util.ArrayList;

/**
 * Created by benznest on 29-Aug-18.
 */

public class MyJobsFilterManager {
    public static ArrayList<JobsFilterDao> getJobFilterChecked(ArrayList<MyRowSwitchSearchView> list) {
        ArrayList<JobsFilterDao> listChecked = new ArrayList<>();
        for (MyRowSwitchSearchView f : list) {
            if (f.isJobFilterChecked()) {
                listChecked.add(f.getJobsFilterDao());
            }
        }
        return listChecked;
    }
}
