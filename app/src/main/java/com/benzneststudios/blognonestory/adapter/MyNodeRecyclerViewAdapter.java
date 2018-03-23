package com.benzneststudios.blognonestory.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.benzneststudios.blognonestory.dao.BlognoneNodeDao;
import com.benzneststudios.blognonestory.view.MyRowNodeTitleView;

import java.util.ArrayList;

/**
 * Created by benznest on 16-Mar-18.
 */

public class MyNodeRecyclerViewAdapter extends RecyclerView.Adapter<MyNodeRecyclerViewAdapter.MyViewHolder> {

    private ArrayList<BlognoneNodeDao> listData = new ArrayList<>();
    private MyRowNodeTitleView.OnNodeListener mOnNodeListener; ;

    public void setData(ArrayList<BlognoneNodeDao> listData) {
        this.listData = listData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View row = new MyRowNodeTitleView(viewGroup.getContext());
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, final int position) {
        viewHolder.row.setNode(listData.get(position));
        viewHolder.row.setOnNodeListener(mOnNodeListener);

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void setOnNodeListener(MyRowNodeTitleView.OnNodeListener onNodeListener) {
        mOnNodeListener = onNodeListener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private MyRowNodeTitleView row;

        private MyViewHolder(View v) {
            super(v);
            this.row = (MyRowNodeTitleView) v;

        }
    }

}
