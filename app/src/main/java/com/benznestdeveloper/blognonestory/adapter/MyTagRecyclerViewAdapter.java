package com.benznestdeveloper.blognonestory.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.benznestdeveloper.blognonestory.BlognoneTags;
import com.benznestdeveloper.blognonestory.view.MyRowTagView;

import java.util.ArrayList;

/**
 * Created by benznest on 16-Mar-18.
 */

public class MyTagRecyclerViewAdapter extends RecyclerView.Adapter<MyTagRecyclerViewAdapter.MyViewHolder> {

    private boolean enableFavoriteMode = true;
    private ArrayList<BlognoneTags> listData = new ArrayList<>();
    private MyRowTagView.OnRowTagListener mOnRowTagListener;

    public void setData(ArrayList<BlognoneTags> listData) {
        this.listData = listData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View row = new MyRowTagView(viewGroup.getContext());
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, final int position) {
        viewHolder.row.setBlognoneTags(listData.get(position));
        viewHolder.row.setOnRowTagListener(mOnRowTagListener);
        viewHolder.row.setEnableFavoriteMode(enableFavoriteMode);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private MyRowTagView row;

        private MyViewHolder(View v) {
            super(v);
            this.row = (MyRowTagView) v;

        }
    }

    public void setOnRowTagListener(MyRowTagView.OnRowTagListener onRowTagListener) {
        mOnRowTagListener = onRowTagListener;
    }

    public void setEnableFavoriteMode(boolean enableFavoriteMode) {
        this.enableFavoriteMode = enableFavoriteMode;
    }

}
