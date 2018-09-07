package com.panuwatjan.blognonestory.fragment;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.panuwatjan.blognonestory.MyFontSize;
import com.panuwatjan.blognonestory.MyUtils;
import com.panuwatjan.blognonestory.R;
import com.panuwatjan.blognonestory.dao.BlognoneNodeDao;
import com.panuwatjan.blognonestory.database.BlognoneTopicCacheDatabase;
import com.panuwatjan.blognonestory.service.blognone.web.MyBlognoneService;
import com.panuwatjan.blognonestory.service.blognone.web.MyBlognoneWeb;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;

/**
 * A simple {@link Fragment} subclass.
 */
public class NodeCommentFragment extends Fragment {
    private static NodeCommentFragment fragment;
    private Context mContext;
    private BlognoneNodeDao node;
    private String mime = "text/html";
    private String encoding = "utf-8";
    private int nodeId = 100702;

    private WebView webviewComment;
    private CircularProgressBar progress;
    private TextView tvCommentCount;
    private TextView tvBlognoneUrl;

    public NodeCommentFragment() {
        // Required empty public constructor
    }

    public static NodeCommentFragment newInstance() {
        Bundle args = new Bundle();
        fragment = new NodeCommentFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public static NodeCommentFragment getFragment() {
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.fragment_node_comment, container, false);
        initView(v);
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

        webviewComment = (WebView) v.findViewById(R.id.webview_comment);
        webviewComment.getSettings().setJavaScriptEnabled(true);
        webviewComment.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webviewComment.getSettings().setTextSize(MyFontSize.getTextSizeWebViewCurrent(getContext()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            webviewComment.getSettings().setAllowUniversalAccessFromFileURLs(true);
            webviewComment.getSettings().setAllowFileAccessFromFileURLs(true);
        }

        tvCommentCount = (TextView) v.findViewById(R.id.tv_last_update);
        tvBlognoneUrl = (TextView) v.findViewById(R.id.tv_blognone_url);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.empty, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        MyUtils.vibrate();
//        Log.d("BENZNEST LOG", "onOptionsItemSelected");
//        if (item.getItemId() == R.id.action_open_in_browser) {
//            MyUtils.openNodeInBrowser(getActivity(), nodeId);
//            Log.d("BENZNEST LOG", "action_open_in_browser");
//        } else if (item.getItemId() == R.id.action_copy_link) {
//            MyUtils.copyToClipboard(getContext(), nodeId);
//        } else if (item.getItemId() == R.id.action_refresh_content) {
//            loadNodeBody();
//        }
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

    public void update(BlognoneNodeDao node) {
        tvCommentCount.setText("Comment (" + node.getCountComment() + ")");
        String htmlComment = node.getHtmlComment();
        htmlComment = "" +
                MyBlognoneWeb.jsImportJquery +
                MyBlognoneWeb.cssBlognone +
                htmlComment;
        webviewComment.loadDataWithBaseURL(null, htmlComment, mime, encoding, null);
        tvBlognoneUrl.setText(MyBlognoneService.getBaseUrl() + "node/" + node.getId());
        loadingFinish();
    }

}
