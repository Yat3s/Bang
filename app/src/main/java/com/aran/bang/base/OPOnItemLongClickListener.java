package com.aran.bang.base;

import android.view.View;

/**
 * Created by Yat3s on 11/8/15.
 * Email: yat3s@opentown.cn
 * Copyright (c) 2015 opentown. All rights reserved.
 */

public interface OPOnItemLongClickListener<TDataModel> {
    void onLongClick(View view, TDataModel tDataModel, int position);
}
