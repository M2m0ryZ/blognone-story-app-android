package com.benzneststudios.blognonestory.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.benzneststudios.blognonestory.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComingSoonFragment extends Fragment {


    public static ComingSoonFragment newInstance() {
        Bundle args = new Bundle();

        ComingSoonFragment fragment = new ComingSoonFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public ComingSoonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coming_soon, container, false);
    }

}
