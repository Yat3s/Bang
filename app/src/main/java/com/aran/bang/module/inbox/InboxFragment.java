package com.aran.bang.module.inbox;

import android.view.View;

import com.aran.bang.R;
import com.aran.bang.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by Yat3s on 3/23/16.
 * Email: yat3s@opentown.cn
 * Copyright (c) 2015 opentown. All rights reserved.
 */

public class InboxFragment extends BaseFragment {

    public static InboxFragment newInstance() {
        return new InboxFragment();
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_inbox;
    }

    @Override
    protected void initView(View rootView) {
        ButterKnife.bind(this, rootView);

    }
}
