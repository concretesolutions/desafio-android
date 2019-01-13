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
    //ActivityMainBinding mActivityMainBinding;
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
                Log.i("###", "resss: " + result);
                // adapter.setList(//*response.getList()*//*result.getList()) ;
                //dogViewModel.saveUrl(result.getList(), mCategory);
            } else {
                //aviso de erro
                // binding.emptyState.setVisibility(View.VISIBLE);
            }

        });


    }




/*
    @Override
    protected  String getTitleToolbar(){
        return getString(R.string.list_repositorio);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }
*/


/*    public void configureToolbar() {
        Toolbar toolbar = mActivityMainBinding.toolbar;
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }*/

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
        // Adds the scroll listener to RecyclerView
        recyclerView.addOnScrollListener(scrollListener);

        adapter = new LisRepositoryAdapter(this);
    }

    private void addDataToList(int page) {
        mActivityMainBinding.itemProgressBar.setVisibility(View.VISIBLE);
        mViewModel.getListRepository(page).observe(this, result -> {


            if (result != null) {
                for (Item item : result.getItems()) {
                    mItemList.add(item);
                }
                adapter.notifyDataSetChanged();
                mActivityMainBinding.itemProgressBar.setVisibility(View.GONE);

                Log.i("###", "resss: " + result);

            } else {
                //aviso de erro
                // binding.emptyState.setVisibility(View.VISIBLE);
            }

        });

    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return super.onRetainCustomNonConfigurationInstance();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        Bundle bundle = new Bundle();
        bundle.putSerializable("list", (Serializable) mItemList);
        onSaveInstanceState(bundle);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("list", (Serializable) mItemList);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getSerializable("list");
    }
}