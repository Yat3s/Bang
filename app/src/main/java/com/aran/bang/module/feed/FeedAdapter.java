package com.aran.bang.module.feed;

import android.content.Context;

import com.aran.bang.R;
import com.yat3s.library.adapter.BaseAdapter;
import com.yat3s.library.adapter.BaseViewHolder;

/**
 * Created by Yat3s on 6/17/16.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class FeedAdapter extends BaseAdapter<FeedModel> {
    public FeedAdapter(Context context) {
        super(context);
    }

    @Override
    protected void bindDataToItemView(BaseViewHolder holder, FeedModel data, int position) {

    }

    @Override
    protected int getItemViewLayoutId(int position, FeedModel data) {
        return R.layout.item_feed;
    }
}
