package com.benznestdeveloper.blognonestory.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.benznestdeveloper.blognonestory.R;
import com.benznestdeveloper.blognonestory.search.job.JobsFilterDao;
import com.github.angads25.toggle.LabeledSwitch;

/**
 * Created by benznest on 16-Mar-18.
 */

public class MyRowSwitchSearchView extends FrameLayout {

    private JobsFilterDao mJobsFilterDao;
    private LabeledSwitch switchFilter;
    private TextView tvTitle;

    public MyRowSwitchSearchView(@NonNull Context context) {
        super(context);
        init();
    }

    public MyRowSwitchSearchView(@NonNull Context context, JobsFilterDao mJobsFilterDao) {
        super(context);
        init();
        this.mJobsFilterDao = mJobsFilterDao;
        setFilterText(mJobsFilterDao.getTitle());
    }

    public MyRowSwitchSearchView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyRowSwitchSearchView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyRowSwitchSearchView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.row_switch_search, this);
        initView();
    }

    private void initView() {
        switchFilter = (LabeledSwitch) findViewById(R.id.switch_filter);
        tvTitle = (TextView) findViewById(R.id.tv_title);
    }

    public void setJobsFilterDao(JobsFilterDao jobsFilterDao) {
        mJobsFilterDao = jobsFilterDao;
    }

    private void setFilterText(String title) {
        tvTitle.setText(title);
    }

    public JobsFilterDao getJobsFilterDao() {
        return mJobsFilterDao;
    }

    public boolean isJobFilterChecked() {
        return switchFilter.isOn();
    }

    public void setJobFilterChecked(boolean checked) {
        switchFilter.setOn(checked);
    }

}
