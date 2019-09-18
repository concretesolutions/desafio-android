package com.joseluzardo.githubjavatoplist.Utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FloatingActionButtonScrollBehavior extends FloatingActionButton.Behavior {

    /* Must provide this constructer method, otherwise app will throw Could not inflate Behavior subclass error message. */
    public FloatingActionButtonScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        boolean ret = false;
        if(nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL)
        {
            ret = true;
        }else
        {
            ret = super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
        }

        return ret;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        /* If RecyclerView scroll action consumed vertical pixels bigger than 0, means scroll down. */
        if (dyConsumed > 0) {
            if(child.getVisibility() == View.VISIBLE) {
                child.hide(new FloatingActionButton.OnVisibilityChangedListener() {
                    @Override
                    public void onHidden(FloatingActionButton floatingActionButton) {
                        super.onHidden(floatingActionButton);
                        floatingActionButton.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }
        // Means recyclerview scroll up.
        else if (dyConsumed < 0 && child.getVisibility() != View.VISIBLE) {
            child.show();
        }
    }
}