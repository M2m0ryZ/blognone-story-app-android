package com.benzneststudios.blognonestory.view;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.benzneststudios.blognonestory.MyApplication;

/**
 * Created by benznest on 21-Mar-18.
 */

public class MyTextView extends AppCompatTextView {
    public MyTextView(Context context) {
        super(context);
        init();
    }


    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        double size = getTextSize();

        double factor = MyApplication.factorFontSize;

        size = size * factor;
        setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) size);

//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
//            int minwidth = getMinWidth();
//            minwidth = (int) (minwidth * factor);
//            setMinWidth(minwidth);
//        }
    }
}
