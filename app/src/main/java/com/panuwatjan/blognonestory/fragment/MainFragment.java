package com.panuwatjan.blognonestory.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.panuwatjan.blognonestory.R;
import com.panuwatjan.blognonestory.adapter.MyMainViewPagerAdapter;
import com.panuwatjan.blognonestory.view.MyBlognoneMainTabView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private MyBlognoneMainTabView tab;
    private ViewPager viewpager;
    private MyMainViewPagerAdapter adapter;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(R.string.app_name);
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        tab = (MyBlognoneMainTabView) v.findViewById(R.id.tab);
        viewpager = (ViewPager) v.findViewById(R.id.viewpager);
        viewpager.setOffscreenPageLimit(3);

        adapter = new MyMainViewPagerAdapter(getChildFragmentManager());
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
        viewpager.setOffscreenPageLimit(5);
        tab.setViewPager(viewpager);

        tab.setTabSelected(0);
        (getActivity()).setTitle(adapter.getPageTitle(0));
    }


}
