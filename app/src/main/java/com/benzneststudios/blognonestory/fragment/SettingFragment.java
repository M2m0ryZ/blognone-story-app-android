package com.benzneststudios.blognonestory.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.benzneststudios.blognonestory.MyApplication;
import com.benzneststudios.blognonestory.MyFontSize;
import com.benzneststudios.blognonestory.MySetting;
import com.benzneststudios.blognonestory.MyUtils;
import com.benzneststudios.blognonestory.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {


    private SwitchCompat switchEnableSaveTopicAuto;
    private SwitchCompat switchEnableDisplayTabOption;
    private SwitchCompat switchEnableNotificationNode;
    private SwitchCompat switchEnableVibration;
    private LinearLayout llChooseFontSize;
    private TextView tvChooseFontSize;
    private SwitchCompat switchEnableDisplayNewComment;

    public SettingFragment() {
        // Required empty public constructor
    }

    public static SettingFragment newInstance() {
        Bundle args = new Bundle();

        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        getActivity().setTitle(R.string.setting);
        View v = inflater.inflate(R.layout.fragment_setting, container, false);
        initView(v);
        update();
        return v;
    }

    private void initView(View v) {
        switchEnableSaveTopicAuto = (SwitchCompat) v.findViewById(R.id.switch_enable_save_topic_auto);
        switchEnableSaveTopicAuto.setChecked(MySetting.isSettingAutoCacheTopic(getContext()));
        switchEnableSaveTopicAuto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                MyUtils.vibrate();
                MySetting.setSettingAutoCacheTopic(getContext(), b);
            }
        });

        switchEnableDisplayTabOption = (SwitchCompat) v.findViewById(R.id.switch_enable_display_tab_option);
        switchEnableDisplayTabOption.setChecked(MySetting.isSettingDisplayTabOption(getContext()));
        switchEnableDisplayTabOption.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                MyUtils.vibrate();
                MySetting.setSettingDisplayTabOption(getContext(), b);
            }
        });

        switchEnableNotificationNode = (SwitchCompat) v.findViewById(R.id.switch_enable_notification_node);
        switchEnableNotificationNode.setChecked(MySetting.isSettingNotification(getContext()));
        switchEnableNotificationNode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                MyUtils.vibrate();
                MySetting.setSettingNotification(getContext(), b);
            }
        });

        switchEnableVibration = (SwitchCompat) v.findViewById(R.id.switch_enable_vibration);
        switchEnableVibration.setChecked(MySetting.isSettingVibration(getContext()));
        switchEnableVibration.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                MyUtils.vibrate();
                MySetting.setSettingVibration(getContext(), b);
            }
        });

        llChooseFontSize = (LinearLayout) v.findViewById(R.id.ll_choose_font_size);
        llChooseFontSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChooseFontSizeDialog();
            }
        });
        tvChooseFontSize = (TextView) v.findViewById(R.id.tv_choose_font_size);

        switchEnableDisplayNewComment = (SwitchCompat) v.findViewById(R.id.switch_enable_display_new_comment);
        switchEnableDisplayNewComment.setChecked(MySetting.isSettingShowNewComment(getContext()));
        switchEnableDisplayNewComment.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                MySetting.setSettingShowNewComment(getContext(), b);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.empty, menu);
    }

    private void openChooseFontSizeDialog() {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getContext());
        builder.setTitle("Font size");
        builder.setSingleChoiceItems(MyFontSize.getFontSizeName(getContext()),
                MySetting.getSettingFontSize(getContext()), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MySetting.setSettingFontSize(getContext(), which);
                        MyApplication.fontSize = MySetting.getSettingFontSize(getContext());
                        MyApplication.factorFontSize = MyFontSize.getFontSizeFactorCurrent(getContext());

                        update();
                        dialog.dismiss();
                    }
                });
        builder.create();
        builder.show();
    }

    private void update() {
        tvChooseFontSize.setText(MyFontSize.getFontSizeNameCurrent(getContext()));
    }

}
