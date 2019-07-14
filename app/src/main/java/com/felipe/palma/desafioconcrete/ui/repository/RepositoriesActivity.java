package com.felipe.palma.desafioconcrete.ui.repository;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.felipe.palma.desafioconcrete.R;
import com.felipe.palma.desafioconcrete.domain.model.Item;
import com.felipe.palma.desafioconcrete.domain.response.RepositoriesResponse;
import com.felipe.palma.desafioconcrete.network.IServiceGithub;
import com.felipe.palma.desafioconcrete.network.ServiceGithubImp;
import com.felipe.palma.desafioconcrete.ui.adapter.AnimationItem;
import com.felipe.palma.desafioconcrete.ui.adapter.InfiniteScrollListener;
import com.felipe.palma.desafioconcrete.ui.adapter.RecyclerItemClickListener;
import com.felipe.palma.desafioconcrete.ui.adapter.RepositoryAdapter;
import com.felipe.palma.desafioconcrete.ui.adapter.decoration.ItemOffsetDecoration;
import com.felipe.palma.desafioconcrete.ui.pullrequest.PullRequestActivity;
import com.felipe.palma.desafioconcrete.utils.Config;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Felipe Palma on 11/07/2019.
 */

public class RepositoriesActivity extends AppCompatActivity implements RepositoriesContract.View {

    private RepositoriesContract.Presenter mPresenter;
    private RepositoryAdapter mRepoAdapter;
    private int mPage = 1;
    private ProgressDialog dialog;

    private List<Item> mRepoList = new ArrayList<>();
    private InfiniteScrollListener infiniteScrollListener;
    private LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
    //Views

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    private SearchView searchView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        INSTANCIA BUTTERKNIFE
         */
        ButterKnife.bind(this);

        setupAdapter();
        setupPresenter();

        setupRecyclerView();



    }

    @Override
    public void hideDialog() {
        dialog.hide();
    }

    @Override
    public void showDialog() {
        dialog = ProgressDialog.show(this, "Baixando dados",
                "Aguarde ...", true);

    }


    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showRepositories(List<Item> itens) {
        Log.d("REPO", itens.toString());
        mRepoList = itens;
        mRepoAdapter.addRepoItems(mRepoList);
        showAnimation();

    }

    @Override
    public void showAnimation() {
        Context context = mRecyclerView.getContext();
        AnimationItem mAnimationItem =  new AnimationItem("Slide from bottom", R.anim.layout_animation_from_bottom);
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, mAnimationItem.getResourceId());

        mRecyclerView.setLayoutAnimation(controller);
        mRecyclerView.getAdapter().notifyDataSetChanged();
        mRecyclerView.scheduleLayoutAnimation();
    }



    private void setupPresenter() {
        mPresenter = new RepositoriesPresenter(this);
        mPresenter.loadRepositories(mPage);
    }

    private void setupAdapter(){
        mRepoAdapter = new RepositoryAdapter(this, mRepoList, recyclerItemClickListener);
        mRecyclerView.setAdapter(mRepoAdapter);
    }
    private void setupRecyclerView() {
        final int spacing = getResources().getDimensionPixelOffset(R.dimen.default_spacing_small);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new ItemOffsetDecoration(spacing));

        infiniteScrollListener = new InfiniteScrollListener(mLinearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                mPresenter.loadRepositories(page);

                Log.d("REPO_TEST", page+"");
            }
        };

        mRecyclerView.addOnScrollListener(infiniteScrollListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);


        setupSearchView(menu);


        return true;
    }


    private void setupSearchView(Menu menu) {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mRepoAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                mRepoAdapter.getFilter().filter(query);
                return false;
            }
        });
    }

    /**
     * RecyclerItem click event listener
     * */
    private RecyclerItemClickListener<Item> recyclerItemClickListener = item -> {
        String owner = item.getOwner().getLogin();
        String repository = item.getName();

        Intent mIntent = new Intent(this, PullRequestActivity.class);
        mIntent.putExtra(Config.OWNER,owner);
        mIntent.putExtra(Config.REPO,repository);
        startActivity(mIntent);
    };

}
