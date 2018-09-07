package com.panuwatjan.blognonestory.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.panuwatjan.blognonestory.MyConstants;
import com.panuwatjan.blognonestory.MySetting;
import com.panuwatjan.blognonestory.R;
import com.panuwatjan.blognonestory.adapter.MyNodeViewPagerAdapter;
import com.panuwatjan.blognonestory.view.MyBlognoneNodeTabView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NodeFragment extends Fragment {
    private static NodeFragment fragment;
    private boolean cacheMode = false;
    private int nodeId = 100702;
    private MyBlognoneNodeTabView tab;
    private ViewPager viewpager;
    private MyNodeViewPagerAdapter adapter;

    public NodeFragment() {
        // Required empty public constructor
    }

    public static NodeFragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt(MyConstants.KEY_NODE_ID, id);
        fragment = new NodeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static NodeFragment newInstance(int id, boolean cacheMode) {
        Bundle args = new Bundle();
        args.putInt(MyConstants.KEY_NODE_ID, id);
        args.putBoolean(MyConstants.KEY_NODE_CACHE_MODE, cacheMode);
        fragment = new NodeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static NodeFragment getFragment() {
        return fragment;
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

        if (MySetting.isSettingEnableTopicCommentTab(getContext())) {
            tab.setVisibility(View.VISIBLE);
        } else {
            tab.setVisibility(View.GONE);
        }
        return v;
    }

    private void initView(View v) {
        tab = (MyBlognoneNodeTabView) v.findViewById(R.id.tab);
        viewpager = (ViewPager) v.findViewById(R.id.viewpager);
        viewpager.setOffscreenPageLimit(2);

        adapter = new MyNodeViewPagerAdapter(getChildFragmentManager(), nodeId, cacheMode);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                (getActivity()).setTitle(adapter.getPageTitle(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewpager.setAdapter(adapter);
        tab.setViewPager(viewpager);

        tab.setTabSelected(0);
        (getActivity()).setTitle(adapter.getPageTitle(0));
    }

    public void setCommentCountOnTab(int count) {
        tab.setCommentCount(count);
    }


}
