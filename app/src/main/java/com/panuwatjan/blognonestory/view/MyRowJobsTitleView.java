package com.panuwatjan.blognonestory.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.panuwatjan.blognonestory.MyCalendarUtils;
import com.panuwatjan.blognonestory.MyGlide;
import com.panuwatjan.blognonestory.MyUtils;
import com.panuwatjan.blognonestory.R;
import com.panuwatjan.blognonestory.dao.jobs.JobsDao;

import java.util.ArrayList;

/**
 * Created by benznest on 16-Mar-18.
 */

public class MyRowJobsTitleView extends FrameLayout {
    private JobsDao mJobsDao;

    private OnJobListener mOnJobsListener;
    private LinearLayout llDetailContainer;
    private ImageView imgNode;
    private TextView tvTitle;
    private TextView tvCompany;
    private TextView tvDistrict;
    private TextView tvSalary;
    private LinearLayout llTagsContainer;
    private TextView tvLastUpdate;
    private LinearLayout llContainerFeature;

    public MyRowJobsTitleView(@NonNull Context context) {
        super(context);
        init();
    }

    public MyRowJobsTitleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyRowJobsTitleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyRowJobsTitleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.row_jobs_title, this);
        initView();
    }

    private void initView() {
        llDetailContainer = (LinearLayout) findViewById(R.id.ll_detail_container);
        imgNode = (ImageView) findViewById(R.id.img_node);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvCompany = (TextView) findViewById(R.id.tv_company);
        tvDistrict = (TextView) findViewById(R.id.tv_district);
        tvSalary = (TextView) findViewById(R.id.tv_salary);
        llTagsContainer = (LinearLayout) findViewById(R.id.ll_tags_container);
        tvLastUpdate = (TextView) findViewById(R.id.tv_last_update);
        llContainerFeature = (LinearLayout) findViewById(R.id.ll_container_feature);
    }

    public void setJob(final JobsDao job) {
        mJobsDao = job;

        tvTitle.setText(job.getTitle());
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnJobsListener != null) {
                    mOnJobsListener.onJobSelected(mJobsDao);
                }
            }
        });

        if (job.getCompany().getLogo() == null) {
            imgNode.setVisibility(GONE);
        } else {
            imgNode.setVisibility(VISIBLE);
        }

        if (job.getPremium()) {
            llContainerFeature.setVisibility(VISIBLE);
        } else {
            llContainerFeature.setVisibility(GONE);
        }

        tvCompany.setText(job.getCompany().getNameEn());

        tvDistrict.setText(job.getDistrict() + " " + job.getProvince());

        tvSalary.setText(MyUtils.comma(job.getSalaryMin()) + "-" + MyUtils.comma(job.getSalaryMax()) + " THB");

        MyGlide.load(getContext(), imgNode, job.getCompany().getLogo());

        tvLastUpdate.setText("" + MyCalendarUtils.getDifferenceTimeString(job.getUpdated(), true));

//        List<BlognoneHistoryTopic> listHistory = BlognoneHistoryTopicDatabase.get(job.getId());
//        if (job.getCountComment() == 0 || !MySetting.isSettingShowNewComment(getContext())) {
//            tvNewCommentCount.setVisibility(GONE);
//        } else {
//            if (listHistory.isEmpty()) {
//                tvNewCommentCount.setVisibility(VISIBLE);
//                tvNewCommentCount.setText(job.getCountComment() + " " + getResources().getQuantityString(R.plurals.new_comments, job.getCountComment()));
//
//            } else {
//                BlognoneHistoryTopic topic = listHistory.get(0);
//                int countCommentHistory = topic.getCountComment();
//                int newComment = job.getCountComment() - countCommentHistory;
//                if (newComment > 0) {
//                    tvNewCommentCount.setVisibility(VISIBLE);
//                    tvNewCommentCount.setText(newComment + " " + getResources().getQuantityString(R.plurals.new_comments, newComment));
//                } else {
//                    tvNewCommentCount.setVisibility(GONE);
//                }
//            }
//        }


        llTagsContainer.removeAllViews();
        ArrayList<String> lisTag = job.getSkills();
        for (final String tag : lisTag) {
            MySkillTagView myTagView = new MySkillTagView(getContext());
            myTagView.setSkillName(tag);
            myTagView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnJobsListener != null) {
                        mOnJobsListener.onSkillSelected(mJobsDao, tag);
                    }
                }
            });
            myTagView.setStickyMode(job.getPremium());
            llTagsContainer.addView(myTagView);
        }


//        if (job.isSticky()) {
//            llContainer.setBackgroundResource(R.color.colorYellowDark2);
//            llContainerBody.setBackgroundResource(R.color.colorBackgroundSticky);
//            llContainerBodyBorder.setBackgroundResource(R.color.colorYellowDark2);
//
//        } else if (job.isWorkplace()) {
//            llContainer.setBackgroundResource(R.color.colorWorkplace);
//            llContainerBody.setBackgroundResource(R.color.colorBackgroundWorkplace);
//            llContainerBodyBorder.setBackgroundResource(R.color.colorWorkplace);
//
//        } else {
//            llContainer.setBackgroundResource(R.color.colorPrimary);
//            llContainerBody.setBackgroundResource(R.color.colorWhite);
//            llContainerBodyBorder.setBackgroundResource(R.color.colorWhite);
//        }

//        if (MySetting.isSettingDisplayTabOption(getContext())) {
//            boolean viewed = BlognoneHistoryTopicDatabase.isViewed(job.getId());
//            boolean favorite = BlognoneFavoriteTopicDatabase.isFavorite(job.getId());
//            boolean saved = BlognoneTopicCacheDatabase.isCache(job.getId());
//
//            if (!viewed && !favorite && !saved) {
//                tabOptionDetailView.setVisibility(GONE);
//            } else {
//                tabOptionDetailView.setVisibility(VISIBLE);
//            }
//
//            tabOptionDetailView.setViewed(viewed);
//            tabOptionDetailView.setFavorite(favorite);
//            tabOptionDetailView.setSaved(saved);
//        } else {
//            tabOptionDetailView.setVisibility(GONE);
//        }
    }

    public void setOnJobsListener(OnJobListener onJobsListener) {
        mOnJobsListener = onJobsListener;
    }

    public void setEnableTopicDetail(boolean b) {
        if (b) {
            llDetailContainer.setVisibility(VISIBLE);
        } else {
            llDetailContainer.setVisibility(GONE);
        }
    }

    public interface OnJobListener {
        void onJobSelected(JobsDao jobs);

        void onSkillSelected(JobsDao jobs, String skill);
    }
}
