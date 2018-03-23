package com.benzneststudios.blognonestory.view;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.benzneststudios.blognonestory.BlognoneHistoryTopic;
import com.benzneststudios.blognonestory.MyGlide;
import com.benzneststudios.blognonestory.MySetting;
import com.benzneststudios.blognonestory.MyUtils;
import com.benzneststudios.blognonestory.R;
import com.benzneststudios.blognonestory.dao.BlognoneNodeDao;
import com.benzneststudios.blognonestory.database.BlognoneFavoriteTopicDatabase;
import com.benzneststudios.blognonestory.database.BlognoneHistoryTopicDatabase;
import com.benzneststudios.blognonestory.database.BlognoneTopicCacheDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benznest on 16-Mar-18.
 */

public class MyRowNodeTitleView extends FrameLayout {
    private BlognoneNodeDao mBlognoneNodeDao;
    private TextView tvTitle;
    private TextView tvWriter;
    private ImageView imgNode;
    private TextView tvDescription;
    private TextView tvCommentCount;
    private LinearLayout llContainer;
    private LinearLayout llContainerBody;
    private FrameLayout llContainerBodyBorder;
    private TextView tvDescription2;
    private LinearLayout llTagsContainer;
    private OnNodeListener mOnNodeListener;
    private MyNodeOptionDetailTabViewView tabOptionDetailView;
    private TextView tvNewCommentCount;

    public MyRowNodeTitleView(@NonNull Context context) {
        super(context);
        init();
    }

    public MyRowNodeTitleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyRowNodeTitleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyRowNodeTitleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.row_node_title, this);
        initView();
    }

    private void initView() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvWriter = (TextView) findViewById(R.id.tv_writer);
        imgNode = (ImageView) findViewById(R.id.img_node);
        tvDescription = (TextView) findViewById(R.id.tv_description);
        tvCommentCount = (TextView) findViewById(R.id.tv_comment_count);
        tvNewCommentCount = (TextView) findViewById(R.id.tv_new_comment_count);
        llContainer = (LinearLayout) findViewById(R.id.ll_container);
        llContainerBody = (LinearLayout) findViewById(R.id.ll_container_body);
        llContainerBodyBorder = (FrameLayout) findViewById(R.id.ll_container_body_border);
        tvDescription2 = (TextView) findViewById(R.id.tv_description_2);
        llTagsContainer = (LinearLayout) findViewById(R.id.ll_tags_container);
        tabOptionDetailView = (MyNodeOptionDetailTabViewView) findViewById(R.id.tab_option_detail_view);
    }

    public void setNode(final BlognoneNodeDao node) {
        mBlognoneNodeDao = node;

        tvTitle.setText(node.getTitle());
        tvTitle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnNodeListener != null) {
                    mOnNodeListener.onTitleClicked(mBlognoneNodeDao);
                }
            }
        });

        if (node.getUrlImage() == null) {
            imgNode.setVisibility(GONE);
        } else {
            imgNode.setVisibility(VISIBLE);
        }

        tvDescription.setText(node.getInfo());

        tvWriter.setText("By " + node.getWriter() + " " + node.getDate());

        String text = tvDescription.getText().toString();
        Paint textPaint = tvDescription.getPaint();

        Rect textRect = new Rect();
        textPaint.getTextBounds(text, 0, text.length(), textRect);
        int textHeight = textRect.height();
        final int maxline = Math.round(MyUtils.convertDpToPixel(100, getContext()) / textHeight);
        tvDescription.setMaxLines(maxline);

        MyGlide.load(getContext(), imgNode, node.getUrlImage());

        tvCommentCount.setText(node.getCountComment() + " " + getResources().getQuantityString(R.plurals.comments, node.getCountComment()));

        List<BlognoneHistoryTopic> listHistory = BlognoneHistoryTopicDatabase.get(node.getId());
        if (node.getCountComment() == 0 || !MySetting.isSettingShowNewComment(getContext())) {
            tvNewCommentCount.setVisibility(GONE);
        } else {
            if (listHistory.isEmpty()) {
                tvNewCommentCount.setVisibility(VISIBLE);
                tvNewCommentCount.setText(node.getCountComment() + " " + getResources().getQuantityString(R.plurals.new_comments, node.getCountComment()));

            } else {
                BlognoneHistoryTopic topic = listHistory.get(0);
                int countCommentHistory = topic.getCountComment();
                int newComment = node.getCountComment() - countCommentHistory;
                if (newComment > 0) {
                    tvNewCommentCount.setVisibility(VISIBLE);
                    tvNewCommentCount.setText(newComment + " " + getResources().getQuantityString(R.plurals.new_comments, newComment));
                } else {
                    tvNewCommentCount.setVisibility(GONE);
                }
            }
        }

        tvDescription2.setText("");

        ViewTreeObserver vto = tvDescription.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Layout layout = tvDescription.getLayout();
                if (layout != null && node.getInfo() != null) {
                    try {
                        int offsetEndMaxLine = layout.getLineEnd(maxline - 1);
                        String str2 = node.getInfo().substring(offsetEndMaxLine);
                        tvDescription2.setText(str2);
                    } catch (Exception e) {
                        tvDescription2.setText("");
                    }
                }
            }
        });

        llTagsContainer.removeAllViews();
        ArrayList<String> lisTag = node.getTags();
        for (final String tag : lisTag) {
            MyTagView myTagView = new MyTagView(getContext());
            myTagView.setTagName(tag);
            myTagView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnNodeListener != null) {
                        mOnNodeListener.onTagClicked(mBlognoneNodeDao, tag);
                    }
                }
            });
            myTagView.setStickyMode(node.isSticky());
            llTagsContainer.addView(myTagView);
        }


        if (node.getInfo() != null) {
            tvDescription.setVisibility(VISIBLE);
            tvDescription2.setVisibility(VISIBLE);
        } else {
            tvDescription.setVisibility(GONE);
            tvDescription2.setVisibility(GONE);
        }


        if (node.isSticky()) {
            llContainer.setBackgroundResource(R.color.colorYellowDark2);
            llContainerBody.setBackgroundResource(R.color.colorBackgroundSticky);
            llContainerBodyBorder.setBackgroundResource(R.color.colorYellowDark2);

        } else if (node.isWorkplace()) {
            llContainer.setBackgroundResource(R.color.colorWorkplace);
            llContainerBody.setBackgroundResource(R.color.colorBackgroundWorkplace);
            llContainerBodyBorder.setBackgroundResource(R.color.colorWorkplace);

        } else {
            llContainer.setBackgroundResource(R.color.colorPrimary);
            llContainerBody.setBackgroundResource(R.color.colorWhite);
            llContainerBodyBorder.setBackgroundResource(R.color.colorWhite);
        }

        if (MySetting.isSettingDisplayTabOption(getContext())) {
            boolean viewed = BlognoneHistoryTopicDatabase.isViewed(node.getId());
            boolean favorite = BlognoneFavoriteTopicDatabase.isFavorite(node.getId());
            boolean saved = BlognoneTopicCacheDatabase.isCache(node.getId());

            if (!viewed && !favorite && !saved) {
                tabOptionDetailView.setVisibility(GONE);
            } else {
                tabOptionDetailView.setVisibility(VISIBLE);
            }

            tabOptionDetailView.setViewed(viewed);
            tabOptionDetailView.setFavorite(favorite);
            tabOptionDetailView.setSaved(saved);
        } else {
            tabOptionDetailView.setVisibility(GONE);
        }
    }

    public void setOnNodeListener(OnNodeListener onNodeListener) {
        mOnNodeListener = onNodeListener;
    }

    public interface OnNodeListener {
        void onTitleClicked(BlognoneNodeDao node);

        void onTagClicked(BlognoneNodeDao node, String tag);
    }
}
