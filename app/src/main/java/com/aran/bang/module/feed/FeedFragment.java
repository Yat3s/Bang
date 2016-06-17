package com.aran.bang.module.feed;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aran.bang.R;
import com.aran.bang.base.BaseFragment;
import com.aran.bang.base.OPBaseRecyclerViewAdapter;
import com.aran.bang.widget.OPDividerItemDecoration;
import com.aran.bang.widget.OPSliderViewPager;
import com.yat3s.library.adapter.AnimationType;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Yat3s on 3/23/16.
 * Email: yat3s@opentown.cn
 * Copyright (c) 2015 opentown. All rights reserved.
 */

public class FeedFragment extends BaseFragment {

    @Bind(R.id.feed_rv)
    RecyclerView feedRv;

    public static FeedFragment newInstance() {
        return new FeedFragment();
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_feed;
    }

    @Override
    protected void initView(View rootView) {
        ButterKnife.bind(this, rootView);
        initView();
    }

    private void initView() {
        FeedAdapter feedAdapter = new FeedAdapter(getActivity());
        feedRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        feedRv.setAdapter(feedAdapter);
        feedRv.addItemDecoration(new OPDividerItemDecoration(getActivity()));

        List<FeedModel> feeds = new ArrayList<>();
        feeds.add(new FeedModel());
        feeds.add(new FeedModel());
        feeds.add(new FeedModel());
        feeds.add(new FeedModel());
        feeds.add(new FeedModel());
        feeds.add(new FeedModel());

        feedAdapter.addFirstDataSet(feeds);
        feedAdapter.setItemAnimation(AnimationType.SCALE);
    }

    public void setSliderLayout() {
        List<ImageView> images = new ArrayList<>();
        ImageView image1 = new ImageView(getActivity());
        image1.setImageResource(R.mipmap.mock_slider1_img);
        ImageView image2 = new ImageView(getActivity());
        image2.setImageResource(R.mipmap.mock_slider2_img);
        ImageView image3 = new ImageView(getActivity());
        image3.setImageResource(R.mipmap.mock_slider3_img);

        image1.setScaleType(ImageView.ScaleType.CENTER_CROP);
        image2.setScaleType(ImageView.ScaleType.CENTER_CROP);
        image3.setScaleType(ImageView.ScaleType.CENTER_CROP);


        images.add(image1);
        images.add(image2);
        images.add(image3);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
