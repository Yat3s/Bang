package com.aran.bang.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * Created by Yat3s on 4/25/16.
 * Email: yat3s@opentown.cn
 * Copyright (c) 2015 opentown. All rights reserved.
 */

public class OPSliderViewPager extends ViewPager {
    private static final String TAG = "OPSliderViewPager";
    private final static int TIME_INTERVAL = 5000;
    private final static int DELAY = 5000;
    private List<ImageView> mViews;
    private int mCurrentItemIndex;
    int downX, downY;

    private ScheduledExecutorService mScheduledExecutorService;

    //Handler
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (null != getContext()) {
                setCurrentItem(mCurrentItemIndex);
            }
        }
    };

    public OPSliderViewPager(Context context) {
        super(context);
    }

    public OPSliderViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) ev.getRawX();
                downY = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) ev.getRawX() - downX;
                int moveY = (int) ev.getRawY() - downY;
                if (Math.abs(moveX) > Math.abs(moveY)) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    public void setData(List<ImageView> imageViews) {
        mViews = imageViews;
        setAdapter(new SliderPagerAdapter());
        setPageTransformer(false, new AccordionTransformer());
    }

    public void startPlay() {
        mScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        mScheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), DELAY, TIME_INTERVAL,
                TimeUnit.MILLISECONDS);
    }

    private void stopPlay() {
        mScheduledExecutorService.shutdown();
    }

    private class SlideShowTask implements Runnable {
        @Override
        public void run() {
            synchronized (this) {
                mCurrentItemIndex = (mCurrentItemIndex + 1) % mViews.size();
                handler.obtainMessage().sendToTarget();
            }
        }
    }

    class SliderPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViews.get(position));
            return mViews.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViews.get(position));
        }
    }
}
