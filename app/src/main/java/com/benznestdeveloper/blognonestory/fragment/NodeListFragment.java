package com.benznestdeveloper.blognonestory.fragment;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.benznestdeveloper.blognonestory.MyCache;
import com.benznestdeveloper.blognonestory.MyConstants;
import com.benznestdeveloper.blognonestory.MySetting;
import com.benznestdeveloper.blognonestory.MyUtils;
import com.benznestdeveloper.blognonestory.R;
import com.benznestdeveloper.blognonestory.activity.MainActivity;
import com.benznestdeveloper.blognonestory.adapter.MyNodeRecyclerViewAdapter;
import com.benznestdeveloper.blognonestory.dao.BlognoneForumDao;
import com.benznestdeveloper.blognonestory.dao.BlognoneNodeDao;
import com.benznestdeveloper.blognonestory.database.BlognoneFavoriteTopicDatabase;
import com.benznestdeveloper.blognonestory.database.BlognoneHistoryTopicDatabase;
import com.benznestdeveloper.blognonestory.database.BlognoneTopicCacheDatabase;
import com.benznestdeveloper.blognonestory.service.blognone.web.MyBlognoneForum;
import com.benznestdeveloper.blognonestory.service.blognone.web.MyBlognoneManager;
import com.benznestdeveloper.blognonestory.view.EndlessRecyclerOnScrollListener;
import com.benznestdeveloper.blognonestory.view.MyRowNodeTitleView;
import com.benznestdeveloper.blognonestory.widget.MyWidgetManager;

import java.util.ArrayList;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;


/**
 * A simple {@link Fragment} subclass.
 */
public class NodeListFragment extends Fragment {

    public static final int MODE_ALL_NODE = 0;
    public static final int MODE_INTERVIEW = 2;
    public static final int MODE_FEATURE = 1;
    public static final int MODE_JOB = 3;
    public static final int MODE_WORKPLACE = 4;
    public static final int MODE_FORUM = 5;
    public static final int MODE_TAG = 6;
    public static final int MODE_FAVORITE = 7;
    public static final int MODE_NODE_CACHE = 8;
    public static final int MODE_NODE_HISTORY = 9;
    public static final int MODE_NODE_UPCOMING = 10;
    private int mode;

    private int indexSelected = -1;
    private String searchKeyword = "";
    private RecyclerView mRecyclerView;
    private MyNodeRecyclerViewAdapter adapter;
    private LinearLayoutManager layoutManager;
    private ArrayList<BlognoneNodeDao> listNode = new ArrayList<>();
    private CircularProgressBar progress;
    private int page = 0;
    private boolean loading = false;
    private String tag;
    private BlognoneForumDao forumDao;
    private TextView tvNoData;
    private AppBarLayout appBarSearch;
    private TextView tvTotal;
    private EditText edtSearch;
    private FrameLayout contentNodeContentContainer;

    public static NodeListFragment newInstance(int mode) {
        Bundle args = new Bundle();
        args.putInt(MyConstants.KEY_MODE_NODE, mode);
        NodeListFragment fragment = new NodeListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static NodeListFragment newInstance(String tag) {
        tag = tag.replace(".","");
        Bundle args = new Bundle();
        args.putInt(MyConstants.KEY_MODE_NODE, MODE_TAG);
        args.putString(MyConstants.KEY_NODE_TAG, tag);
        NodeListFragment fragment = new NodeListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static NodeListFragment newInstance(BlognoneForumDao forum) {
        Bundle args = new Bundle();
        args.putInt(MyConstants.KEY_MODE_NODE, MODE_FORUM);
        args.putParcelable(MyConstants.KEY_FORUM, forum);
        NodeListFragment fragment = new NodeListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public NodeListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.fragment_node_list, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            mode = bundle.getInt(MyConstants.KEY_MODE_NODE);
            forumDao = bundle.getParcelable(MyConstants.KEY_FORUM);
            tag = bundle.getString(MyConstants.KEY_NODE_TAG, null);

            if (forumDao != null) {
                getActivity().setTitle(forumDao.getTitle());
            }

            if (tag != null) {
                getActivity().setTitle(tag);
            }
        }

        initView(v);
        if (savedInstanceState == null) {
            Log.d("BENZNEST LOG", "savedInstanceState = NULL");
            loadNodeListData();
        } else {
            mRecyclerView.scrollToPosition(savedInstanceState.getInt(MyConstants.KEY_POSITION_RECYCLERVIEW));
            page = savedInstanceState.getInt(MyConstants.KEY_PAGE);
        }

        if (mode == MODE_FAVORITE) {
            getActivity().setTitle("Favorite");
        } else if (mode == MODE_NODE_CACHE) {
            getActivity().setTitle("Saved topic");
        } else if (mode == MODE_NODE_HISTORY) {
            getActivity().setTitle("Recent viewed");
        } else if (mode == MODE_INTERVIEW) {
            getActivity().setTitle("Interview");
        } else if (mode == MODE_WORKPLACE) {
            getActivity().setTitle("Workplace");
        }

        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(MyConstants.KEY_POSITION_RECYCLERVIEW, layoutManager.findFirstVisibleItemPosition()); // get current recycle view position here.
        outState.putInt(MyConstants.KEY_PAGE, page);
        super.onSaveInstanceState(outState);
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
                    if (mode == MODE_ALL_NODE ||
                            mode == MODE_INTERVIEW ||
                            mode == MODE_JOB ||
                            mode == MODE_FORUM ||
                            mode == MODE_TAG) {
                        page++;
                        loadNodeListData();
                    }
                }
            }
        });

        adapter = new MyNodeRecyclerViewAdapter();
        adapter.setOnNodeListener(new MyRowNodeTitleView.OnNodeListener() {
            @Override
            public void onTitleClicked(BlognoneNodeDao node) {
                selectNode(node);
            }

            @Override
            public void onTagClicked(BlognoneNodeDao node, String tag) {
                Fragment fragment = NodeListFragment.newInstance(tag);
                ((MainActivity) getActivity()).setContent(fragment, "");
            }
        });

        adapter.setData(listNode);
        adapter.setEnableShowDetail(MySetting.isSettingShowDetailOfTopic(getContext()));
        mRecyclerView.setAdapter(adapter);

        tvNoData = (TextView) v.findViewById(R.id.tv_no_data);
        tvNoData.setVisibility(View.GONE);
        appBarSearch = (AppBarLayout) v.findViewById(R.id.app_bar_search);
        tvTotal = (TextView) v.findViewById(R.id.tv_keyword);
        edtSearch = (EditText) v.findViewById(R.id.edt_search);
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchKeyword = charSequence.toString().trim();
                loadNodeListData();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        if (mode == MODE_FAVORITE || mode == MODE_NODE_CACHE || mode == MODE_NODE_HISTORY) {
            appBarSearch.setVisibility(View.VISIBLE);
        } else {
            appBarSearch.setVisibility(View.GONE);
        }


        if (MyUtils.isTablet()) {
            contentNodeContentContainer = (FrameLayout) v.findViewById(R.id.content_node_content_container);
        }
    }

    private void selectNode(BlognoneNodeDao node) {
        if (MyUtils.isTablet()) {
            if (mode == MODE_NODE_CACHE) {
                Fragment fragment = NodeFragment.newInstance(node.getId(), true);
                getChildFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_node_content_container, fragment)
                        .commit();
            } else {
                Fragment fragment = NodeFragment.newInstance(node.getId());
                getChildFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_node_content_container, fragment)
                        .commit();
            }
        } else {
            if (mode == MODE_NODE_CACHE) {
                Fragment fragment = NodeFragment.newInstance(node.getId(), true);
                ((MainActivity) getActivity()).setContent(fragment, "");
            } else {
                Fragment fragment = NodeFragment.newInstance(node.getId());
                ((MainActivity) getActivity()).setContent(fragment, "");
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.node_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_refresh) {
            MyUtils.vibrate();
            page = 0;
            listNode = new ArrayList<>();
            update();
            loadNodeListData();
        }
        return true;
    }

    private void loadNodeListData() {
        if (mode != MODE_FAVORITE && mode != MODE_NODE_CACHE && mode != MODE_NODE_HISTORY) {
            if (page == 0 && !listNode.isEmpty()) {
                return;
            }
        }

        loadingShow();
        if (mode == MODE_ALL_NODE) {
            MyBlognoneManager.loadNodeList(page, new MyBlognoneManager.OnLoadNodeListListener() {
                @Override
                public void onLoaded(ArrayList<BlognoneNodeDao> list) {
                    if (page == 0) {
                        MyCache.setNodeList(getContext(), list);
                        MyWidgetManager.updateWidget();
                    }

                    listNode.addAll(list);
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
        } else if (mode == MODE_FEATURE) {
            MyBlognoneManager.loadNodeFeature(new MyBlognoneManager.OnLoadNodeListListener() {
                @Override
                public void onLoaded(ArrayList<BlognoneNodeDao> list) {
                    listNode = list;
                    update();
                    loadingFinish();
                }

                @Override
                public void onFail() {
                    loadingFinish();
                }
            });
        } else if (mode == MODE_INTERVIEW) {
            MyBlognoneManager.loadNodeInterview(page, new MyBlognoneManager.OnLoadNodeListListener() {
                @Override
                public void onLoaded(ArrayList<BlognoneNodeDao> list) {
                    listNode.addAll(list);
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
        } else if (mode == MODE_JOB) {
            MyBlognoneManager.loadNodeForum(MyBlognoneForum.ENDPOINT_JOB, page, new MyBlognoneManager.OnLoadNodeListListener() {
                @Override
                public void onLoaded(ArrayList<BlognoneNodeDao> list) {
                    listNode.addAll(list);
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
        } else if (mode == MODE_WORKPLACE) {
            MyBlognoneManager.loadNodeWorkplace(new MyBlognoneManager.OnLoadNodeListListener() {
                @Override
                public void onLoaded(ArrayList<BlognoneNodeDao> list) {
                    listNode = list;
                    update();
                    loadingFinish();
                }

                @Override
                public void onFail() {
                    loadingFinish();
                }
            });
        } else if (mode == MODE_FORUM) {
            String endPoint = forumDao.getEndpoint();
            MyBlognoneManager.loadNodeForum(endPoint, page, new MyBlognoneManager.OnLoadNodeListListener() {
                @Override
                public void onLoaded(ArrayList<BlognoneNodeDao> list) {
                    listNode.addAll(list);
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
        } else if (mode == MODE_TAG) {
            String endPoint = MyBlognoneManager.convertToEndPoint(tag);
            MyBlognoneManager.loadNodeTag(endPoint, page, new MyBlognoneManager.OnLoadNodeListListener() {
                @Override
                public void onLoaded(ArrayList<BlognoneNodeDao> list) {
                    listNode.addAll(list);
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
        } else if (mode == MODE_FAVORITE) {
            listNode = BlognoneFavoriteTopicDatabase.getAllOnlyNode(searchKeyword);
            update();
            loadingFinish();
        } else if (mode == MODE_NODE_CACHE) {
            listNode = BlognoneTopicCacheDatabase.getAllOnlyNode(searchKeyword);
            update();
            loadingFinish();
        } else if (mode == MODE_NODE_HISTORY) {
            listNode = BlognoneHistoryTopicDatabase.getAllOnlyNode(searchKeyword);
            update();
            loadingFinish();
        } else if (mode == MODE_NODE_UPCOMING) {
            MyBlognoneManager.loadNodeUpcoming(new MyBlognoneManager.OnLoadNodeListListener() {
                @Override
                public void onLoaded(ArrayList<BlognoneNodeDao> list) {
                    listNode = list;
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

        if (mode == MODE_NODE_HISTORY || mode == MODE_NODE_CACHE || mode == MODE_FAVORITE) {
            tvNoData.setText("No data.");
        } else if (mode == MODE_NODE_UPCOMING) {
            tvNoData.setText("No upcoming data.");
        } else {
            tvNoData.setText("Unable to load data.");
        }

        if (tvNoData != null) {
            if (listNode.isEmpty()) {
                tvNoData.setVisibility(View.VISIBLE);
            } else {
                tvNoData.setVisibility(View.GONE);
            }
        }
    }

    private void update() {
        if (isAdded()) {
            if (MyUtils.isTablet() && indexSelected == -1) {
                if (listNode.size() > 0) {
                    selectNode(listNode.get(0));
                    indexSelected = 0;
                }
            }

            adapter.setData(listNode);
            adapter.setEnableShowDetail(MySetting.isSettingShowDetailOfTopic(getContext()));
            adapter.notifyDataSetChanged();

            tvTotal.setText(listNode.size() + " " + getResources().getQuantityString(R.plurals.items, listNode.size()));
        }
    }
}
