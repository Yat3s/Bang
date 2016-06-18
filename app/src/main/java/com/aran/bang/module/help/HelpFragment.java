package com.aran.bang.module.help;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aran.bang.R;
import com.aran.bang.base.BaseFragment;
import com.aran.bang.module.UserModel;
import com.aran.bang.widget.OPDividerItemDecoration;
import com.yat3s.library.adapter.AnimationType;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Yat3s on 3/23/16.
 * Email: yat3s@opentown.cn
 * Copyright (c) 2015 opentown. All rights reserved.
 */

public class HelpFragment extends BaseFragment {
    private static final String TAG = "HelpFragment";
    @Bind(R.id.help_rv)
    RecyclerView helpRv;
    HelpAdapter helpAdapter;

    public static HelpFragment newInstance() {
        return new HelpFragment();
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_help;
    }

    @Override
    protected void initView(View rootView) {
        ButterKnife.bind(this, rootView);
        initView();
    }

    private void initView() {
        helpAdapter = new HelpAdapter(getActivity());
        helpRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        helpRv.setAdapter(helpAdapter);
        helpRv.addItemDecoration(new OPDividerItemDecoration(getActivity()));
        helpAdapter.setItemAnimation(AnimationType.SCALE);


        loadHelps();
    }


    private void loadHelps() {
        BmobQuery<HelpModel> query = new BmobQuery<>();
        query.include("host");    // 注意这里的include方法，当在查询过程中想将指针类型的对象信息也查询出来是请使用此方法。
        query.findObjects(getActivity(), new FindListener<HelpModel>() {
            @Override
            public void onSuccess(List<HelpModel> list) {
                helpAdapter.addFirstDataSet(list);
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.action_btn)
    public void onClick() {
        startActivity(PostHelpDialogActivity.class);
    }
}
