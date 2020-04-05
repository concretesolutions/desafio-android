package com.igormeira.githubpop.handler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.igormeira.githubpop.model.Repository;
import com.igormeira.githubpop.pullrequest.PullRequestsActivity;

public class EventHandler {
    private Context mContext;

    public EventHandler(Context mContext) {
        this.mContext = mContext;
    }

    public void onRepositoryClick(Repository repository) {
        Intent intent = new Intent(mContext, PullRequestsActivity.class);
        intent.putExtra("creator", repository.getUser().getUsername());
        intent.putExtra("name", repository.getTitle());
        mContext.startActivity(intent);
    }

    public void onPullRequestClick(String url) {
        mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    public void onBackClick() {
        ((Activity) mContext).finish();
    }
}
