package com.aran.bang.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import rx.Subscription;

/**
 * Created by Yat3s on 11/6/15.
 * Email: yat3s@opentown.cn
 * Copyright (c) 2015 opentown. All rights reserved.
 */

public abstract class BaseFragment extends Fragment {
    protected Subscription mSubscription;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected abstract int getRootViewId();

    protected abstract void initView(View rootView);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
    Bundle savedInstanceState) {
        View rootView = inflater.inflate(getRootViewId(), container, false);
        initView(rootView);
        return rootView;
    }

    public void showToast(String toastStr) {
        // if fragment had been attach to activity
        if (isAdded()) {
            Toast toast = Toast.makeText(getActivity(), toastStr, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    public void startActivity(Class<?> paramClass) {
        startActivity(paramClass, null);
    }

    public void startActivity(Class<?> paramClass, Bundle paramBundle) {
        Intent localIntent = new Intent();
        localIntent.setClass(getActivity(), paramClass);
        if (paramBundle != null) {
            localIntent.putExtras(paramBundle);
        }
        startActivity(localIntent);
    }


    public void showLoading(String tips) {

    }

    public void showLoading() {

    }

    public void showFullScreenLoading() {

    }

    public void dismissLoading() {

    }

    public void finishView() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mSubscription) {
            mSubscription.unsubscribe();
        }
    }
}
