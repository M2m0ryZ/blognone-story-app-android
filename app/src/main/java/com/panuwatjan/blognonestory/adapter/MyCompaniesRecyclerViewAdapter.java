package com.panuwatjan.blognonestory.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.panuwatjan.blognonestory.dao.jobs.CompanyDao;
import com.panuwatjan.blognonestory.view.MyRowCompanyTitleView;

import java.util.ArrayList;

/**
 * Created by benznest on 16-Mar-18.
 */

public class MyCompaniesRecyclerViewAdapter extends RecyclerView.Adapter<MyCompaniesRecyclerViewAdapter.MyViewHolder> {

    private ArrayList<CompanyDao> listData = new ArrayList<>();
    private MyRowCompanyTitleView.OnCompanyListener mOnCompanyListener;
    private boolean enableShowDetail;


    public void setData(ArrayList<CompanyDao> listData) {
        this.listData = listData;
    }

    public void setEnableShowDetail(boolean enableShowDetail) {
        this.enableShowDetail = enableShowDetail;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View row = new MyRowCompanyTitleView(viewGroup.getContext());
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, final int position) {
        viewHolder.row.setCompany(listData.get(position));
        viewHolder.row.setOnCompanyListener(mOnCompanyListener);
//        viewHolder.row.setEnableTopicDetail(enableShowDetail);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void setOnCompanyListener(MyRowCompanyTitleView.OnCompanyListener onCompanyListener) {
        mOnCompanyListener = onCompanyListener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private MyRowCompanyTitleView row;

        private MyViewHolder(View v) {
            super(v);
            this.row = (MyRowCompanyTitleView) v;

        }
    }

}
