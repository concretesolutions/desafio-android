package br.com.appdesafio.view.activity;

import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.appdesafio.R;
import br.com.appdesafio.databinding.ActivityMainBinding;
import br.com.appdesafio.model.pojo.Item;
import br.com.appdesafio.view.adapter.LisRepositoryAdapter;
import br.com.appdesafio.viewmodel.LisRepositoryViewModel;


public class ListRepositoryActivity extends BaseActivity {

    private List<Item> mItemList = new ArrayList<>();
    ActivityMainBinding mActivityMainBinding;
    private RecyclerView recyclerView;
    private LisRepositoryAdapter adapter;
    @Inject
    public LisRepositoryViewModel mViewModel;
    private EndlessRecyclerViewScrollListener scrollListener;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        configureRecyclerView();
        mViewModel.getListRepository(1).observe(this, result -> {

            if (result != null) {

                mItemList = result.getItems();
                adapter.setRepository(result.getItems());
                recyclerView.setAdapter(adapter);

            } else {
                //aviso de erro
              //  mActivityMainBinding.itemProgressBar.setVisibility(View.GONE);
                mActivityMainBinding.emptyState.setVisibility(View.VISIBLE);
            }
            mActivityMainBinding.itemProgressBar.setVisibility(View.GONE);

        });


    }


    public void configureRecyclerView() {
        recyclerView = mActivityMainBinding.recyclerView;

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                addDataToList(page);
            }
        };

        recyclerView.addOnScrollListener(scrollListener);

        adapter = new LisRepositoryAdapter(this);
    }

    private void addDataToList(int page) {
        mViewModel.getListRepository(page).observe(this, result -> {


            if (result != null) {
                for (Item item : result.getItems()) {
                    mItemList.add(item);
                }
                adapter.notifyDataSetChanged();
                mActivityMainBinding.itemProgressBar.setVisibility(View.GONE);

                Log.i("###", "resss: " + result);

            } else {
                mActivityMainBinding.itemProgressBar.setVisibility(View.GONE);
                //aviso de erro
                // binding.emptyState.setVisibility(View.VISIBLE);
            }

        });

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Bundle bundle = new Bundle();
        bundle.putSerializable("list", (Serializable) mItemList);
        onSaveInstanceState(bundle);
    }


}