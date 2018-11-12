package com.jcjm.desafioandroid.activities;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.jcjm.desafioandroid.MyApplication;
import com.jcjm.desafioandroid.R;
import com.jcjm.desafioandroid.databinding.ActivityPullRequestsBinding;
import com.jcjm.desafioandroid.model.PullRequest;
import com.jcjm.desafioandroid.recycler.adapters.PullRequestAdapter;
import com.jcjm.desafioandroid.util.ApiResponse;
import com.jcjm.desafioandroid.util.Constant;
import com.jcjm.desafioandroid.util.ViewModelFactory;
import com.jcjm.desafioandroid.viewmodel.PullRequestViewModel;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

public class PullRequestsActivity extends AppCompatActivity {

    @Inject
    ViewModelFactory viewModelFactory;

    ActivityPullRequestsBinding binding;

    String user;
    String repositorio;

    PullRequestViewModel viewModel;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pull_requests);
        progressDialog = Constant.getProgressDialog(this, "Please wait...");
        ((MyApplication) getApplication()).getAppComponent().doInjection(this);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        user = intent.getStringExtra("user");
        repositorio = intent.getStringExtra("repositorio");

        init();
    }

    private void init() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.list.setLayoutManager(linearLayoutManager);
        PullRequestAdapter pullRequestAdapter = new PullRequestAdapter();
        binding.list.setAdapter(pullRequestAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.list.getContext(),
                linearLayoutManager.getOrientation());
        binding.list.addItemDecoration(dividerItemDecoration);


        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PullRequestViewModel.class);

        viewModel.pullRequestResponse().observe(this, this::consumeResponse);

        if (!Constant.checkInternetConnection(this)) {
            Toast.makeText(PullRequestsActivity.this,getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
        } else {
            viewModel.hitPullRequestsApi(user,repositorio);
        }

    }



    private void consumeResponse(ApiResponse apiResponse) {

        switch (apiResponse.status) {

            case LOADING:
                progressDialog.show();
                break;

            case SUCCESS:
                progressDialog.dismiss();
                renderSuccessResponse(apiResponse.data);
                break;

            case ERROR:
                progressDialog.dismiss();
                Toast.makeText(PullRequestsActivity.this,getResources().getString(R.string.errorString), Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }


    private void renderSuccessResponse(JsonElement response) {
        if (!response.isJsonNull()) {
            Log.d("response=", response.toString());

            Type listType = new TypeToken<List<PullRequest>>() {}.getType();
            List<PullRequest> listPullRequest = new Gson().fromJson(response, listType);

            ((PullRequestAdapter)binding.list.getAdapter()).updateData(listPullRequest);

        } else {
            Toast.makeText(PullRequestsActivity.this,getResources().getString(R.string.errorString), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
