package com.benznestdeveloper.blognonestory.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.benznestdeveloper.blognonestory.R;

/**
 * Created by benznest on 16-Mar-18.
 */

public class MyTagView extends FrameLayout {

    private TextView tvTagName;
    private FrameLayout flContainer;

    public MyTagView(@NonNull Context context) {
        super(context);
        init();
    }

    public MyTagView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTagView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyTagView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.tag_view, this);
        initView();
    }

    private void initView() {
        tvTagName = (TextView) findViewById(R.id.tv_tag_name);
        flContainer = (FrameLayout) findViewById(R.id.fl_container);
    }

    public void setTagName(String name) {
        tvTagName.setText(name);
    }

    public void setStickyMode(boolean is) {
        if (is) {
            tvTagName.setBackgroundResource(R.color.colorBackgroundSticky);
            flContainer.setBackgroundResource(R.color.colorYellowDark2);
        } else {
            tvTagName.setBackgroundResource(R.color.colorBackgroundPrimary);
            flContainer.setBackgroundResource(R.color.colorSilver);
        }
    }

}
