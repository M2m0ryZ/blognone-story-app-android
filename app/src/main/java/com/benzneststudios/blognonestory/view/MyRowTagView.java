package com.benzneststudios.blognonestory.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.benzneststudios.blognonestory.BlognoneTags;
import com.benzneststudios.blognonestory.MyGlide;
import com.benzneststudios.blognonestory.R;

/**
 * Created by benznest on 16-Mar-18.
 */

public class MyRowTagView extends FrameLayout {

    private BlognoneTags mBlognoneTags;
    private ImageView img;
    private TextView tvTagName;
    private TextView tvTagEndpoint;
    private ImageView imgFavoriteTag;
    private OnRowTagListener mOnRowTagListener;

    public MyRowTagView(@NonNull Context context) {
        super(context);
        init();
    }

    public MyRowTagView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyRowTagView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyRowTagView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.row_tag_title, this);
        initView();
    }

    private void initView() {
        img = (ImageView) findViewById(R.id.img);
        img.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnRowTagListener != null) {
                    mOnRowTagListener.onTagSelected(mBlognoneTags);
                }
            }
        });

        tvTagName = (TextView) findViewById(R.id.tv_tag_name);
        tvTagName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnRowTagListener != null) {
                    mOnRowTagListener.onTagSelected(mBlognoneTags);
                }
            }
        });
        tvTagEndpoint = (TextView) findViewById(R.id.tv_tag_endpoint);


        imgFavoriteTag = (ImageView) findViewById(R.id.img_favorite_tag);
        imgFavoriteTag.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean favorite = !mBlognoneTags.getFavorite();
                mBlognoneTags.setFavorite(favorite);
                if (mOnRowTagListener != null) {
                    mOnRowTagListener.onFavoriteTagChanged(mBlognoneTags, favorite);
                }
            }
        });
    }

    public void setBlognoneTags(BlognoneTags blognoneTags) {
        mBlognoneTags = blognoneTags;
        tvTagName.setText(mBlognoneTags.getName());
        tvTagEndpoint.setText("blognone.com/topics/" + mBlognoneTags.getEndpoint());
        MyGlide.load(getContext(), img, mBlognoneTags.getIcon());

        if (blognoneTags.getFavorite()) {
            imgFavoriteTag.setImageResource(R.drawable.ic_favorite_filled);
        } else {
            imgFavoriteTag.setImageResource(R.drawable.ic_favorite_outline);
        }
    }

    public void setEnableFavoriteMode(boolean b) {
        if (b) {
            imgFavoriteTag.setVisibility(VISIBLE);
        } else {
            imgFavoriteTag.setVisibility(GONE);
        }
    }

    public void setOnRowTagListener(OnRowTagListener onRowTagListener) {
        mOnRowTagListener = onRowTagListener;
    }

    public interface OnRowTagListener {
        void onTagSelected(BlognoneTags blognoneTags);
        void onFavoriteTagChanged(BlognoneTags blognoneTags, boolean favorite);
    }
}
