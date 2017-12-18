package br.com.githubrepos.pullrequests;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

import br.com.githubrepos.Injection;
import br.com.githubrepos.R;
import br.com.githubrepos.data.entity.PullRequest;

public class PullRequestsActivity extends AppCompatActivity implements PullRequestsContract.View {

    private PullRequestsContract.UserActionsListener mActionsListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pullrequests);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        // show or hide the default home button
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        // enable overriding the default toolbar layout
        actionBar.setDisplayShowCustomEnabled(true);
        // enable the default title element here (for centered title)
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("PullRequests");

        mActionsListener = new PullRequestsPresenter(Injection.providePullRequestServiceApi(), this);
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void setProgressIndicator(boolean isActive) {

    }

    @Override
    public void showPullRequestList(List<PullRequest> pullRequestList) {

    }

    @Override
    public void changeActionBarWhenPullRequestSelected() {

    }

    @Override
    public void changeActionBarWhenPullRequestUnselected() {

    }

    @Override
    public void removePullRequest(PullRequest pullRequest) {

    }

    @Override
    public void showPullRequestInBrowser() {

    }
}
