package com.aran.bang.module.setting;


import android.view.View;

import com.aran.bang.R;
import com.aran.bang.base.BaseDialogFragment;
import com.aran.bang.module.AboutActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Yat3s on 6/12/16.
 * Email: yat3s@opentown.cn
 * Copyright (c) 2015 opentown. All rights reserved.
 */

public class SettingDialogFragment extends BaseDialogFragment {

    public static SettingDialogFragment newInstance() {
        return new SettingDialogFragment();
    }

    @Override
    protected int getRootViewId() {
        return R.layout.dialog_setting;
    }

    @Override
    protected void initView(View rootView) {
        ButterKnife.bind(this, rootView);
    }

    @Override
    protected int getDialogAnimationStyle() {
        return 0;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.push_switch, R.id.push_layout, R.id.weibo_layout, R.id.contract_layout, R.id
            .feedback_layout, R.id.about_layout, R.id.logout_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.push_switch:
                break;
            case R.id.push_layout:
                break;
            case R.id.weibo_layout:
                break;
            case R.id.contract_layout:
                break;
            case R.id.feedback_layout:
                showToast("写点好话吧，不然被老板看到会骂的TT");
                break;
            case R.id.about_layout:
                startActivity(AboutActivity.class);
                break;
            case R.id.logout_layout:
//                UserSession.logout();
                break;
        }
    }
}
