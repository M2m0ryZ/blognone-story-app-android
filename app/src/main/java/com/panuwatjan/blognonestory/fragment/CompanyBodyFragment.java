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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.panuwatjan.blognonestory.MyConstants;
import com.panuwatjan.blognonestory.MyFontSize;
import com.panuwatjan.blognonestory.MyGlide;
import com.panuwatjan.blognonestory.MyUtils;
import com.panuwatjan.blognonestory.R;
import com.panuwatjan.blognonestory.dao.jobs.CompanyDataDao;
import com.panuwatjan.blognonestory.dao.jobs.JobsDao;
import com.panuwatjan.blognonestory.dao.jobs.StoryBlockDao;
import com.panuwatjan.blognonestory.dao.jobs.TeamBlockDao;
import com.panuwatjan.blognonestory.service.blognone.jobs.MyBlognoneJobsManager;
import com.panuwatjan.blognonestory.view.MyRowJobsTitleView;
import com.panuwatjan.blognonestory.view.MyStoryBlockView;
import com.panuwatjan.blognonestory.view.MyTeamBlockView;
import com.panuwatjan.blognonestory.view.MyTextView;

import java.util.ArrayList;
import java.util.List;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyBodyFragment extends Fragment {
    private Context mContext;
    private boolean cacheMode = false;
    private CompanyDataDao node;
    private List<JobsDao> listJob = new ArrayList<>();
    private String mime = "text/html";
    private String encoding = "utf-8";
    private WebView webViewBody;

    private CircularProgressBar progress;
    private CoordinatorLayout mainContent;
    private String companySlug;
    private ImageView imgCover;
    private ImageView imgLogo;
    private ImageView imgVerified;
    private TextView tvCompany;
    private TextView tvDescription;
    private FrameLayout llContainerBodyBorder;
    private TextView tvCompany2;
    private TextView tvCompanyAddress;
    private TextView tvCompanyTel;
    private TextView tvCompanyEmail;
    private TextView tvCompanyWebsite;
    private TextView tvCompanyNameWorkingAt;
    private LinearLayout llStoryContainer;
    private LinearLayout llTeamContainer;
    private MyTextView tvBlognoneUrl;
    private ImageView imgCompany1;
    private ImageView imgCompany2;
    private LinearLayout llOtherJobContainer;
    private LinearLayout llStoryMainContainer;
    private LinearLayout llTeamMainContainer;
    private LinearLayout llOtherJobMainContainer;

    public CompanyBodyFragment() {
        // Required empty public constructor
    }

    public static CompanyBodyFragment newInstance(String companySlug) {
        Bundle args = new Bundle();
        args.putString(MyConstants.KEY_COMPANY_SLUG, companySlug);
        CompanyBodyFragment fragment = new CompanyBodyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static CompanyBodyFragment newInstance(String companySlug, boolean cacheMode) {
        Bundle args = new Bundle();
        args.putString(MyConstants.KEY_COMPANY_SLUG, companySlug);
        CompanyBodyFragment fragment = new CompanyBodyFragment();
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
            cacheMode = bundle.getBoolean(MyConstants.KEY_NODE_CACHE_MODE, false);
        }

        View v = inflater.inflate(R.layout.fragment_company_body, container, false);
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


        imgCover = (ImageView) v.findViewById(R.id.img_cover);
        imgLogo = (ImageView) v.findViewById(R.id.img_logo);
        imgVerified = (ImageView) v.findViewById(R.id.img_verified);
        tvCompany = (TextView) v.findViewById(R.id.tv_company);
        tvDescription = (TextView) v.findViewById(R.id.tv_description);
        llContainerBodyBorder = (FrameLayout) v.findViewById(R.id.ll_container_body_border);
        tvCompany2 = (TextView) v.findViewById(R.id.tv_company_2);
        tvCompanyAddress = (TextView) v.findViewById(R.id.tv_company_address);
        tvCompanyTel = (TextView) v.findViewById(R.id.tv_company_tel);
        tvCompanyEmail = (TextView) v.findViewById(R.id.tv_company_email);
        tvCompanyWebsite = (TextView) v.findViewById(R.id.tv_company_website);
        tvCompanyNameWorkingAt = (TextView) v.findViewById(R.id.tv_company_name_working_at);
        llStoryContainer = (LinearLayout) v.findViewById(R.id.ll_story_container);
        llTeamContainer = (LinearLayout) v.findViewById(R.id.ll_team_container);
        llOtherJobContainer = (LinearLayout) v.findViewById(R.id.ll_other_job_container);
        tvBlognoneUrl = (MyTextView) v.findViewById(R.id.tv_blognone_url);
        imgCompany1 = (ImageView) v.findViewById(R.id.img_company_1);
        imgCompany2 = (ImageView) v.findViewById(R.id.img_company_2);
        llStoryMainContainer = (LinearLayout) v.findViewById(R.id.ll_story_main_container);
        llTeamMainContainer = (LinearLayout) v.findViewById(R.id.ll_team_main_container);
        llOtherJobMainContainer = (LinearLayout) v.findViewById(R.id.ll_other_job_main_container);

        if (MyUtils.isTablet() && MyUtils.isLandscape()) {
            llStoryContainer.setOrientation(LinearLayout.HORIZONTAL);
        } else {
            llStoryContainer.setOrientation(LinearLayout.VERTICAL);
        }


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.node_content, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        MyUtils.vibrate();
        Log.d("BENZNEST LOG", "onOptionsItemSelected");
        if (item.getItemId() == R.id.action_open_in_browser) {
            MyUtils.openCompanyInBrowser(getActivity(), node.getSlug());
            Log.d("BENZNEST LOG", "action_open_in_browser");
        } else if (item.getItemId() == R.id.action_copy_link) {
            MyUtils.copyCompanyToClipboard(getContext(), node.getSlug());
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
            MyBlognoneJobsManager.loadCompanyBody(companySlug, new MyBlognoneJobsManager.OnLoadCompanyDataListener() {
                @Override
                public void onLoaded(CompanyDataDao companyDataDao, List<JobsDao> listJob) {
                    node = companyDataDao;
                    CompanyBodyFragment.this.listJob = listJob;
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
        tvCompany.setText(node.getNameEn());
        tvCompanyNameWorkingAt.setText("Working at "+node.getNameEn());

        if (node.getLogo() == null) {
            imgLogo.setVisibility(GONE);
        } else {
            imgLogo.setVisibility(VISIBLE);
            MyGlide.load(getContext(), imgLogo, node.getLogo());
        }

        MyGlide.load(getContext(), imgCover, node.getCover());
        if (node.getRichProfile().getCover2() == null) {
            imgCompany1.setVisibility(GONE);
        } else {
            MyGlide.load(getContext(), imgCompany1, node.getRichProfile().getCover2());
        }

        if (node.getRichProfile().getCover2() == null) {
            imgCompany1.setVisibility(GONE);
        } else {
            MyGlide.load(getContext(), imgCompany2, node.getRichProfile().getCover3());
        }

        if (node.getVerified()) {
            imgVerified.setVisibility(VISIBLE);
        } else {
            imgVerified.setVisibility(GONE);
        }

        tvDescription.setText(node.getIndustry());


        tvCompany2.setText(node.getNameEn());

        tvCompanyAddress.setText(node.getAddress());
        tvCompanyTel.setText(node.getPhone());
        tvCompanyEmail.setText(node.getEmail());
        tvCompanyWebsite.setText(node.getWebsite());


        String htmlBody = node.getLongDescription();
        htmlBody = "" +
//                MyBlognoneWeb.jsImportJquery +
//                MyBlognoneWeb.cssBlognone +
                htmlBody;


        webViewBody.loadDataWithBaseURL(null, htmlBody, mime, encoding, null);


        List<StoryBlockDao> listStory = node.getRichProfile().getStoryBlock();
        if (listStory != null && !listStory.isEmpty()) {
            llStoryMainContainer.setVisibility(VISIBLE);
            llStoryContainer.removeAllViews();
            for (StoryBlockDao s : listStory) {
                MyStoryBlockView view = new MyStoryBlockView(getContext());
                view.setStoryBlockDao(s);
                llStoryContainer.addView(view);
            }
        } else {
            llStoryMainContainer.setVisibility(GONE);
        }


        List<TeamBlockDao> listTeam = node.getRichProfile().getTeamBlock();
        if (listTeam != null && !listTeam.isEmpty()) {
            llTeamMainContainer.setVisibility(VISIBLE);
            llTeamContainer.removeAllViews();
            for (TeamBlockDao t : listTeam) {
                MyTeamBlockView view = new MyTeamBlockView(getContext());
                view.setTeamBlockDao(t);
                llTeamContainer.addView(view);
            }
        } else {
            llTeamMainContainer.setVisibility(GONE);
        }

        List<JobsDao> listOtherJob = listJob;
        if (listOtherJob != null && !listOtherJob.isEmpty()) {
            llOtherJobMainContainer.setVisibility(VISIBLE);
            llOtherJobContainer.removeAllViews();
            for (JobsDao j : listOtherJob) {
                j.setCompany(node.convertToCompanyDao()); // Same company.

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
        } else {
            llOtherJobMainContainer.setVisibility(GONE);
        }


        String url = "https://jobs.blognone.com/company/" + node.getSlug();
        tvBlognoneUrl.setText(url);

    }

    private void saveToHistory() {
//        BlognoneHistoryTopicDatabase.insert(nodeId, node);
    }

}
