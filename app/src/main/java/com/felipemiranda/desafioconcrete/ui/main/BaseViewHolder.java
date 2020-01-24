package com.felipemiranda.desafioconcrete.ui.main;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by felipemiranda
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {
    private int mCurrentPosition;
    public BaseViewHolder(View itemView) {
        super(itemView);
    }
    protected abstract void clear();
    public void onBind(int position) {
        mCurrentPosition = position;
        clear();
    }
    public int getCurrentPosition() {
        return mCurrentPosition;
    }
}