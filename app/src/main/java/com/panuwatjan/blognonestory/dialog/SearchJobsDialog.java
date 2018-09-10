package com.panuwatjan.blognonestory.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.panuwatjan.blognonestory.R;
import com.panuwatjan.blognonestory.search.job.SearchJobsDao;
import com.panuwatjan.blognonestory.view.MySearchJobView;

/**
 * Created by benznest on 29-Aug-18.
 */

public class SearchJobsDialog extends Dialog {

    private MySearchJobView searchJobsView;

    public SearchJobsDialog(Context context) {
        super(context);
        init();
    }

    public SearchJobsDialog(Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    public SearchJobsDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    public void setSearchJobs(SearchJobsDao searchJobs) {
        searchJobsView.setSearchJobs(searchJobs);
    }

    private void init() {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_search_jobs);
        this.setCancelable(true);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        initView();
    }


    private void initView() {
        searchJobsView = (MySearchJobView) findViewById(R.id.search_jobs_view);
    }


    public void setOnSearchFormListener(MySearchJobView.OnSearchFormListener onSearchFormListener) {
        searchJobsView.setOnSearchFormListener(onSearchFormListener);
    }
}
