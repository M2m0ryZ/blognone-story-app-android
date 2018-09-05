package com.benznestdeveloper.blognonestory.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.benznestdeveloper.blognonestory.R;

/**
 * Created by benznest on 16-Mar-18.
 */

public class MyNodeOptionDetailTabViewView extends FrameLayout {

    private FrameLayout flViewedContainer;
    private FrameLayout flFavoriteContainer;
    private FrameLayout flCacheContainer;

    public MyNodeOptionDetailTabViewView(@NonNull Context context) {
        super(context);
        init();
    }

    public MyNodeOptionDetailTabViewView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyNodeOptionDetailTabViewView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyNodeOptionDetailTabViewView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.tab_node_option_detail, this);
        initView();
    }

    private void initView() {
        flViewedContainer = (FrameLayout) findViewById(R.id.fl_viewed_container);
        flFavoriteContainer = (FrameLayout) findViewById(R.id.fl_favorite_container);
        flCacheContainer = (FrameLayout) findViewById(R.id.fl_cache_container);
    }

    public void setViewed(boolean is) {
        if (is) {
            flViewedContainer.setVisibility(VISIBLE);
        } else {
            flViewedContainer.setVisibility(GONE);
        }
    }

    public void setFavorite(boolean is) {
        if (is) {
            flFavoriteContainer.setVisibility(VISIBLE);
        } else {
            flFavoriteContainer.setVisibility(GONE);
        }
    }

    public void setSaved(boolean is) {
        if (is) {
            flCacheContainer.setVisibility(VISIBLE);
        } else {
            flCacheContainer.setVisibility(GONE);
        }
    }


    public interface OnNodeOptionDetailListener {

    }

}
