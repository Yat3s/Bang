package com.aran.bang.widget;

import android.graphics.Typeface;

import com.aran.bang.App;


/**
 * Created by Yat3s on 6/10/16.
 * Email: yat3s@opentown.cn
 * Copyright (c) 2015 opentown. All rights reserved.
 */

public class TypefaceUtil {
    private static Typeface mTypeface;
    public static Typeface getContentTypeface() {
        if (null == mTypeface) {
            mTypeface = Typeface.createFromAsset(App.getContext().getAssets(), "fonts/hytx.ttf");
        }
        return mTypeface;
    }
}
