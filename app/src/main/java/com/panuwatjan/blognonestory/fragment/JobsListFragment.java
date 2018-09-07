package com.panuwatjan.blognonestory.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.panuwatjan.blognonestory.MyConstants;
import com.panuwatjan.blognonestory.MySetting;
import com.panuwatjan.blognonestory.MyUtils;
import com.panuwatjan.blognonestory.R;
import com.panuwatjan.blognonestory.activity.MainActivity;
import com.panuwatjan.blognonestory.adapter.MyJobsRecyclerViewAdapter;
import com.panuwatjan.blognonestory.dao.jobs.JobsDao;
import com.panuwatjan.blognonestory.dialog.SearchJobsDialog;
import com.panuwatjan.blognonestory.search.job.JobsFilterDao;
import com.panuwatjan.blognonestory.search.job.SearchJobsDao;
import com.panuwatjan.blognonestory.service.blognone.jobs.MyBlognoneJobsManager;
import com.panuwatjan.blognonestory.view.EndlessRecyclerOnScrollListener;
import com.panuwatjan.blognonestory.view.MyFilterTagView;
import com.panuwatjan.blognonestory.view.MyRowJobsTitleView;

import java.util.ArrayList;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;


/**
 * A simple {@link Fragment} subclass.
 */
public class JobsListFragment extends Fragment {
    private static JobsListFragment sJobsListFragment;
    public static final int MODE_ALL_JOBS = 0;
    public static final int MODE_JOBS_CACHE = 8;
    private int mode;

    private int page = 1;
    private int indexSelected = -1;
    private SearchJobsDao sj = new SearchJobsDao();
    private RecyclerView mRecyclerView;
    private MyJobsRecyclerViewAdapter adapter;
    private LinearLayoutManager layoutManager;
    private ArrayList<JobsDao> listJobs = new ArrayList<>();
    private CircularProgressBar progress;

    private boolean loading = false;
    private TextView tvNoData;
    private TextView tvKeyword;
    private FrameLayout contentNodeContentContainer;  // for tablet.
    private LinearLayout llFilterJobTypeContainer;
    private LinearLayout llFilterJobLevelContainer;
    private LinearLayout llFilterJobFunctionContainer;

    public static JobsListFragment newInstance() {
        Bundle args = new Bundle();
        sJobsListFragment = new JobsListFragment();
        sJobsListFragment.setArguments(args);
        return sJobsListFragment;
    }

    public JobsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.fragment_jobs_list, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {

        }

        initView(v);
        if (savedInstanceState == null) {
            Log.d("BENZNEST LOG", "savedInstanceState = NULL");
            loadJobsListData();
        } else {
            mRecyclerView.scrollToPosition(savedInstanceState.getInt(MyConstants.KEY_POSITION_RECYCLERVIEW));
            page = savedInstanceState.getInt(MyConstants.KEY_PAGE);
        }

        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(MyConstants.KEY_POSITION_RECYCLERVIEW, layoutManager.findFirstVisibleItemPosition()); // get current recycle view position here.
        outState.putInt(MyConstants.KEY_PAGE, page);
        super.onSaveInstanceState(outState);
    }

    public static JobsListFragment getJobsListFragment() {
        return sJobsListFragment;
    }

    private void initView(View v) {
        progress = (CircularProgressBar) v.findViewById(R.id.progress);
        progress.setVisibility(View.GONE);

        layoutManager = new LinearLayoutManager(getContext());

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemViewCacheSize(6);
        mRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                if (!loading) {
                    page++;
                    loadJobsListData();
                }
            }
        });

        adapter = new MyJobsRecyclerViewAdapter();
        adapter.setOnJobListener(new MyRowJobsTitleView.OnJobListener() {
            @Override
            public void onJobSelected(JobsDao jobs) {
                selectNode(jobs);
            }

            @Override
            public void onSkillSelected(JobsDao jobs, String skill) {
//
            }
        });

        adapter.setData(listJobs);
        adapter.setEnableShowDetail(MySetting.isSettingShowDetailOfTopic(getContext()));
        mRecyclerView.setAdapter(adapter);

        tvNoData = (TextView) v.findViewById(R.id.tv_no_data);
        tvNoData.setVisibility(View.GONE);
        tvKeyword = (TextView) v.findViewById(R.id.tv_keyword);
        llFilterJobTypeContainer = (LinearLayout) v.findViewById(R.id.ll_filter_job_type_container);
        llFilterJobLevelContainer = (LinearLayout) v.findViewById(R.id.ll_filter_job_level_container);
        llFilterJobFunctionContainer = (LinearLayout) v.findViewById(R.id.ll_filter_job_function_container);

        if (MyUtils.isTablet()) {
            contentNodeContentContainer = (FrameLayout) v.findViewById(R.id.content_node_content_container);
        }
    }

    public void selectNode(JobsDao node) {
        if (MyUtils.isTablet()) {
            if (mode == MODE_JOBS_CACHE) {
                Fragment fragment = JobsBodyFragment.newInstance(node.getCompany().getSlug(), node.getSlug(), true);
                getChildFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_node_content_container, fragment)
                        .commit();
            } else {
                Fragment fragment = JobsBodyFragment.newInstance(node.getCompany().getSlug(), node.getSlug());
                getChildFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_node_content_container, fragment)
                        .commit();
            }
        } else {
            if (mode == MODE_JOBS_CACHE) {
                Fragment fragment = JobsBodyFragment.newInstance(node.getCompany().getSlug(), node.getSlug(), true);
                ((MainActivity) getActivity()).setContent(fragment, "");
            } else {
                Fragment fragment = JobsBodyFragment.newInstance(node.getCompany().getSlug(), node.getSlug());
                ((MainActivity) getActivity()).setContent(fragment, "");
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.node_job_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_refresh) {
            MyUtils.vibrate();
            page = 1;
            sj = new SearchJobsDao();
            listJobs = new ArrayList<>();
            update();
            loadJobsListData();
        } else if (item.getItemId() == R.id.action_search) {
            MyUtils.vibrate();
            openSearchJbsDialog();
        }
        return true;
    }

    private void openSearchJbsDialog() {
        final SearchJobsDialog dialog = new SearchJobsDialog(getContext());
        dialog.setSearchJobs(sj);
        dialog.setOnSearchFormListener(new SearchJobsDialog.OnSearchFormListener() {
            @Override
            public void onSubmit(SearchJobsDao searchJobsDao) {
                dialog.dismiss();
                sj = searchJobsDao;
                page = 1;
                loadJobsListData();
            }
        });
        dialog.show();
    }

    private void loadJobsListData() {
        loadingShow();
        if (mode == MODE_ALL_JOBS) {
            MyBlognoneJobsManager.loadJobsList(page, sj, new MyBlognoneJobsManager.OnLoadJobsListListener() {
                @Override
                public void onLoaded(ArrayList<JobsDao> list) {
                    if (page == 1) {
//                        MyCache.setNodeList(getContext(), list);
//                        MyWidgetManager.updateWidget();
                    }

                    listJobs.addAll(list);
                    update();
                    loadingFinish();
                }

                @Override
                public void onFail() {
                    if (page > 0) {
                        page--;
                    }
                    loadingFinish();
                }
            });
        }
    }

    private void loadingShow() {
        loading = true;
        if (progress != null) {
            progress.setVisibility(View.VISIBLE);
        }
    }

    private void loadingFinish() {
        loading = false;
        if (progress != null) {
            progress.setVisibility(View.GONE);
        }

        if (mode == MODE_ALL_JOBS) {
            tvNoData.setText("Unable to load data.");
        } else {
            tvNoData.setText("Unable to load data.");
        }

        if (tvNoData != null) {
            if (listJobs.isEmpty()) {
                tvNoData.setVisibility(View.VISIBLE);
            } else {
                tvNoData.setVisibility(View.GONE);
            }
        }
    }

    private void update() {
        if (isAdded()) {
            if (MyUtils.isTablet() && indexSelected == -1) {
                if (listJobs.size() > 0) {
                    selectNode(listJobs.get(0));
                    indexSelected = 0;
                }
            }

            adapter.setData(listJobs);
            adapter.setEnableShowDetail(MySetting.isSettingShowDetailOfTopic(getContext()));
            adapter.notifyDataSetChanged();

            if (sj.getKeyword().isEmpty()) {
                tvKeyword.setText("All");
            } else {
                tvKeyword.setText("\"" + sj.getKeyword() + "\"");
            }

            initFilterContainer(llFilterJobTypeContainer, sj.getJobType());
            initFilterContainer(llFilterJobLevelContainer, sj.getJobLevel());
            initFilterContainer(llFilterJobFunctionContainer, sj.getJobFunction());
        }
    }

    private void initFilterContainer(LinearLayout container, ArrayList<JobsFilterDao> list) {
        container.removeAllViews();
        if (list != null) {
            for (JobsFilterDao filter : list) {
                MyFilterTagView view = new MyFilterTagView(getContext());
                view.setFilterName(filter.getTitle());
                container.addView(view);
            }
        }
    }
}
