package com.benznestdeveloper.blognonestory.view;

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

import com.benznestdeveloper.blognonestory.R;

/**
 * Created by benznest on 16-Mar-18.
 */

public class MyNodeOptionTabViewView extends FrameLayout {

    private boolean favorite;
    private FrameLayout flFavoriteContainer;
    private ImageView imgFavorite;
    private FrameLayout flShareContainer;
    private FrameLayout flSaveContainer;

    private OnNodeOptionListener mOnNodeOptionListener;
    private boolean save;
    private ImageView imgSave;
    private TextView tvFavorite;
    private TextView tvSave;

    public MyNodeOptionTabViewView(@NonNull Context context) {
        super(context);
        init();
    }

    public MyNodeOptionTabViewView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyNodeOptionTabViewView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyNodeOptionTabViewView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.tab_node_option_view, this);
        initView();
    }

    private void initView() {
        imgFavorite = (ImageView) findViewById(R.id.img_favorite);
        flFavoriteContainer = (FrameLayout) findViewById(R.id.fl_favorite_container);
        flFavoriteContainer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                favorite = !favorite;
                setFavorite(favorite);
                if (mOnNodeOptionListener != null) {
                    mOnNodeOptionListener.onFavoriteChanged(favorite);
                }
            }
        });

        flShareContainer = (FrameLayout) findViewById(R.id.fl_share_container);
        flShareContainer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                favorite = !favorite;
                if (mOnNodeOptionListener != null) {
                    mOnNodeOptionListener.onShareNode();
                }
            }
        });

        flSaveContainer = (FrameLayout) findViewById(R.id.fl_save_container);
        flSaveContainer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                save = !save;
                setSaveTopic(save);
                if (mOnNodeOptionListener != null) {
                    mOnNodeOptionListener.onSaveTopicChanged(save);
                }
            }
        });
        imgSave = (ImageView) findViewById(R.id.img_save);

        tvFavorite = (TextView) findViewById(R.id.tv_favorite);
        tvSave = (TextView) findViewById(R.id.tv_save);
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
        if (favorite) {
            imgFavorite.setImageResource(R.drawable.ic_favorite_filled);
            tvFavorite.setText("Favorite");
        } else {
            imgFavorite.setImageResource(R.drawable.ic_favorite_outline);
            tvFavorite.setText("Favored");
        }
    }

    public void setSaveTopic(boolean save) {
        this.save = save;
        if (save) {
            imgSave.setImageResource(R.drawable.ic_save_bl);
            tvSave.setText("Saved");
        } else {
            imgSave.setImageResource(R.drawable.ic_save_b);
            tvSave.setText("Save");
        }
    }

    public void setOnNodeOptionListener(OnNodeOptionListener onNodeOptionListener) {
        mOnNodeOptionListener = onNodeOptionListener;
    }

    public interface OnNodeOptionListener {
        void onFavoriteChanged(boolean favorite);

        void onShareNode();

        void onSaveTopicChanged(boolean save);
    }


}
