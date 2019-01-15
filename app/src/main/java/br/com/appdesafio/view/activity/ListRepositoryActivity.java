package br.com.appdesafio.view.activity;

import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.appdesafio.R;

import br.com.appdesafio.databinding.ActivityListRepositoryBinding;
import br.com.appdesafio.model.pojo.Item;
import br.com.appdesafio.view.adapter.ListRepositoryAdapter;
import br.com.appdesafio.viewmodel.ListRepositoryViewModel;

/**
 * Class responsible for displaying the repository list.
 */
public class ListRepositoryActivity extends BaseActivity {

    private List<Item> mItemList = new ArrayList<>();
    ActivityListRepositoryBinding mActivityMainBinding;
    private RecyclerView recyclerView;
    private ListRepositoryAdapter adapter;
    @Inject
    public ListRepositoryViewModel mViewModel;
    private EndlessRecyclerViewScrollListener scrollListener;
    private int page = 1;


     /**
     * Initialize the fundamental components of the activity.
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_list_repository);
        configureRecyclerView();
        mViewModel.getListRepository(page).observe(this, result -> {

            if (result != null) {

                mItemList = result.getItems();
                adapter.setRepository(result.getItems());
                recyclerView.setAdapter(adapter);

            } else {
                mActivityMainBinding.emptyState.setVisibility(View.VISIBLE);
            }
            mActivityMainBinding.itemProgressBar.setVisibility(View.GONE);

        });


    }


    /**
     * Configures the activity's recyclerview and is also responsible for infinite scroll logic.
     */
    public void configureRecyclerView() {
        recyclerView = mActivityMainBinding.recyclerView;

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                onGetMoreItemList(page);
            }
        };

        recyclerView.addOnScrollListener(scrollListener);

        adapter = new ListRepositoryAdapter(this);
    }

    /**
     *method called by the onLoadMore method of infinite scroll.
     * @param page number that is sent as a parameter to the server.
     */
    private void onGetMoreItemList(int page) {
        mViewModel.getListRepository(page).observe(this, result -> {


            if (result != null) {
                for (Item item : result.getItems()) {
                    mItemList.add(item);
                }
                adapter.notifyDataSetChanged();
                mActivityMainBinding.itemProgressBar.setVisibility(View.GONE);


            } else {
                mActivityMainBinding.itemProgressBar.setVisibility(View.GONE);
            }

        });

    }

    /**
     * method responsible for saving the state of the screen when the
     * screen orientation change happens.
     * @param newConfig
     */

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Bundle bundle = new Bundle();
        bundle.putSerializable("list", (Serializable) mItemList);
        onSaveInstanceState(bundle);
    }


}