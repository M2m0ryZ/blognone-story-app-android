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
import android.widget.TextView;

import com.panuwatjan.blognonestory.MyConstants;
import com.panuwatjan.blognonestory.MySetting;
import com.panuwatjan.blognonestory.MyUtils;
import com.panuwatjan.blognonestory.R;
import com.panuwatjan.blognonestory.activity.MainActivity;
import com.panuwatjan.blognonestory.adapter.MyCompaniesRecyclerViewAdapter;
import com.panuwatjan.blognonestory.dao.jobs.CompanyDao;
import com.panuwatjan.blognonestory.service.blognone.jobs.MyBlognoneJobsManager;
import com.panuwatjan.blognonestory.view.EndlessRecyclerOnScrollListener;
import com.panuwatjan.blognonestory.view.MyRowCompanyTitleView;

import java.util.ArrayList;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;


/**
 * A simple {@link Fragment} subclass.
 */
public class CompaniesListFragment extends Fragment {
    private static CompaniesListFragment sCompaniesListFragment;
    public static final int MODE_ALL_COMPANIES = 0;
    public static final int MODE_JOBS_CACHE = 8;
    private int mode;

    private int page = 1;
    private int indexSelected = -1;
    private RecyclerView mRecyclerView;
    private MyCompaniesRecyclerViewAdapter adapter;
    private LinearLayoutManager layoutManager;
    private ArrayList<CompanyDao> listCompanies = new ArrayList<>();
    private CircularProgressBar progress;

    private boolean loading = false;
    private TextView tvNoData;

    private FrameLayout contentNodeContentContainer;

    public static CompaniesListFragment newInstance() {
        Bundle args = new Bundle();
        sCompaniesListFragment = new CompaniesListFragment();
        sCompaniesListFragment.setArguments(args);
        return sCompaniesListFragment;
    }

    public CompaniesListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        getActivity().setTitle("Companies");
        View v = inflater.inflate(R.layout.fragment_companies_list, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {

        }

        initView(v);
        if (savedInstanceState == null) {
            Log.d("BENZNEST LOG", "savedInstanceState = NULL");
            loadCompaniesListData();
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

    public static CompaniesListFragment getCompaniesListFragment() {
        return sCompaniesListFragment;
    }

    private void initView(View v) {
        progress = (CircularProgressBar) v.findViewById(R.id.progress);
        progress.setVisibility(View.GONE);

        layoutManager = new LinearLayoutManager(getContext());

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemViewCacheSize(8);
        mRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                if (!loading) {
                    page++;
                    loadCompaniesListData();
                }
            }
        });

        adapter = new MyCompaniesRecyclerViewAdapter();
        adapter.setOnCompanyListener(new MyRowCompanyTitleView.OnCompanyListener() {
            @Override
            public void onCompanySelected(CompanyDao companyDao) {
                selectCompany(companyDao);
            }
        });

        adapter.setData(listCompanies);
        mRecyclerView.setAdapter(adapter);

        tvNoData = (TextView) v.findViewById(R.id.tv_no_data);
        tvNoData.setVisibility(View.GONE);

        if (MyUtils.isTablet()) {
            contentNodeContentContainer = (FrameLayout) v.findViewById(R.id.content_node_content_container);
        }
    }

    public void selectCompany(CompanyDao node) {
        if (MyUtils.isTablet()) {
//            if (mode == MODE_JOBS_CACHE) {
//                Fragment fragment = JobsBodyFragment.newInstance(node.getCompany().getSlug(), node.getSlug(), true);
//                getChildFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.content_node_content_container, fragment)
//                        .commit();
//            } else {
//                Fragment fragment = JobsBodyFragment.newInstance(node.getCompany().getSlug(), node.getSlug());
//                getChildFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.content_node_content_container, fragment)
//                        .commit();
//            }
        } else {
            if (mode == MODE_JOBS_CACHE) {
                Fragment fragment = CompanyBodyFragment.newInstance(node.getSlug(), true);
                ((MainActivity) getActivity()).setContent(fragment, "");
            } else {
                Fragment fragment = CompanyBodyFragment.newInstance(node.getSlug());
                ((MainActivity) getActivity()).setContent(fragment, "");
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.node_companies_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_refresh) {
            MyUtils.vibrate();
            page = 1;
            listCompanies = new ArrayList<>();
            update();
            loadCompaniesListData();
        }
        return true;
    }

    private void loadCompaniesListData() {
        loadingShow();
        if (mode == MODE_ALL_COMPANIES) {
            MyBlognoneJobsManager.loadCompaniesList(page, new MyBlognoneJobsManager.OnLoadCompaniesDataListListener() {
                @Override
                public void onLoaded(ArrayList<CompanyDao> list) {
                    if (page == 1) {
//                        MyCache.setNodeList(getContext(), list);
//                        MyWidgetManager.updateWidget();
                    }

                    listCompanies.addAll(list);
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

        if (mode == MODE_ALL_COMPANIES) {
            tvNoData.setText("Unable to load data.");
        } else {
            tvNoData.setText("Unable to load data.");
        }

        if (tvNoData != null) {
            if (listCompanies.isEmpty()) {
                tvNoData.setVisibility(View.VISIBLE);
            } else {
                tvNoData.setVisibility(View.GONE);
            }
        }
    }

    private void update() {
        if (isAdded()) {
            if (MyUtils.isTablet() && indexSelected == -1) {
                if (listCompanies.size() > 0) {
                    selectCompany(listCompanies.get(0));
                    indexSelected = 0;
                }
            }

            adapter.setData(listCompanies);
            adapter.setEnableShowDetail(MySetting.isSettingShowDetailOfTopic(getContext()));
            adapter.notifyDataSetChanged();
        }
    }
}
