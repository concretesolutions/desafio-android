package com.example.desafioandroid.feature.pulls;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AbsListView;

import com.example.desafioandroid.R;
import com.example.desafioandroid.feature.repository.RepositoryAdapter;
import com.example.desafioandroid.model.Pull;

import java.util.List;

public class PullActivity extends AppCompatActivity implements PullAdapter.PullAdapterOnClickListener {

    //View
    private RecyclerView mRecyclerView;

    //ViewModel
    private PullViewModel viewModel;

    //Adapter
    private PullAdapter mPullAdapter;

    //Anothers Variables
    private String user, repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull);

        mRecyclerView = findViewById(R.id.recyclerViewPull);

        Intent intentStartThisActivity = getIntent();

        if (intentStartThisActivity != null) {
            if (intentStartThisActivity.hasExtra("USER") &&
                    intentStartThisActivity.hasExtra("REPOSITORY")){

                this.user = intentStartThisActivity.getStringExtra("USER");
                this.repository = intentStartThisActivity.getStringExtra("REPOSITORY");

            }
        }

        initUI();
        setupViewModel();
    }

    private void setupViewModel() {

        viewModel = ViewModelProviders.of(this, new PullViewModelFactory(this.getApplication(), user, repository)).get(PullViewModel.class);
        viewModel.observerPullRepository().observe(this, new Observer<List<Pull>>() {
            @Override
            public void onChanged(List<Pull> pulls) {
                mPullAdapter.updateItems(pulls);
            }
        });


    }

    private void initUI() {
        mPullAdapter = new PullAdapter(null, this, this);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mPullAdapter);


    }

    @Override
    public void onMyClickListener(Pull pull) {

    }
}
