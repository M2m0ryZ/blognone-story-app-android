package com.benznestdeveloper.blognonestory.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.benznestdeveloper.blognonestory.fragment.ComingSoonFragment;
import com.benznestdeveloper.blognonestory.fragment.NodeBodyFragment;
import com.benznestdeveloper.blognonestory.fragment.NodeCommentFragment;

/**
 * Created by benznest on 16-Mar-18.
 */

public class MyNodeViewPagerAdapter extends FragmentStatePagerAdapter {
    private boolean cacheMode = false;
    private int nodeId = 100702;

    public MyNodeViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public MyNodeViewPagerAdapter(FragmentManager fm, int nodeId, boolean cacheMode) {
        super(fm);
        this.cacheMode = cacheMode;
        this.nodeId = nodeId;
    }

    public void setCacheMode(boolean cacheMode) {
        this.cacheMode = cacheMode;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return NodeBodyFragment.newInstance(nodeId, cacheMode);
        } else if (position == 1) {
            return NodeCommentFragment.newInstance();
        }
        return ComingSoonFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Blognone";
        } else if (position == 1) {
            return "Comments";
        }
        return "";
    }
}
