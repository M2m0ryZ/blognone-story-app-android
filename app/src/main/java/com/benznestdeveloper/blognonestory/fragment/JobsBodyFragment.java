package com.benznestdeveloper.blognonestory.fragment;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
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

import com.benznestdeveloper.blognonestory.MyConstants;
import com.benznestdeveloper.blognonestory.MyFontSize;
import com.benznestdeveloper.blognonestory.MyGlide;
import com.benznestdeveloper.blognonestory.MyUtils;
import com.benznestdeveloper.blognonestory.R;
import com.benznestdeveloper.blognonestory.dao.jobs.JobDataDao;
import com.benznestdeveloper.blognonestory.dao.jobs.JobsDao;
import com.benznestdeveloper.blognonestory.service.blognone.jobs.MyBlognoneJobsManager;
import com.benznestdeveloper.blognonestory.service.blognone.web.MyBlognoneService;
import com.benznestdeveloper.blognonestory.view.MyNodeOptionTabViewView;
import com.benznestdeveloper.blognonestory.view.MyRowJobsTitleView;

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
    private MyNodeOptionTabViewView tabOptionView;
    private TextView tvBlognoneUrl;
    private String companySlug;
    private String jobSlug;
    private ImageView imgNode;
    private TextView tvTitle;
    private TextView tvCompany;
    private TextView tvDistrict;
    private TextView tvSalary;
    private LinearLayout llOtherJobContainer;

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
//
//    public static JobsBodyFragment newInstance(int id, boolean cacheMode) {
//        Bundle args = new Bundle();
//        args.putInt(MyConstants.KEY_NODE_ID, id);
//        args.putBoolean(MyConstants.KEY_NODE_CACHE_MODE, cacheMode);
//        JobsBodyFragment fragment = new JobsBodyFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }

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

        tabOptionView = (MyNodeOptionTabViewView) v.findViewById(R.id.tab_option_view);
//        tabOptionView.setFavorite(BlognoneFavoriteTopicDatabase.isFavorite(nodeId));
//        tabOptionView.setSaveTopic(BlognoneTopicCacheDatabase.isCache(nodeId));

        tabOptionView.setOnNodeOptionListener(new MyNodeOptionTabViewView.OnNodeOptionListener() {
            @Override
            public void onFavoriteChanged(boolean favorite) {
                MyUtils.vibrate();
//                if (favorite) {
//                    BlognoneFavoriteTopicDatabase.insert(nodeId, node);
//                } else {
//                    BlognoneFavoriteTopicDatabase.delete(nodeId);
//                }
            }

            @Override
            public void onShareNode() {
                MyUtils.vibrate();
//                MyUtils.shareLink(getActivity(), nodeId);
            }

            @Override
            public void onSaveTopicChanged(boolean save) {
                MyUtils.vibrate();
                if (save) {
                    saveToCache();
                } else {
//                    BlognoneTopicCacheDatabase.delete(nodeId);
                }
            }
        });

        tvBlognoneUrl = (TextView) v.findViewById(R.id.tv_blognone_url);
        imgNode = (ImageView) v.findViewById(R.id.img_node);
        tvTitle = (TextView) v.findViewById(R.id.tv_title);
        tvCompany = (TextView) v.findViewById(R.id.tv_company);
        tvDistrict = (TextView) v.findViewById(R.id.tv_district);
        tvSalary = (TextView) v.findViewById(R.id.tv_salary);
        llOtherJobContainer = (LinearLayout) v.findViewById(R.id.ll_other_job);
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
//        if (item.getItemId() == R.id.action_open_in_browser) {
//            MyUtils.openBrowser(getActivity(), nodeId);
//            Log.d("BENZNEST LOG", "action_open_in_browser");
//        } else if (item.getItemId() == R.id.action_copy_link) {
//            MyUtils.copyToClipboard(getContext(), nodeId);
//        } else if (item.getItemId() == R.id.action_refresh_content) {
//            loadNodeBody();
//        }
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
            MyBlognoneJobsManager.loadJobBody(companySlug, jobSlug, new MyBlognoneJobsManager.OnLoadJobsDataListListener() {
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

        List<JobsDao> listOtherJob = node.getOtherJobs();
        llOtherJobContainer.removeAllViews();
        for (JobsDao j : listOtherJob) {
            j.setCompany(node.getJob().getCompany()); // Same company.
            MyRowJobsTitleView view = new MyRowJobsTitleView(getContext());
            view.setJob(j);
            llOtherJobContainer.addView(view);
        }

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

//        if (node.isSticky()) {
//            flTitleContainer.setBackgroundResource(R.color.colorYellowDark2);
//            llContainerBody.setBackgroundResource(R.color.colorBackgroundSticky);
//            llContainerBodyBorder.setBackgroundResource(R.color.colorYellowDark2);
//
//        } else if (node.isWorkplace()) {
//            flTitleContainer.setBackgroundResource(R.color.colorWorkplace);
//            llContainerBody.setBackgroundResource(R.color.colorBackgroundWorkplace);
//            llContainerBodyBorder.setBackgroundResource(R.color.colorWorkplace);
//        } else {
//            flTitleContainer.setBackgroundResource(R.color.colorPrimary);
//            llContainerBody.setBackgroundResource(R.color.colorWhite);
//            llContainerBodyBorder.setBackgroundResource(R.color.colorWhite);
//        }

        tvBlognoneUrl.setText(MyBlognoneService.getBaseUrl() + "node/" + node.getJob().getSlug());

    }

    private void saveToHistory() {
//        BlognoneHistoryTopicDatabase.insert(nodeId, node);
    }

}
