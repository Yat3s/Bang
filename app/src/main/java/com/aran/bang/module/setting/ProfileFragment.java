package com.aran.bang.module.setting;

import android.view.View;
import android.widget.TextView;

import com.aran.bang.R;
import com.aran.bang.base.BaseFragment;
import com.facebook.drawee.view.SimpleDraweeView;
import com.joanzapata.iconify.widget.IconTextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Yat3s on 6/12/16.
 * Email: yat3s@opentown.cn
 * Copyright (c) 2015 opentown. All rights reserved.
 */

public class ProfileFragment extends BaseFragment {

    @Bind(R.id.followers_count_tv)
    TextView followersCountTv;
    @Bind(R.id.follows_count_tv)
    TextView followsCountTv;
    @Bind(R.id.nickname_tv)
    TextView nicknameTv;
    @Bind(R.id.wow_count_tv)
    TextView wowCountTv;
    @Bind(R.id.intro_tv)
    TextView introTv;
    @Bind(R.id.logo_view)
    IconTextView logoView;
    @Bind(R.id.background_iv)
    SimpleDraweeView backgroundIv;
    @Bind(R.id.avatar_iv)
    SimpleDraweeView avatarIv;

    public static ProfileFragment newInstance(String userId) {
        return new ProfileFragment();
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void initView(View rootView) {
        ButterKnife.bind(this, rootView);
        loadProfile();
    }

    private void loadProfile() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.intro_layout, R.id.setting_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.intro_layout:
                break;
            case R.id.setting_layout:
                SettingDialogFragment.newInstance().show(getFragmentManager(),
                        "SettingDialogFragment");
                break;
        }
    }
}
