package com.panuwatjan.blognonestory.fragment;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.panuwatjan.blognonestory.MyConstants;
import com.panuwatjan.blognonestory.MyFontSize;
import com.panuwatjan.blognonestory.MyGlide;
import com.panuwatjan.blognonestory.MyUtils;
import com.panuwatjan.blognonestory.R;
import com.panuwatjan.blognonestory.dao.jobs.CompanyDao;
import com.panuwatjan.blognonestory.dao.jobs.JobDataDao;
import com.panuwatjan.blognonestory.dao.jobs.JobsDao;
import com.panuwatjan.blognonestory.service.blognone.jobs.MyBlognoneJobsManager;
import com.panuwatjan.blognonestory.view.MyRowJobsTitleView;

import java.util.List;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * A simple {@link Fragment} subclass.
 */
public class JobsBodyFragment extends Fragment {
    private Context mContext;
    private boolean cacheMode = false;
    private JobDataDao node;
    private String mime = "text/html";
    private String encoding = "utf-8";
    private WebView webViewBody;

    private CircularProgressBar progress;
    private CoordinatorLayout mainContent;
    private TextView tvBlognoneUrl;
    private String companySlug;
    private String jobSlug;
    private ImageView imgNode;
    private TextView tvTitle;
    private TextView tvCompany;
    private TextView tvDistrict;
    private TextView tvSalary;
    private LinearLayout llOtherJobContainer;
    private TextView tvMoreAt;
    private TextView tvMoreCountJob;
    private TextView tvCompany2;
    private TextView tvCompanyAddress;
    private TextView tvCompanyTel;
    private TextView tvCompanyEmail;
    private TextView tvCompanyWebsite;

    public JobsBodyFragment() {
        // Required empty public constructor
    }

    public static JobsBodyFragment newInstance(String companySlug, String jobSlug) {
        Bundle args = new Bundle();
        args.putString(MyConstants.KEY_COMPANY_SLUG, companySlug);
        args.putString(MyConstants.KEY_JOB_SLUG, jobSlug);
        JobsBodyFragment fragment = new JobsBodyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static JobsBodyFragment newInstance(String companySlug, String jobSlug, boolean cacheMode) {
        Bundle args = new Bundle();
        args.putString(MyConstants.KEY_COMPANY_SLUG, companySlug);
        args.putString(MyConstants.KEY_JOB_SLUG, jobSlug);
        JobsBodyFragment fragment = new JobsBodyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        Bundle bundle = getArguments();
        if (bundle != null) {
            companySlug = bundle.getString(MyConstants.KEY_COMPANY_SLUG);
            jobSlug = bundle.getString(MyConstants.KEY_JOB_SLUG);
            cacheMode = bundle.getBoolean(MyConstants.KEY_NODE_CACHE_MODE, false);
        }

        View v = inflater.inflate(R.layout.fragment_jobs_body, container, false);
        initView(v);

        if (savedInstanceState == null) {
            loadNodeBody();
        }
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void initView(View v) {
        progress = (CircularProgressBar) v.findViewById(R.id.progress);
        progress.setVisibility(GONE);


        webViewBody = (WebView) v.findViewById(R.id.webview_body);
        webViewBody.getSettings().setJavaScriptEnabled(true);
        webViewBody.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webViewBody.getSettings().setTextSize(MyFontSize.getTextSizeWebViewCurrent(getContext()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            webViewBody.getSettings().setAllowUniversalAccessFromFileURLs(true);
            webViewBody.getSettings().setAllowFileAccessFromFileURLs(true);
        }

        mainContent = (CoordinatorLayout) v.findViewById(R.id.main_content);
        mainContent.setVisibility(View.INVISIBLE);

        tvBlognoneUrl = (TextView) v.findViewById(R.id.tv_blognone_url);
        imgNode = (ImageView) v.findViewById(R.id.img_node);
        tvTitle = (TextView) v.findViewById(R.id.tv_title);
        tvCompany = (TextView) v.findViewById(R.id.tv_company);
        tvDistrict = (TextView) v.findViewById(R.id.tv_district);
        tvSalary = (TextView) v.findViewById(R.id.tv_salary);
        llOtherJobContainer = (LinearLayout) v.findViewById(R.id.ll_other_job);
        tvMoreAt = (TextView) v.findViewById(R.id.tv_more_at);
        tvMoreCountJob = (TextView) v.findViewById(R.id.tv_more_count_job);
        tvCompany2 = (TextView) v.findViewById(R.id.tv_company_2);
        tvCompanyAddress = (TextView) v.findViewById(R.id.tv_company_address);
        tvCompanyTel = (TextView) v.findViewById(R.id.tv_company_tel);
        tvCompanyEmail = (TextView) v.findViewById(R.id.tv_company_email);
        tvCompanyWebsite = (TextView) v.findViewById(R.id.tv_company_website);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.node_content, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        MyUtils.vibrate();
//        Log.d("BENZNEST LOG", "onOptionsItemSelected");
        if (item.getItemId() == R.id.action_open_in_browser) {
            MyUtils.openJobsInBrowser(getActivity(), node.getJob());
            Log.d("BENZNEST LOG", "action_open_in_browser");
        } else if (item.getItemId() == R.id.action_copy_link) {
            MyUtils.copyJobsToClipboard(getContext(), node.getJob());
        } else if (item.getItemId() == R.id.action_refresh_content) {
            loadNodeBody();
        }
        return true;
    }

    private void saveToCache() {
//        BlognoneTopicCacheDatabase.insert(nodeId, node);
        MyUtils.toast(getContext(), "Saved.");
    }

    private void loadingShow() {
        if (progress != null) {
            progress.setVisibility(VISIBLE);
        }
    }

    private void loadingFinish() {
        if (progress != null) {
            progress.setVisibility(GONE);
        }
    }

    private void loadNodeBody() {
        if (cacheMode) {
//            node = BlognoneTopicCacheDatabase.getOnlyNode(nodeId);
//            update();
//            mainContent.setVisibility(View.VISIBLE);
        } else {
            loadingShow();
            MyBlognoneJobsManager.loadJobBody(companySlug, jobSlug, new MyBlognoneJobsManager.OnLoadJobsDataListener() {
                @Override
                public void onLoaded(JobDataDao jobData) {
                    node = jobData;
                    update();
                    mainContent.setVisibility(VISIBLE);
                    loadingFinish();
                }

                @Override
                public void onFail() {
                    MyUtils.log("onFail");
                    loadingFinish();
                }
            });
        }
    }

    private void update() {
        tvTitle.setText(node.getJob().getTitle());

        if (node.getJob().getCompany().getLogo() == null) {
            imgNode.setVisibility(GONE);
        } else {
            imgNode.setVisibility(VISIBLE);
        }

        tvCompany.setText(node.getJob().getCompany().getNameEn());

        tvDistrict.setText(node.getJob().getDistrict() + " " + node.getJob().getProvince());

        tvSalary.setText(MyUtils.comma(node.getJob().getSalaryMin()) + "-" + MyUtils.comma(node.getJob().getSalaryMax()) + " THB");

        MyGlide.load(getContext(), imgNode, node.getJob().getCompany().getLogo());

        tvMoreAt.setText("More at " + node.getJob().getCompany().getNameEn());

        int countOtherJob = node.getOtherJobs().size();
        tvMoreCountJob.setText(countOtherJob + " " + getResources().getQuantityString(R.plurals.other_opening, countOtherJob));

        List<JobsDao> listOtherJob = node.getOtherJobs();
        llOtherJobContainer.removeAllViews();
        for (JobsDao j : listOtherJob) {
            j.setCompany(node.getJob().getCompany()); // Same company.
            MyRowJobsTitleView view = new MyRowJobsTitleView(getContext());
            view.setJob(j);
            view.setOnJobsListener(new MyRowJobsTitleView.OnJobListener() {
                @Override
                public void onJobSelected(JobsDao jobs) {
                    JobsListFragment.getJobsListFragment().selectNode(jobs);
                }

                @Override
                public void onSkillSelected(JobsDao jobs, String skill) {

                }
            });
            llOtherJobContainer.addView(view);
        }

        CompanyDao companyDao = node.getJob().getCompany();
        tvCompany2.setText(companyDao.getNameEn());

        tvCompanyAddress.setText(companyDao.getAddress());
        tvCompanyTel.setText(companyDao.getPhone());
        tvCompanyEmail.setText(companyDao.getEmail());
        tvCompanyWebsite.setText(companyDao.getWebsite());


//        ArrayList<String> lisTag = node.getTags();
//        for (final String tag : lisTag) {
//            MyTagView myTagView = null;
//            if (getContext() != null) {
//                myTagView = new MyTagView(getContext());
//            } else {
//                myTagView = new MyTagView(mContext);
//            }
//
//            myTagView.setTagName(tag);
//            myTagView.setStickyMode(node.isSticky());
//            myTagView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Fragment fragment = NodeListFragment.newInstance(tag);
//                    ((MainActivity) getActivity()).setContent(fragment, "");
//                }
//            });
//            llTagsContainer.addView(myTagView);
//        }

        String htmlBody = node.getJob().getDescription();
        htmlBody = "" +
//                MyBlognoneWeb.jsImportJquery +
//                MyBlognoneWeb.cssBlognone +
                htmlBody;


        webViewBody.loadDataWithBaseURL(null, htmlBody, mime, encoding, null);

        String url = "https://jobs.blognone.com/company/" + node.getJob().getCompany().getSlug() + "/job" + node.getJob().getSlug();
        tvBlognoneUrl.setText(url);

    }

    private void saveToHistory() {
//        BlognoneHistoryTopicDatabase.insert(nodeId, node);
    }

}
