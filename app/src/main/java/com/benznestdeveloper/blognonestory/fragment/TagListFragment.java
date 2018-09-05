package com.benznestdeveloper.blognonestory.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.benznestdeveloper.blognonestory.BlognoneTags;
import com.benznestdeveloper.blognonestory.MyConstants;
import com.benznestdeveloper.blognonestory.MyUtils;
import com.benznestdeveloper.blognonestory.R;
import com.benznestdeveloper.blognonestory.activity.MainActivity;
import com.benznestdeveloper.blognonestory.adapter.MyTagRecyclerViewAdapter;
import com.benznestdeveloper.blognonestory.database.BlognoneTagsDatabase;
import com.benznestdeveloper.blognonestory.view.MyRowTagView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TagListFragment extends Fragment {

    public static int MODE_TAG_ALL = 0;
    public static int MODE_TAG_FAVORITE = 1;

    private String searchKeyword = "";
    private int mode = 0;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;
    private MyTagRecyclerViewAdapter adapter;
    private ArrayList<BlognoneTags> listTag = new ArrayList<>();
    private TextView tvNoData;
    private TextView tvTotal;
    private EditText edtSearch;
    private FloatingActionButton fabAddTag;

    public static TagListFragment newInstance() {
        Bundle args = new Bundle();

        TagListFragment fragment = new TagListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static TagListFragment newInstance(int mode) {
        Bundle args = new Bundle();
        args.putInt(MyConstants.KEY_MODE_TAG, mode);
        TagListFragment fragment = new TagListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public TagListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.fragment_tag_list, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            mode = bundle.getInt(MyConstants.KEY_MODE_TAG, 0);
        }

        if (mode == MODE_TAG_ALL) {
            getActivity().setTitle("Tags");
        }

        initView(v);
        update();
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.empty, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    private void update() {

        if (mode == MODE_TAG_ALL) {
            adapter.setEnableFavoriteMode(true);
            listTag = BlognoneTagsDatabase.getAll(searchKeyword);
        } else if (mode == MODE_TAG_FAVORITE) {
            adapter.setEnableFavoriteMode(false);
            listTag = BlognoneTagsDatabase.getAllFavorite(true, searchKeyword);
        }

        adapter.setData(listTag);
        adapter.notifyDataSetChanged();

        tvTotal.setText(listTag.size() + " " + getResources().getQuantityString(R.plurals.items, listTag.size()));

        if (tvNoData != null) {
            if (listTag.isEmpty()) {
                tvNoData.setVisibility(View.VISIBLE);
            } else {
                tvNoData.setVisibility(View.GONE);
            }
        }
    }

    private void initView(View v) {
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(layoutManager);

        adapter = new MyTagRecyclerViewAdapter();
        adapter.setOnRowTagListener(new MyRowTagView.OnRowTagListener() {
            @Override
            public void onFavoriteTagChanged(BlognoneTags blognoneTags, boolean favorite) {
                MyUtils.vibrate();
                BlognoneTagsDatabase.update(blognoneTags);
                update();
            }

            @Override
            public void onTagSelected(BlognoneTags blognoneTags) {
                MyUtils.vibrate();
                Fragment fragment = NodeListFragment.newInstance(blognoneTags.getName());
                ((MainActivity) getActivity()).setContent(fragment, "");
            }

        });

        adapter.setData(listTag);
        mRecyclerView.setAdapter(adapter);

        tvNoData = (TextView) v.findViewById(R.id.tv_no_data);
        tvTotal = (TextView) v.findViewById(R.id.tv_keyword);
        edtSearch = (EditText) v.findViewById(R.id.edt_search);
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchKeyword = charSequence.toString().trim();
                update();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        fabAddTag = (FloatingActionButton) v.findViewById(R.id.fab_add_tag);
        fabAddTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = TagListFragment.newInstance(TagListFragment.MODE_TAG_ALL);
                ((MainActivity) getActivity()).setContent(fragment, "");
            }
        });

        if (mode == MODE_TAG_ALL) {
            fabAddTag.setVisibility(View.GONE);
        }
    }
}
