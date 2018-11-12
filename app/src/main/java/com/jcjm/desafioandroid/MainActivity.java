package com.jcjm.desafioandroid;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.jcjm.desafioandroid.databinding.ActivityMainBinding;
import com.jcjm.desafioandroid.recycler.adapters.RepositoryPageListAdapter;
import com.jcjm.desafioandroid.util.Constant;
import com.jcjm.desafioandroid.util.ViewModelFactory;
import com.jcjm.desafioandroid.viewmodel.PagingGitRepositoriesViewModel;

import java.util.Objects;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    ViewModelFactory viewModelFactory;

    PagingGitRepositoriesViewModel viewModel;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ((MyApplication) getApplication()).getAppComponent().doInjection(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PagingGitRepositoriesViewModel.class);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initPaging();
    }


    private void initPaging() {



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.list.setLayoutManager(linearLayoutManager);
        RepositoryPageListAdapter adapter = new RepositoryPageListAdapter();
        binding.list.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.list.getContext(),
                linearLayoutManager.getOrientation());
        binding.list.addItemDecoration(dividerItemDecoration);


        if (!Constant.checkInternetConnection(this)) {
            Snackbar.make(findViewById(android.R.id.content), Constant.CHECK_NETWORK_ERROR, Snackbar.LENGTH_SHORT)
                    .show();
        }

        viewModel.getListLiveData().observe(this, adapter::submitList);

        viewModel.getProgressLoadStatus().observe(this, status -> {
            if (Objects.requireNonNull(status).equalsIgnoreCase(Constant.LOADING)) {
                binding.progress.setVisibility(View.VISIBLE);
            } else if (status.equalsIgnoreCase(Constant.LOADED)) {
                binding.progress.setVisibility(View.GONE);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
