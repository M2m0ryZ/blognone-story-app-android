package com.panuwatjan.blognonestory.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.panuwatjan.blognonestory.MyThemes;
import com.panuwatjan.blognonestory.R;

/**
 * Created by benznest on 16-Mar-18.
 */

public class MyBlognoneNodeTabView extends FrameLayout {

    private final int COUNT_TABS = 2;
    private LinearLayout llContainer;

    private LinearLayout[] llContainerPage;
    private TextView[] tvTab;
    private ViewPager mViewPager;
    private TextView tvCountOrder;

    public MyBlognoneNodeTabView(Context context) {
        super(context);
        init();
    }

    public MyBlognoneNodeTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyBlognoneNodeTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyBlognoneNodeTabView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        inflate(getContext(), R.layout.tab_blognone_node, this);
        initInstance();
    }

    private void initInstance() {

        llContainer = (LinearLayout) findViewById(R.id.ll_container);

        llContainerPage = new LinearLayout[COUNT_TABS];
        llContainerPage[0] = (LinearLayout) findViewById(R.id.ll_container_1);
        llContainerPage[1] = (LinearLayout) findViewById(R.id.ll_container_2);

        tvTab = new TextView[COUNT_TABS];
        tvTab[0] = (TextView) findViewById(R.id.tv_tab_1);
        tvTab[1] = (TextView) findViewById(R.id.tv_tab_2);

        for (int i = 0; i < COUNT_TABS; i++) {
            final int index = i;
            llContainerPage[i].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    setTabSelected(index);
                }
            });
        }
    }

    private void setAllTabNotSelected() {
        for (int i = 0; i < COUNT_TABS; i++) {
            llContainerPage[i].setBackgroundResource(MyThemes.getCurrentTheme().getColorSecondary());
        }
    }

    public void setTabSelected(int index) {
        if (mViewPager != null) {
            mViewPager.setCurrentItem(index);
        }
        setAllTabNotSelected();
        llContainerPage[index].setBackgroundResource(MyThemes.getCurrentTheme().getIndicatorColor());
    }

    public void setViewPager(ViewPager viewPager) {
        mViewPager = viewPager;
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setTabSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setCommentCount(int count) {
        if (count > 0) {
            tvTab[1].setText("Comments (" + count + ")");
        }else{
            tvTab[1].setText("Comment");
        }
    }
}
