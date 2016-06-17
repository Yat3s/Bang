package com.aran.bang.module.setting;

import android.view.View;

import com.aran.bang.R;
import com.aran.bang.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by Yat3s on 3/23/16.
 * Email: yat3s@opentown.cn
 * Copyright (c) 2015 opentown. All rights reserved.
 */

public class SettingFragment extends BaseFragment {

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void initView(View rootView) {
        ButterKnife.bind(this, rootView);
    }
}
