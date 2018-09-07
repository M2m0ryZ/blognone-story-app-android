package com.panuwatjan.blognonestory.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.panuwatjan.blognonestory.fragment.ComingSoonFragment;
import com.panuwatjan.blognonestory.fragment.NodeListFragment;
import com.panuwatjan.blognonestory.fragment.TagListFragment;

/**
 * Created by benznest on 16-Mar-18.
 */

public class MyMainViewPagerAdapter extends FragmentStatePagerAdapter {

    public MyMainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return NodeListFragment.newInstance(NodeListFragment.MODE_ALL_NODE);
        } else if (position == 1) {
//            return ComingSoonFragment.newInstance();
            return NodeListFragment.newInstance(NodeListFragment.MODE_FEATURE);
        } else if (position == 2) {
//            return ComingSoonFragment.newInstance();
            return TagListFragment.newInstance(TagListFragment.MODE_TAG_FAVORITE);
        } else if (position == 3) {
            return NodeListFragment.newInstance(NodeListFragment.MODE_NODE_UPCOMING);
        }
        return ComingSoonFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Blognone";
        } else if (position == 1) {
            return "Features";
        } else if (position == 2) {
            return "Your tags";
        } else if (position == 3) {
            return "Jobs";
        } else if (position == 4) {
            return "Upcoming";
        }
        return "";
    }
}
