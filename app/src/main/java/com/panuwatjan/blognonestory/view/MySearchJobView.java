package com.panuwatjan.blognonestory.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.flexbox.FlexboxLayout;
import com.panuwatjan.blognonestory.R;
import com.panuwatjan.blognonestory.search.job.JobsFilterDao;
import com.panuwatjan.blognonestory.search.job.MyJobsFilterData;
import com.panuwatjan.blognonestory.search.job.MyJobsFilterManager;
import com.panuwatjan.blognonestory.search.job.SearchJobsDao;

import java.util.ArrayList;

/**
 * Created by benznest on 10-Sep-18.
 */

public class MySearchJobView extends FrameLayout {
    private final int UPDATE_ALL = 0;
    private final int UPDATE_JOB_TYPE = 1;
    private final int UPDATE_JOB_LEVEL = 2;
    private final int UPDATE_JOB_FUNCTION = 3;


    private ArrayList<MyRowSwitchSearchView> listFilterJobTypeView;
    private ArrayList<MyRowSwitchSearchView> listFilterJobLevelView;
    private ArrayList<MyRowSwitchSearchView> listFilterJobFunctionView;

    private LinearLayout llJobTypeContainer;
    private LinearLayout llJobLevelContainer;
    private LinearLayout llJobFunctionContainer;

    private EditText edtSearch;
    private LinearLayout llTypeHeaderContainer;
    private ImageView imgArrowType;
    private LinearLayout llJobLevelHeaderContainer;
    private ImageView imgArrowJobLevel;
    private LinearLayout llJobFunctionHeaderContainer;
    private ImageView imgArrowJobFunction;
    private Button btnSearch;

    private boolean enableJobType;
    private boolean enableJobLevel;
    private boolean enableJobFunction;

    private OnSearchFormListener mOnSearchFormListener;

    private FlexboxLayout llJobTypeTagContainer;
    private FlexboxLayout llJobLevelTagContainer;
    private FlexboxLayout llJobFunctionTagContainer;
    private SearchJobsDao searchJobs = new SearchJobsDao();


    public MySearchJobView(@NonNull Context context) {
        super(context);
        init();
    }

    public MySearchJobView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MySearchJobView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MySearchJobView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.search_jobs_view, this);
        initView();
        initData();
    }

    public void setSearchJobs(SearchJobsDao searchJobs) {
        this.searchJobs = searchJobs;
        initData();
    }

    private void initData() {

        edtSearch.setText(searchJobs.getKeyword());

        listFilterJobTypeView = new ArrayList<>();
        listFilterJobLevelView = new ArrayList<>();
        listFilterJobFunctionView = new ArrayList<>();

        // job type
        llJobTypeContainer.removeAllViews();
        for (JobsFilterDao filter : MyJobsFilterData.getListFilterJobType()) {
            MyRowSwitchSearchView view = new MyRowSwitchSearchView(getContext(), filter);
            view.setJobFilterChecked(searchJobs.constainInJobType(filter));
            listFilterJobTypeView.add(view);
            llJobTypeContainer.addView(view);
        }

        // level
        llJobLevelContainer.removeAllViews();
        for (JobsFilterDao filter : MyJobsFilterData.getListFilterJobLevel()) {
            MyRowSwitchSearchView view = new MyRowSwitchSearchView(getContext(), filter);
            view.setJobFilterChecked(searchJobs.constainInJobLevel(filter));
            listFilterJobLevelView.add(view);
            llJobLevelContainer.addView(view);
        }

        // job func
        llJobFunctionContainer.removeAllViews();
        for (JobsFilterDao filter : MyJobsFilterData.getListFilterJobFunction()) {
            MyRowSwitchSearchView view = new MyRowSwitchSearchView(getContext(), filter);
            view.setJobFilterChecked(searchJobs.constainInJobFunction(filter));
            listFilterJobFunctionView.add(view);
            llJobFunctionContainer.addView(view);
        }
    }

    private void initView() {
        llJobTypeContainer = (LinearLayout) findViewById(R.id.ll_job_type_container);
        llJobLevelContainer = (LinearLayout) findViewById(R.id.ll_job_level_container);
        llJobFunctionContainer = (LinearLayout) findViewById(R.id.ll_job_function_container);

        edtSearch = (EditText) findViewById(R.id.edt_search);

        llTypeHeaderContainer = (LinearLayout) findViewById(R.id.ll_job_type_header_container);
        llTypeHeaderContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableJobType = !enableJobType;
                update(UPDATE_JOB_TYPE);
            }
        });
        llJobTypeTagContainer = (FlexboxLayout) findViewById(R.id.fb_job_type_tag_container);
        imgArrowType = (ImageView) findViewById(R.id.img_arrow_job_type);


        llJobLevelHeaderContainer = (LinearLayout) findViewById(R.id.ll_job_level_header_container);
        llJobLevelHeaderContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableJobLevel = !enableJobLevel;
                update(UPDATE_JOB_LEVEL);
            }
        });

        llJobLevelTagContainer = (FlexboxLayout) findViewById(R.id.fb_job_level_tag_container);
        imgArrowJobLevel = (ImageView) findViewById(R.id.img_arrow_job_level);

        llJobFunctionHeaderContainer = (LinearLayout) findViewById(R.id.ll_job_function_header_container);
        llJobFunctionHeaderContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableJobFunction = !enableJobFunction;
                update(UPDATE_JOB_FUNCTION);
            }
        });

        llJobFunctionTagContainer = (FlexboxLayout) findViewById(R.id.fb_job_function_tag_container);
        imgArrowJobFunction = (ImageView) findViewById(R.id.img_arrow_job_function);

        btnSearch = (Button) findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search();
            }
        });
    }

    private void search() {
        searchJobs = new SearchJobsDao();
        searchJobs.setKeyword(edtSearch.getText().toString().trim());
        searchJobs.setJobType(MyJobsFilterManager.getJobFilterChecked(listFilterJobTypeView));
        searchJobs.setJobLevel(MyJobsFilterManager.getJobFilterChecked(listFilterJobLevelView));
        searchJobs.setJobFunction(MyJobsFilterManager.getJobFilterChecked(listFilterJobFunctionView));

        if (mOnSearchFormListener != null) {
            mOnSearchFormListener.onSubmit(searchJobs);
        }
    }

    private void update() {
        update(UPDATE_ALL);
    }

    private void update(int mode) {
        // job type
        if (mode == UPDATE_ALL || mode == UPDATE_JOB_TYPE) {
            if (enableJobType) {
                imgArrowType.setImageResource(R.drawable.ic_arrow_up);
                llJobTypeContainer.setVisibility(View.VISIBLE);
                llJobTypeTagContainer.setVisibility(View.GONE);
            } else {
                imgArrowType.setImageResource(R.drawable.ic_arrow_down);
                llJobTypeContainer.setVisibility(View.GONE);
                llJobTypeTagContainer.setVisibility(View.VISIBLE);

                // display filter on tag.
                llJobTypeTagContainer.removeAllViews();
                ArrayList<JobsFilterDao> listJobTypeChecked = MyJobsFilterManager.getJobFilterChecked(listFilterJobTypeView);
                for (JobsFilterDao filter : listJobTypeChecked) {
                    MyFilterTagView view = new MyFilterTagView(getContext());
                    view.setFilterName(filter.getTitle());
                    llJobTypeTagContainer.addView(view);
                }
            }
        }

        // job level
        if (mode == UPDATE_ALL || mode == UPDATE_JOB_LEVEL) {
            if (enableJobLevel) {
                imgArrowJobLevel.setImageResource(R.drawable.ic_arrow_up);
                llJobLevelContainer.setVisibility(View.VISIBLE);
                llJobLevelTagContainer.setVisibility(View.GONE);
            } else {
                imgArrowJobLevel.setImageResource(R.drawable.ic_arrow_down);
                llJobLevelContainer.setVisibility(View.GONE);
                llJobLevelTagContainer.setVisibility(View.VISIBLE);

                // display filter on tag.
                llJobLevelTagContainer.removeAllViews();
                ArrayList<JobsFilterDao> listJobTypeChecked = MyJobsFilterManager.getJobFilterChecked(listFilterJobLevelView);
                for (JobsFilterDao filter : listJobTypeChecked) {
                    MyFilterTagView view = new MyFilterTagView(getContext());
                    view.setFilterName(filter.getTitle());
                    llJobLevelTagContainer.addView(view);
                }
            }
        }

        // job func
        if (mode == UPDATE_ALL || mode == UPDATE_JOB_FUNCTION) {
            if (enableJobFunction) {
                imgArrowJobFunction.setImageResource(R.drawable.ic_arrow_up);
                llJobFunctionContainer.setVisibility(View.VISIBLE);
                llJobFunctionTagContainer.setVisibility(View.GONE);
            } else {
                imgArrowJobFunction.setImageResource(R.drawable.ic_arrow_down);
                llJobFunctionContainer.setVisibility(View.GONE);
                llJobFunctionTagContainer.setVisibility(View.VISIBLE);

                // display filter on tag.
                llJobFunctionTagContainer.removeAllViews();
                ArrayList<JobsFilterDao> listJobTypeChecked = MyJobsFilterManager.getJobFilterChecked(listFilterJobFunctionView);
                for (JobsFilterDao filter : listJobTypeChecked) {
                    MyFilterTagView view = new MyFilterTagView(getContext());
                    view.setFilterName(filter.getTitle());
                    llJobFunctionTagContainer.addView(view);
                }
            }
        }
    }

    public void setOnSearchFormListener(OnSearchFormListener onSearchFormListener) {
        mOnSearchFormListener = onSearchFormListener;
    }

    public interface OnSearchFormListener {
        void onSubmit(SearchJobsDao sj);
    }

}
