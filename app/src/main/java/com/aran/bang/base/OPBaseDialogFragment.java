package com.aran.bang.base;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by Yat3s on 4/19/16.
 * Email: yat3s@opentown.cn
 * Copyright (c) 2015 opentown. All rights reserved.
 */

public abstract class OPBaseDialogFragment extends DialogFragment {
    private boolean isTransparentDialog;
    private int mWidth = WindowManager.LayoutParams.WRAP_CONTENT;
    private int mHeight = WindowManager.LayoutParams.WRAP_CONTENT;
    private int mDialogAnimationStyleRes;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
    Bundle savedInstanceState) {
        if (isTransparentDialog) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        getDialog().getWindow().setLayout(mWidth, mHeight);
        if (0 != mDialogAnimationStyleRes) {
            getDialog().getWindow().getAttributes().windowAnimations = mDialogAnimationStyleRes;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
        mBuilder.setView(getRootView());
        return mBuilder.create();
    }

    protected abstract View getRootView();

    protected void showToast(String tip) {
        Toast.makeText(getActivity(), tip, Toast.LENGTH_SHORT).show();
    }

    protected void setDialogAnimationStyleRes(int animationStyleRes) {
        mDialogAnimationStyleRes = animationStyleRes;
    }

    protected void setTransparentDialog(boolean isTransparentDialog) {
        this.isTransparentDialog = isTransparentDialog;
    }

    protected void setDialogSize(int width, int height) {
        mWidth = width;
        mHeight = height;
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
}
