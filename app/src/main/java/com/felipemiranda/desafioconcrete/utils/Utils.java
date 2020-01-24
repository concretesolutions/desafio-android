package com.felipemiranda.desafioconcrete.utils;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by felipemiranda
 */

public class Utils {

    public static void recyclerViewAnimationEntrance(RecyclerView recyclerView) {
        AnimationSet set = new AnimationSet(true);
        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(1000);
        set.addAnimation(animation);

        animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f
        );
        animation.setDuration(500);
        set.addAnimation(animation);

        LayoutAnimationController controller = new LayoutAnimationController(set, 0.1f);
        controller.setInterpolator(new AccelerateInterpolator());
        recyclerView.setLayoutAnimation(controller);
    }

    public static AlphaAnimation alphaAnimation(float fromAlpha, float toAlpha, int duration){
        AlphaAnimation alphaAnimation = new AlphaAnimation(fromAlpha, toAlpha);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);

        return alphaAnimation;
    }
}
