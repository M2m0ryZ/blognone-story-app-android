package com.benznestdeveloper.blognonestory.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.benznestdeveloper.blognonestory.BuildConfig;
import com.benznestdeveloper.blognonestory.MyConstants;
import com.benznestdeveloper.blognonestory.MyUtils;
import com.benznestdeveloper.blognonestory.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {

    private TextView tvVersion;
    private Button btnOnPlayStore;
    private Button btnOnFacebook;

    public static AboutFragment newInstance() {
        Bundle args = new Bundle();

        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(R.string.about);
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        initView(view);
        return view;
    }

    private void initView(View v) {
        tvVersion = (TextView) v.findViewById(R.id.tv_version);
        tvVersion.setText("Version " + BuildConfig.VERSION_NAME);

        btnOnPlayStore = (Button) v.findViewById(R.id.btn_on_play_store);
        btnOnPlayStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.openPlayStore(getContext());
            }
        });

        btnOnFacebook = (Button) v.findViewById(R.id.btn_on_facebook);
        btnOnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.openFacebookGroupURL(getContext(), MyConstants.FACEBOOK_GROUP_ID);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.empty, menu);
    }


}
