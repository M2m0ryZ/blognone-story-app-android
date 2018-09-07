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

import com.panuwatjan.blognonestory.MyGlide;
import com.panuwatjan.blognonestory.R;
import com.panuwatjan.blognonestory.dao.jobs.CompanyDao;

/**
 * Created by benznest on 16-Mar-18.
 */

public class MyRowCompanyTitleView extends FrameLayout {
    private CompanyDao mCompanyDao;

    private OnCompanyListener mOnCompanyListener;
    private LinearLayout llDetailContainer;
    private ImageView imgCover;
    private TextView tvTitle;
    private TextView tvCompany;
    private TextView tvFeature;
    private TextView tvType;
    private TextView tvDescription;

    public MyRowCompanyTitleView(@NonNull Context context) {
        super(context);
        init();
    }

    public MyRowCompanyTitleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyRowCompanyTitleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyRowCompanyTitleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.row_company_title, this);
        initView();
    }

    private void initView() {
        imgCover = findViewById(R.id.img_cover);
        tvCompany = findViewById(R.id.tv_company);
        tvFeature = (TextView) findViewById(R.id.tv_feature);
        tvType = (TextView) findViewById(R.id.tv_type);
        tvDescription = (TextView) findViewById(R.id.tv_description);
    }

    public void setCompany(final CompanyDao company) {
        mCompanyDao = company;

        tvCompany.setText(company.getNameEn());
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnCompanyListener != null) {
                    mOnCompanyListener.onCompanySelected(mCompanyDao);
                }
            }
        });

        if (company.getCover() == null) {
            imgCover.setVisibility(GONE);
        } else {
            imgCover.setVisibility(VISIBLE);
        }

        if (company.getPremium()) {
            tvFeature.setVisibility(VISIBLE);
        } else {
            tvFeature.setVisibility(GONE);
        }

        tvType.setText(company.getIndustry());

        tvDescription.setText(company.getBrief());

        MyGlide.load(getContext(), imgCover, company.getCover());

    }

    public void setOnCompanyListener(OnCompanyListener onCompanyListener) {
        mOnCompanyListener = onCompanyListener;
    }

    public void setEnableTopicDetail(boolean b) {
        if (b) {
            llDetailContainer.setVisibility(VISIBLE);
        } else {
            llDetailContainer.setVisibility(GONE);
        }
    }

    public interface OnCompanyListener {
        void onCompanySelected(CompanyDao companyDao);

    }
}
