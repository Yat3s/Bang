package com.aran.bang.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;


/**
 * Created by Yat3s on 6/10/16.
 * Email: yat3s@opentown.cn
 * Copyright (c) 2015 opentown. All rights reserved.
 */

public class ContentTextView extends TextView {

    public ContentTextView(Context context) {
        this(context, null);
    }

    public ContentTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ContentTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setTypeface(TypefaceUtil.getContentTypeface(), Typeface.BOLD);
        setLineSpacing(0.0f, 1.5f);
    }
}
