package com.concrete.android.challenge.ui.repository;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.concrete.android.challenge.BR;
import com.concrete.android.challenge.R;
import com.concrete.android.challenge.data.model.Repository;
import com.concrete.android.challenge.data.remote.response.paging.GithubPaging;
import com.concrete.android.challenge.databinding.FragmentRepositoryBinding;
import com.concrete.android.challenge.ui.base.BaseFragment;
import com.concrete.android.challenge.ui.pull_request.PullRequestActivity;
import java.util.Collections;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
public class RepositoryFragment extends BaseFragment<FragmentRepositoryBinding, RepositoryViewModel>
    implements RepositoryNavigator, RepositoryItemViewModel.RepositoryItemViewModelListener {

  private FragmentRepositoryBinding fragmentRepositoryBinding;
  private RepositoryViewModel repositoryViewModel;

  @Inject
  RepositoryAdapter adapter;

  private String query;
  private GithubPaging githubPaging;
  private boolean allowLoadMore = true;

  public static RepositoryFragment newInstance() {
    Bundle args = new Bundle();
    RepositoryFragment fragment = new RepositoryFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public int getBindingVariable() {
    return BR.viewModel;
  }

  @Override
  public int getLayoutId() {
    return R.layout.fragment_repository;
  }

  @Override
  public RepositoryViewModel getViewModel() {
    repositoryViewModel = ViewModelProviders.of(this, factory).get(RepositoryViewModel.class);
    return repositoryViewModel;
  }

  @Override public void onSuccess() {
    if (fragmentRepositoryBinding.refreshList.isRefreshing()) {
      fragmentRepositoryBinding.refreshList.setRefreshing(false);
    }
  }

  @Override
  public void handleError(Throwable throwable) {
    // handle error
  }

  @Override public void setGithubPaging(GithubPaging githubPaging) {
    this.githubPaging = githubPaging;
  }

  @Override public void setAllowLoadMore(boolean allowLoadMore) {
    this.allowLoadMore = allowLoadMore;
  }

  @Override public void onCreateOptionsMenu(@NotNull Menu menu, @NotNull MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.menu_search, menu);

    MenuItem mSearch = menu.findItem(R.id.action_search);

    SearchView mSearchView = (SearchView) mSearch.getActionView();

    mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        setQuery(query);
        adapter.clearItems();
        repositoryViewModel.getRepositories(query, "stars", 1);
        return false;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        if (newText.isEmpty()) {
          setQuery("");
        }
        return true;
      }
    });
  }

  @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_order_by_name:
        Collections.sort(repositoryViewModel.getRepositoryListLiveData().getValue(),
            Repository.getNameComparator());
        adapter.clearItems();
        adapter.add(repositoryViewModel.getRepositoryListLiveData().getValue());
        break;
      case R.id.action_order_by_stars:
        Collections.sort(repositoryViewModel.getRepositoryListLiveData().getValue(),
            Repository.getStarComparator());
        adapter.clearItems();
        adapter.add(repositoryViewModel.getRepositoryListLiveData().getValue());
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    repositoryViewModel.setNavigator(this);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    fragmentRepositoryBinding = getViewDataBinding();

    adapter.setListener(this);
    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
    fragmentRepositoryBinding.repositoryRecyclerView.setLayoutManager(layoutManager);
    fragmentRepositoryBinding.repositoryRecyclerView.setItemAnimator(new DefaultItemAnimator());
    fragmentRepositoryBinding.repositoryRecyclerView.setAdapter(adapter);

    fragmentRepositoryBinding.repositoryRecyclerView.addOnScrollListener(
        new RecyclerView.OnScrollListener() {
          @Override
          public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            if (layoutManager.getItemCount() - 2 <=
                layoutManager.getChildCount() + layoutManager.findFirstVisibleItemPosition()) {
              if (githubPaging.hasMore() && allowLoadMore) {
                allowLoadMore = false;
                repositoryViewModel.getRepositories("language:Java", "stars",
                    githubPaging.nextPage());
              }
            }
          }
        });

    fragmentRepositoryBinding.refreshList.setOnRefreshListener(
        () -> {
          adapter.clearItems();
          repositoryViewModel.refreshRepositoriesList();
        });
  }

  @Override public void onItemClick(Repository repository) {
    Intent intent = PullRequestActivity.getIntent(getContext(), repository.getName(),
        repository.getOwner().getLogin());
    startActivity(intent);
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public String getQuery() {
    return query;
  }
}
