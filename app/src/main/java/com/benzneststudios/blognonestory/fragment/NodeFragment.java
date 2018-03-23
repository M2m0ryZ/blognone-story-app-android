package com.benzneststudios.blognonestory.fragment;


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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.benzneststudios.blognonestory.MyConstants;
import com.benzneststudios.blognonestory.MyFontSize;
import com.benzneststudios.blognonestory.MySetting;
import com.benzneststudios.blognonestory.MyUtils;
import com.benzneststudios.blognonestory.R;
import com.benzneststudios.blognonestory.activity.MainActivity;
import com.benzneststudios.blognonestory.dao.BlognoneNodeDao;
import com.benzneststudios.blognonestory.database.BlognoneFavoriteTopicDatabase;
import com.benzneststudios.blognonestory.database.BlognoneHistoryTopicDatabase;
import com.benzneststudios.blognonestory.database.BlognoneTopicCacheDatabase;
import com.benzneststudios.blognonestory.service.blognone.MyBlognoneManager;
import com.benzneststudios.blognonestory.service.blognone.MyBlognoneService;
import com.benzneststudios.blognonestory.service.blognone.MyBlognoneWeb;
import com.benzneststudios.blognonestory.view.MyNodeOptionTabViewView;
import com.benzneststudios.blognonestory.view.MyTagView;

import java.util.ArrayList;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;

/**
 * A simple {@link Fragment} subclass.
 */
public class NodeFragment extends Fragment {
    private Context mContext;
    private boolean cacheMode = false;
    private BlognoneNodeDao node;
    private String mime = "text/html";
    private String encoding = "utf-8";
    private int nodeId = 100702;
    private TextView tvTitle;
    private FrameLayout llContainerBodyBorder;
    private LinearLayout llContainerBody;
    private WebView webViewBody;
    private TextView tvWriter;
    private TextView tvDate;
    private WebView webviewComment;
    private CircularProgressBar progress;
    private CoordinatorLayout mainContent;
    private FrameLayout flTitleContainer;
    private LinearLayout llTagsContainer;
    private MyNodeOptionTabViewView tabOptionView;
    private TextView tvCommentCount;
    private TextView tvBlognoneUrl;

    public NodeFragment() {
        // Required empty public constructor
    }

    public static NodeFragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt(MyConstants.KEY_NODE_ID, id);
        NodeFragment fragment = new NodeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static NodeFragment newInstance(int id, boolean cacheMode) {
        Bundle args = new Bundle();
        args.putInt(MyConstants.KEY_NODE_ID, id);
        args.putBoolean(MyConstants.KEY_NODE_CACHE_MODE, cacheMode);
        NodeFragment fragment = new NodeFragment();
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
            nodeId = bundle.getInt(MyConstants.KEY_NODE_ID);
            cacheMode = bundle.getBoolean(MyConstants.KEY_NODE_CACHE_MODE, false);
        }

        View v = inflater.inflate(R.layout.fragment_node, container, false);
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
        progress.setVisibility(View.GONE);

        tvTitle = (TextView) v.findViewById(R.id.tv_title);
        llContainerBodyBorder = (FrameLayout) v.findViewById(R.id.ll_container_body_border);
        llContainerBody = (LinearLayout) v.findViewById(R.id.ll_container_body);

        webViewBody = (WebView) v.findViewById(R.id.webview_body);
        webViewBody.getSettings().setJavaScriptEnabled(true);
        webViewBody.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webViewBody.getSettings().setTextSize(MyFontSize.getTextSizeWebViewCurrent(getContext()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            webViewBody.getSettings().setAllowUniversalAccessFromFileURLs(true);
            webViewBody.getSettings().setAllowFileAccessFromFileURLs(true);
        }

        tvWriter = (TextView) v.findViewById(R.id.tv_writer);
        tvDate = (TextView) v.findViewById(R.id.tv_date);
        webviewComment = (WebView) v.findViewById(R.id.webview_comment);
        webviewComment.getSettings().setJavaScriptEnabled(true);
        webviewComment.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webviewComment.getSettings().setTextSize(MyFontSize.getTextSizeWebViewCurrent(getContext()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            webviewComment.getSettings().setAllowUniversalAccessFromFileURLs(true);
            webviewComment.getSettings().setAllowFileAccessFromFileURLs(true);
        }

        mainContent = (CoordinatorLayout) v.findViewById(R.id.main_content);
        mainContent.setVisibility(View.INVISIBLE);
        flTitleContainer = (FrameLayout) v.findViewById(R.id.fl_title_container);
        llTagsContainer = (LinearLayout) v.findViewById(R.id.ll_tags_container);

        tabOptionView = (MyNodeOptionTabViewView) v.findViewById(R.id.tab_option_view);
        tabOptionView.setFavorite(BlognoneFavoriteTopicDatabase.isFavorite(nodeId));
        tabOptionView.setSaveTopic(BlognoneTopicCacheDatabase.isCache(nodeId));

        tabOptionView.setOnNodeOptionListener(new MyNodeOptionTabViewView.OnNodeOptionListener() {
            @Override
            public void onFavoriteChanged(boolean favorite) {
                MyUtils.vibrate();
                if (favorite) {
                    BlognoneFavoriteTopicDatabase.insert(nodeId, node);
                } else {
                    BlognoneFavoriteTopicDatabase.delete(nodeId);
                }
            }

            @Override
            public void onShareNode() {
                MyUtils.vibrate();
                MyUtils.shareLink(getActivity(), nodeId);
            }

            @Override
            public void onSaveTopicChanged(boolean save) {
                MyUtils.vibrate();
                if (save) {
                    saveToCache();
                } else {
                    BlognoneTopicCacheDatabase.delete(nodeId);
                }
            }
        });
        tvCommentCount = (TextView) v.findViewById(R.id.tv_comment_count);
        tvBlognoneUrl = (TextView) v.findViewById(R.id.tv_blognone_url);
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
            MyUtils.openBrowser(getActivity(), nodeId);
            Log.d("BENZNEST LOG", "action_open_in_browser");
        } else if (item.getItemId() == R.id.action_copy_link) {
            MyUtils.copyToClipboard(getContext(), nodeId);
        } else if (item.getItemId() == R.id.action_refresh_content) {
            loadNodeBody();
        }
        return true;
    }

    private void saveToCache() {
        BlognoneTopicCacheDatabase.insert(nodeId, node);
        MyUtils.toast(getContext(), "Saved.");
    }

    private void loadingShow() {
        if (progress != null) {
            progress.setVisibility(View.VISIBLE);
        }
    }

    private void loadingFinish() {
        if (progress != null) {
            progress.setVisibility(View.GONE);
        }
    }

    private void loadNodeBody() {
        if (cacheMode) {
            node = BlognoneTopicCacheDatabase.getOnlyNode(nodeId);
            update();
            mainContent.setVisibility(View.VISIBLE);
        } else {
            loadingShow();
            MyBlognoneManager.loadNodeContent(nodeId, new MyBlognoneManager.OnLoadNodeContentListener() {
                @Override
                public void onLoaded(BlognoneNodeDao n) {
                    node = n;
                    update();
                    mainContent.setVisibility(View.VISIBLE);
                    loadingFinish();
                }

                @Override
                public void onFail() {
                    loadingFinish();
                }
            });
        }
    }

    private void update() {
        saveToHistory();

        if (MySetting.isSettingAutoCacheTopic(getContext())) {
            saveToCache();
        }

        tvTitle.setText(node.getTitle());
        tvWriter.setText("By " + node.getWriter());
        tvDate.setText(node.getDate());
        tvCommentCount.setText("Comment (" + node.getCountComment() + ")");

        llTagsContainer.removeAllViews();
        ArrayList<String> lisTag = node.getTags();
        for (final String tag : lisTag) {
            MyTagView myTagView = null;
            if (getContext() != null) {
                myTagView = new MyTagView(getContext());
            } else {
                myTagView = new MyTagView(mContext);
            }

            myTagView.setTagName(tag);
            myTagView.setStickyMode(node.isSticky());
            myTagView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment fragment = NodeListFragment.newInstance(tag);
                    ((MainActivity) getActivity()).setContent(fragment, "");
                }
            });
            llTagsContainer.addView(myTagView);
        }

        String htmlBody = node.getHtmlContent();
        htmlBody = "" +
                MyBlognoneWeb.jsImportJquery +
                MyBlognoneWeb.cssBlognone +
                htmlBody;

        String htmlComment = node.getHtmlComment();
        webViewBody.loadDataWithBaseURL(null, htmlBody, mime, encoding, null);

        htmlComment = "" +
                MyBlognoneWeb.jsImportJquery +
                MyBlognoneWeb.cssBlognone +
                htmlComment;
        webviewComment.loadDataWithBaseURL(null, htmlComment, mime, encoding, null);

        if (node.isSticky()) {
            flTitleContainer.setBackgroundResource(R.color.colorYellowDark2);
            llContainerBody.setBackgroundResource(R.color.colorBackgroundSticky);
            llContainerBodyBorder.setBackgroundResource(R.color.colorYellowDark2);

        } else if (node.isWorkplace()) {
            flTitleContainer.setBackgroundResource(R.color.colorWorkplace);
            llContainerBody.setBackgroundResource(R.color.colorBackgroundWorkplace);
            llContainerBodyBorder.setBackgroundResource(R.color.colorWorkplace);
        } else {
            flTitleContainer.setBackgroundResource(R.color.colorPrimary);
            llContainerBody.setBackgroundResource(R.color.colorWhite);
            llContainerBodyBorder.setBackgroundResource(R.color.colorWhite);
        }

        tvBlognoneUrl.setText(MyBlognoneService.getBaseUrl() + "node/" + node.getId());

    }

    private void saveToHistory() {
        BlognoneHistoryTopicDatabase.insert(nodeId, node);
    }

}
