package com.panuwatjan.blognonestory.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.panuwatjan.blognonestory.MyGlide;
import com.panuwatjan.blognonestory.R;
import com.panuwatjan.blognonestory.dao.jobs.StoryBlockDao;

/**
 * Created by benznest on 06-Sep-18.
 */

public class MyStoryBlockView extends FrameLayout {
    private StoryBlockDao mStoryBlockDao;
    private TextView tvStoryTitle;
    private TextView tvStoryDescription;
    private ImageView imgStory;

    public MyStoryBlockView(@NonNull Context context) {
        super(context);
        init();
    }

    public MyStoryBlockView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyStoryBlockView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyStoryBlockView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.row_story_block, this);
        initView();
    }

    private void initView() {

        tvStoryTitle = (TextView) findViewById(R.id.tv_story_title);
        tvStoryDescription = (TextView) findViewById(R.id.tv_story_description);
        imgStory = (ImageView) findViewById(R.id.img_story);
    }

    public void setStoryBlockDao(StoryBlockDao storyBlockDao) {
        mStoryBlockDao = storyBlockDao;
        tvStoryTitle.setText(mStoryBlockDao.getTitle());
        tvStoryDescription.setText(mStoryBlockDao.getContent());
        MyGlide.load(getContext(), imgStory, mStoryBlockDao.getImage());
    }
}
