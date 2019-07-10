package com.example.javapop.Views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javapop.APIClientGithub.APIClientGithub;
import com.example.javapop.APIClientGithub.Retrofit.RetrofitService;
import com.example.javapop.APIClientGithub.Retrofit.ServiceGenerator;
import com.example.javapop.Adapter.AdapterRepositoryList;
import com.example.javapop.Events.ClickItemRepositoryEvent;
import com.example.javapop.Events.RepositoryListEvent;
import com.example.javapop.Models.Repository;
import com.example.javapop.R;
import com.example.javapop.Utils.EndlessScrollListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityRepositoryList extends AppCompatActivity {

    private static final String TAG = "ActivityRepositoryList";

    private static final int PAGE_INITIAL = 1;
    private List<Repository> mRepositories;
    private AdapterRepositoryList mAdapterRepositoryList;
    private APIClientGithub APIClientGithub;
    private EndlessScrollListener scrollListener;

    @BindView(R.id.recyclerView_RepositoryList)
    RecyclerView mRecyclerViewRepositoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_list);
        ButterKnife.bind(this);
        configRetrofit();
        initView();
    }

    private void initView() {
        mRepositories = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerViewRepositoryList.setLayoutManager(linearLayoutManager);
        mAdapterRepositoryList = new AdapterRepositoryList(mRepositories, getApplicationContext());
        mRecyclerViewRepositoryList.setAdapter(mAdapterRepositoryList);
        scrollListener = new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                Log.d(TAG, "onLoadMore");
                loadNextDataFromApi(page);
            }
        };

        mRecyclerViewRepositoryList.addOnScrollListener(scrollListener);
    }

    public void loadNextDataFromApi(int offset) {
        APIClientGithub.getListRepositoryCall(offset);
        mAdapterRepositoryList.notifyDataSetChanged();
    }

    private void configRetrofit() {
        RetrofitService mService = ServiceGenerator.createService(RetrofitService.class);
        APIClientGithub = new APIClientGithub(mService);
        APIClientGithub.getListRepositoryCall(PAGE_INITIAL);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRepositoryListEvent(RepositoryListEvent event) {
        if (event != null) {
            mRepositories.addAll(event.getRepository());
            mAdapterRepositoryList.notifyDataSetChanged();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onClickedRepositoryEvent(ClickItemRepositoryEvent event) {
        if (event != null) {
            loadParamters(event.getRepository());
        }
    }

    public void loadParamters(Repository repository) {
        if (repository != null) {
            Intent it = new Intent(this, ActivityPullRequest.class);
            it.putExtra("mOwner", repository.getOwnerGit().getUsername());
            it.putExtra("mRepository", repository.getNameRepository());
            startActivity(it);
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
