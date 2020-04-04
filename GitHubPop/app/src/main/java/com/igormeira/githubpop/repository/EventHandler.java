package com.igormeira.githubpop.repository;

import android.content.Context;
import android.widget.Toast;

public class EventHandler {
    private Context mContext;
    public EventHandler(Context mContext) {
        this.mContext = mContext;
    }
    public void onRepositoryClick() {
        Toast.makeText(mContext, "Now you are following ", Toast.LENGTH_SHORT).show();
    }
}
