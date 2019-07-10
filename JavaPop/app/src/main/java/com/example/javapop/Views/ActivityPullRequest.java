package com.example.javapop.Views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javapop.APIClientGithub.APIClientGithub;
import com.example.javapop.APIClientGithub.Retrofit.RetrofitService;
import com.example.javapop.APIClientGithub.Retrofit.ServiceGenerator;
import com.example.javapop.Adapter.AdapterPullRequest;
import com.example.javapop.Events.ClickItemPullRequestEvent;
import com.example.javapop.Events.PullRequestEvent;
import com.example.javapop.Models.PullRequest;
import com.example.javapop.Models.PullRequestList;
import com.example.javapop.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityPullRequest extends AppCompatActivity {

    private static final String TAG = "ActivityPullRequest";

    private List<PullRequestList> mPullRequest;
    private AdapterPullRequest mAdapterPullRequest;
    private APIClientGithub mAPIClientGithub;
    private String mOwner;
    private String mRepository;

    @BindView(R.id.recyclerView_PullRequest)
    RecyclerView mRecyclerViewPullRequest;

    @BindView(R.id.repository_name)
    TextView mRepositoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_request);
        ButterKnife.bind(this);
        Intent myIntent = getIntent();
        mOwner = myIntent.getStringExtra("mOwner");
        mRepository = myIntent.getStringExtra("mRepository");

        configRetrofit();
        initView();
    }

    private void initView() {
        mPullRequest = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerViewPullRequest.setLayoutManager(linearLayoutManager);
        mAdapterPullRequest = new AdapterPullRequest(mPullRequest, getApplicationContext());
        mRecyclerViewPullRequest.setAdapter(mAdapterPullRequest);
        if (!"".equals(mRepository)) {
            mRepositoryName.setText(mRepository);
        }
    }

    private void configRetrofit() {
        RetrofitService mService = ServiceGenerator.createService(RetrofitService.class);
        mAPIClientGithub = new APIClientGithub(mService);
        mAPIClientGithub.getPullRequest(mOwner, mRepository);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPullRequestListEvent(PullRequestEvent event) {
        if (event != null) {
            mPullRequest.addAll(event.getPullRequests());
            mAdapterPullRequest.notifyDataSetChanged();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onClickedPullRequestEvent(ClickItemPullRequestEvent event) {
        if (event != null) {
            openURLPullRequest(event.getPullRequest());
        }
    }

    public void openURLPullRequest(PullRequest pullRequest) {
        if (pullRequest != null) {
            Intent intentSite = new Intent(Intent.ACTION_VIEW);
            intentSite.setData(Uri.parse(pullRequest.getURL_PR()));
            startActivity(intentSite);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
