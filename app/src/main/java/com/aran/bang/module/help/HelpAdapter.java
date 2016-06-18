package com.aran.bang.module.help;

import android.content.Context;
import android.net.Uri;

import com.aran.bang.R;
import com.yat3s.library.adapter.BaseAdapter;
import com.yat3s.library.adapter.BaseViewHolder;

/**
 * Created by Yat3s on 6/17/16.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class HelpAdapter extends BaseAdapter<HelpModel> {
    private static final String TAG = "HelpAdapter";

    public HelpAdapter(Context context) {
        super(context);
    }

    @Override
    protected void bindDataToItemView(BaseViewHolder holder, HelpModel data, int position) {
        holder.setImageURI(R.id.image_iv, Uri.parse(data.image))
                .setText(R.id.title_tv, data.title)
                .setText(R.id.host_nickname_tv, data.host.nickname)
                .setImageURI(R.id.host_avatar_iv, Uri.parse(data.host.avatar));
    }

    @Override
    protected int getItemViewLayoutId(int position, HelpModel data) {
        return R.layout.item_help;
    }
}
