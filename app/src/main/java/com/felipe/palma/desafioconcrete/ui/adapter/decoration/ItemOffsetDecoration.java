package com.felipe.palma.desafioconcrete.ui.adapter.decoration;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Felipe Palma on 12/07/2019.
 */
public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {

    private int mSpacing;

    public ItemOffsetDecoration(int itemOffset) {
        mSpacing = itemOffset;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(mSpacing, mSpacing, mSpacing, mSpacing);
    }
}