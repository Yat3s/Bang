package com.aran.bang;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import cn.bmob.v3.Bmob;

/**
 * Created by Yat3s on 5/28/16.
 * Email: yat3s@opentown.cn
 * Copyright (c) 2015 opentown. All rights reserved.
 */

public class App extends Application {
    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        Iconify.with(new FontAwesomeModule());
        Fresco.initialize(this);
        Bmob.initialize(this, "fcefb1d93da0fbedf7d364eb8e47b138");
    }

    public static Context getContext() {
        return mInstance.getApplicationContext();
    }

}
