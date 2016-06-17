package com.aran.bang.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yat3s on 11/8/15.
 * Email: yat3s@opentown.cn
 * Copyright (c) 2015 opentown. All rights reserved.
 */

public abstract class OPBaseRecyclerViewAdapter<TDataModel, TViewHolder extends OPBaseRecyclerViewAdapter.OPViewHolder> extends RecyclerView.Adapter<TViewHolder> {
    /**
     * click listener
     */
    protected OPOnItemClickListener mOnItemClickListener;
    /**
     * long click listener
     */
    protected OPOnItemLongClickListener mOnItemLongClickListener;
    /**
     * data
     */
    protected List<TDataModel> mList;

    public Context context;

    public OPBaseRecyclerViewAdapter(Context context) {
        this.context = context;
        mList = new ArrayList<>();
    }

    public void addSetDataToAdapter(List<TDataModel> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void addDataToAdapter(TDataModel list) {
        mList.add(list);
        notifyDataSetChanged();
    }

    public void clearData() {
        if (null != mList) {
            mList.clear();
        }
    }

    /**
     * get a item by index
     *
     * @param position
     * @return
     */
    protected TDataModel getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public OPViewHolder createOPViewHolder(ViewGroup viewGroup, int layoutID) {
        return new OPViewHolder(inflateItemView(viewGroup, layoutID));
    }

    /**
     * set a long click listener
     *
     * @param onItemLongClickListener
     */
    public void setOnItemLongClickListener(OPOnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    /**
     * set a click listener
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OPOnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    /**
     * inflate a view by viewgroup ,id ,etc
     *
     * @param viewGroup
     * @param layoutId
     * @return
     */
    protected View inflateItemView(ViewGroup viewGroup, int layoutId) {
        return inflateItemView(viewGroup, layoutId, false);
    }

    /**
     * inflate a view by viewgroup ,id ,etc
     *
     * @param viewGroup
     * @param layoutId
     * @param attach
     * @return
     */
    protected View inflateItemView(ViewGroup viewGroup, int layoutId, boolean attach) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, attach);
    }

    /**
     * a final function to avoid you override
     * use template design pattern
     *
     * @param vh
     * @param position
     */
    @Override
    public final void onBindViewHolder(TViewHolder vh, int position) {
        final TDataModel item = getItem(position);
        bindDataToItemView(vh, item, position);
        bindClickListenerToItemView(vh, item, position);
    }


    /**
     * bind data to itemview
     *
     * @param tViewHolder viewholder
     * @param tDataModel  item
     */
    protected abstract void bindDataToItemView(TViewHolder tViewHolder, TDataModel tDataModel, int position);


    /**
     * bind click listner to itemview
     *
     * @param tViewHolder viewholder
     * @param tDataModel  item
     */
    protected final void bindClickListenerToItemView(TViewHolder tViewHolder, final TDataModel tDataModel, final int position) {
        if (mOnItemClickListener != null) {
            tViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onClick(view, tDataModel, position);
                }
            });
        }
        if (mOnItemLongClickListener != null) {
            tViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemLongClickListener.onLongClick(v, tDataModel, position);
                    return true;
                }
            });
        }
    }

    /**
     * start activity in adapter
     *
     * @param paramClass
     */
    public void startActivity(Class<?> paramClass) {
        startActivity(paramClass, null);
    }

    public void startActivity(Class<?> paramClass, Bundle paramBundle) {
        Intent localIntent = new Intent();
        localIntent.setClass(context, paramClass);
        if (paramBundle != null) {
            localIntent.putExtras(paramBundle);
        }
        context.startActivity(localIntent);
    }

    public void showToast(String toast) {
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
    }

    /**
     * BaseViewHolder
     * using bindViewById(View view,int id) function to handle the relations between view and viewId
     */
    public abstract static class BaseViewHolder extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);
            findView();
        }

        /**
         * you need to override this method to bind view in the viewholder
         * you'd better use bindViewById(View view,int id)
         */
        protected abstract void findView();

        /**
         * generic function to findViewById
         *
         * @param id viewId
         * @return the view found
         */
        protected <TView extends View> TView findViewById(int id) {
            return (TView) itemView.findViewById(id);
        }

    }

    public static class OPViewHolder extends RecyclerView.ViewHolder {
        private final SparseArray<View> mViews;

        public OPViewHolder(View itemView) {
            super(itemView);
            mViews = new SparseArray<>();
        }

        public <TView extends View> TView getView(int id) {
            View view = mViews.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                mViews.put(id, view);
            }
            return (TView) view;
        }

        public TextView getTextView(int id) {
            return getView(id);
        }

        public ImageView getImageView(int id) {
            return getView(id);
        }

        public OPViewHolder setTextView(int viewId, String value) {
            TextView view = getView(viewId);
            view.setText(value);
            return this;
        }

        public OPViewHolder setTextColor(int viewId, int textColor) {
            TextView view = getView(viewId);
            view.setTextColor(textColor);
            return this;
        }

        public OPViewHolder setImageResource(int viewId, int imageResId) {
            ImageView view = getView(viewId);
            view.setImageResource(imageResId);
            return this;
        }

        public OPViewHolder setBackgroundColor(int viewId, int color) {
            View view = getView(viewId);
            view.setBackgroundColor(color);
            return this;
        }

        public OPViewHolder setBackgroundResource(int viewId, int backgroundRes) {
            View view = getView(viewId);
            view.setBackgroundResource(backgroundRes);
            return this;
        }

        public OPViewHolder setVisible(int viewId, boolean visible) {
            View view = getView(viewId);
            view.setVisibility(visible ? View.VISIBLE : View.GONE);
            return this;
        }

        public OPViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
            View view = getView(viewId);
            view.setOnClickListener(listener);
            return this;
        }

        public OPViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
            View view = getView(viewId);
            view.setOnTouchListener(listener);
            return this;
        }

        public OPViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
            View view = getView(viewId);
            view.setOnLongClickListener(listener);
            return this;
        }

        public OPViewHolder setTag(int viewId, Object tag) {
            View view = getView(viewId);
            view.setTag(tag);
            return this;
        }

    }
}
