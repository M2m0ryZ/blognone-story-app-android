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
import com.panuwatjan.blognonestory.dao.jobs.TeamBlockDao;

/**
 * Created by benznest on 06-Sep-18.
 */

public class MyTeamBlockView extends FrameLayout {
    private TeamBlockDao mTeamBlockDao;
    private ImageView imgTeam;
    private TextView tvTitle;
    private TextView tvSubTitle;
    private TextView tvDescription;

    public MyTeamBlockView(@NonNull Context context) {
        super(context);
        init();
    }

    public MyTeamBlockView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTeamBlockView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyTeamBlockView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.row_team_block, this);
        initView();
    }

    private void initView() {

        imgTeam = (ImageView) findViewById(R.id.img_team);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvSubTitle = (TextView) findViewById(R.id.tv_sub_title);
        tvDescription = (TextView) findViewById(R.id.tv_description);
    }

    public void setTeamBlockDao(TeamBlockDao teamBlockDao) {
        mTeamBlockDao = teamBlockDao;
        tvTitle.setText(teamBlockDao.getTitle());
        tvSubTitle.setText(teamBlockDao.getSubtitle());
        tvDescription.setText(teamBlockDao.getContent());
        MyGlide.load(getContext(), imgTeam, teamBlockDao.getImage());
    }
}
