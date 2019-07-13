package com.felipe.palma.desafioconcrete.ui.adapter;

/**
 * Created by Felipe Palma on 12/07/2019.
 */
public class AnimationItem {
    private final String mName;
    private final int mResourceId;

    public AnimationItem(String name, int resourceId) {
        mName = name;
        mResourceId = resourceId;
    }

    public String getName() {
        return mName;
    }

    public int getResourceId() {
        return mResourceId;
    }
}