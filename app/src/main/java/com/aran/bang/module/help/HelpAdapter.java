package com.aran.bang.module.help;

import android.content.Context;

import com.aran.bang.R;
import com.yat3s.library.adapter.BaseAdapter;
import com.yat3s.library.adapter.BaseViewHolder;

/**
 * Created by Yat3s on 6/17/16.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class HelpAdapter extends BaseAdapter<HelpModel> {
    public HelpAdapter(Context context) {
        super(context);
    }

    @Override
    protected void bindDataToItemView(BaseViewHolder holder, HelpModel data, int position) {

    }

    @Override
    protected int getItemViewLayoutId(int position, HelpModel data) {
        return R.layout.item_help;
    }
}
