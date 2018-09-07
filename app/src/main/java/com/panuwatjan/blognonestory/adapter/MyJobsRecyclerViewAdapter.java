package com.panuwatjan.blognonestory.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.panuwatjan.blognonestory.dao.jobs.JobsDao;
import com.panuwatjan.blognonestory.view.MyRowJobsTitleView;

import java.util.ArrayList;

/**
 * Created by benznest on 16-Mar-18.
 */

public class MyJobsRecyclerViewAdapter extends RecyclerView.Adapter<MyJobsRecyclerViewAdapter.MyViewHolder> {

    private ArrayList<JobsDao> listData = new ArrayList<>();
    private MyRowJobsTitleView.OnJobListener mOnJobListener;
    private boolean enableShowDetail;


    public void setData(ArrayList<JobsDao> listData) {
        this.listData = listData;
    }

    public void setEnableShowDetail(boolean enableShowDetail) {
        this.enableShowDetail = enableShowDetail;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View row = new MyRowJobsTitleView(viewGroup.getContext());
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, final int position) {
        viewHolder.row.setJob(listData.get(position));
        viewHolder.row.setOnJobsListener(mOnJobListener);
        viewHolder.row.setEnableTopicDetail(enableShowDetail);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void setOnJobListener( MyRowJobsTitleView.OnJobListener onJobListener) {
        mOnJobListener = onJobListener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private MyRowJobsTitleView row;

        private MyViewHolder(View v) {
            super(v);
            this.row = (MyRowJobsTitleView) v;

        }
    }

}
