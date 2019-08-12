package com.example.desafioandroid.feature.repository;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desafioandroid.R;
import com.example.desafioandroid.feature.endpoints.GetDataService;
import com.example.desafioandroid.feature.pulls.PullActivity;
import com.example.desafioandroid.feature.utilities.EndlessScroll;
import com.example.desafioandroid.feature.utilities.RetrofitClientInstance;
import com.example.desafioandroid.model.Base;
import com.example.desafioandroid.model.Repository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryActivity extends AppCompatActivity implements RepositoryAdapter.RepositoryAdapterOnClickListener {

    //Views
    private RecyclerView mRecyclerView;

    //ViewModel
    private RepositoryViewModel viewModel;

    //Adapter
    private RepositoryAdapter mRepositoryAdapter;

    //Sercice
    private GetDataService service;

    //onScroll
    boolean isScrolling = false;
    int currentItems, totalItens, scrollOutItems;
    private int mCurrentPage;

    //Anothers Variables
    private static final String CURRENT_PAGE_KEY = "CURRENT_PAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);

        this.mRecyclerView = findViewById(R.id.recyclerViewRepository);

        if (savedInstanceState != null){
            mCurrentPage =savedInstanceState.getInt(CURRENT_PAGE_KEY);
            Log.d("CURRENT_PAGE", "SALVO");
        }else{
            mCurrentPage = 1;
            Log.d("CURRENT_PAGE", "NAO SALVO");
        }

        initUI();
        setupViewModel();
    }

    private void setupViewModel() {

        viewModel = ViewModelProviders.of(this, new RepositoryViewModelFactory(this.getApplication(), mCurrentPage)).get(RepositoryViewModel.class);
        viewModel.observerBaseRepository().observe(this, new Observer<Base>() {
            @Override
            public void onChanged(Base base) {
                mRepositoryAdapter.updateItems(base.getRepositories());
            }

        });


    }

    private void initUI() {
        mRepositoryAdapter = new RepositoryAdapter(null, this, this);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mRepositoryAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = linearLayoutManager.getChildCount();
                totalItens = linearLayoutManager.getItemCount();
                scrollOutItems = linearLayoutManager.findFirstVisibleItemPosition();

                if (isScrolling && (currentItems + scrollOutItems == totalItens)) {
                    isScrolling = false;
                    fetchData();
                }
            }
        });
    }


    private void fetchData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mCurrentPage++;
                updateListRepository();
            }
        }, 1000);
    }


    private void updateListRepository() {
        service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Base> call = service.getBaseResponse(mCurrentPage);

        call.enqueue(new Callback<Base>() {
            @Override
            public void onResponse(Call<Base> call, Response<Base> response) {
                Base base = response.body();
                mRepositoryAdapter.updateItems2(base.getRepositories());

            }

            @Override
            public void onFailure(Call<Base> call, Throwable t) {

            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_PAGE_KEY, mCurrentPage);
    }

    @Override
    public void onMyClickListener(Repository repository) {
        Toast.makeText(this, repository.getName(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, PullActivity.class);
        intent.putExtra("USER", repository.getOwner().getLogin());
        intent.putExtra("REPOSITORY", repository.getName());
        startActivity(intent);
    }

}

