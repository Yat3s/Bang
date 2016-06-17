package com.aran.bang.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import rx.Subscription;

/**
 * Created by Yat3s on 3/23/16.
 * Email: yat3s@opentown.cn
 * Copyright (c) 2015 opentown. All rights reserved.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected Subscription mSubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getRootViewId());
        initView();
    }
    protected abstract int getRootViewId();

    protected abstract void initView();

    public void startActivity(Class<?> paramClass) {
        startActivity(paramClass, null);
    }

    public void startActivity(Class<?> paramClass, Bundle paramBundle) {
        Intent localIntent = new Intent();
        localIntent.setClass(this, paramClass);
        if (paramBundle != null) {
            localIntent.putExtras(paramBundle);
        }
        startActivity(localIntent);
    }

    protected void showToast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mSubscription) {
            mSubscription.unsubscribe();
        }
    }
}
