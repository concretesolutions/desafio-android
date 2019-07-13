package com.concrete.android.challenge.ui.pull_request;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.concrete.android.challenge.BR;
import com.concrete.android.challenge.R;
import com.concrete.android.challenge.data.model.PullRequest;
import com.concrete.android.challenge.data.remote.response.paging.GithubPaging;
import com.concrete.android.challenge.databinding.ActivityPullRequestBinding;
import com.concrete.android.challenge.ui.base.BaseActivity;
import java.util.List;
import javax.inject.Inject;

/**
 * @author Thiago Corredo
 * @since 2019-07-12
 */
public class PullRequestActivity
    extends BaseActivity<ActivityPullRequestBinding, PullRequestViewModel>
    implements PullRequestNavigator, PullRequestItemViewModel.PullRequestItemViewModelListener {

  public static final String REPOSITORY_NAME = "repository_name";
  public static final String REPOSITORY_OWNER_NAME = "repository_owner_name";

  private ActivityPullRequestBinding pullRequestBinding;
  private PullRequestViewModel pullRequestViewModel;

  @Inject
  PullRequestAdapter adapter;

  private String repositoryName;
  private String repositoryOwnerName;
  private GithubPaging githubPaging;
  private int opened = 0;
  private int closed = 0;
  private boolean allowLoadMore = true;

  public static Intent getIntent(Context context, String repositoryName,
      String repositoryOwnerName) {
    Intent intent = new Intent(context, PullRequestActivity.class);
    intent.putExtra(REPOSITORY_NAME, repositoryName);
    intent.putExtra(REPOSITORY_OWNER_NAME, repositoryOwnerName);
    return intent;
  }

  @Override public PullRequestViewModel getViewModel() {
    pullRequestViewModel = ViewModelProviders.of(this, factory).get(PullRequestViewModel.class);
    return pullRequestViewModel;
  }

  @Override public int getBindingVariable() {
    return BR.viewModel;
  }

  @Override public int getLayoutId() {
    return R.layout.activity_pull_request;
  }

  @Override public void onSuccess() {
    if (pullRequestBinding.refreshList.isRefreshing()) {
      pullRequestBinding.refreshList.setRefreshing(false);
    }
  }

  @Override public void handleError(Throwable throwable) {

  }

  @Override public void setGithubPaging(GithubPaging githubPaging) {
    this.githubPaging = githubPaging;
  }

  @Override public void updateTotalOpenedAndClosed(List<PullRequest> pullRequests) {
    for (PullRequest pullRequest : pullRequests) {
      if (pullRequest.getState().equals("open")) {
        opened++;
      } else if (pullRequest.getState().equals("closed")) {
        closed++;
      }
    }

    pullRequestBinding.stateOpened.setText(
        getResources().getString(R.string.pull_request_opened, opened));
    pullRequestBinding.stateClosed.setText(
        getResources().getString(R.string.pull_request_closed, closed));
  }

  @Override public void setAllowLoadMore(boolean allowLoadMore) {
    this.allowLoadMore = allowLoadMore;
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    pullRequestBinding = getViewDataBinding();
    pullRequestViewModel.setNavigator(this);
    setSupportActionBar(pullRequestBinding.toolbar);
    setActionbarNavigationAsBack(pullRequestBinding.toolbar);

    repositoryName = getIntent().getStringExtra(REPOSITORY_NAME);
    repositoryOwnerName = getIntent().getStringExtra(REPOSITORY_OWNER_NAME);

    setTitle(repositoryName);

    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    pullRequestBinding.pullRequestRecyclerView.setLayoutManager(layoutManager);
    pullRequestBinding.pullRequestRecyclerView.setItemAnimator(new DefaultItemAnimator());
    pullRequestBinding.pullRequestRecyclerView.setAdapter(adapter);

    pullRequestBinding.pullRequestRecyclerView.addOnScrollListener(
        new RecyclerView.OnScrollListener() {
          @Override
          public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            if (layoutManager.getItemCount() - 2 <=
                layoutManager.getChildCount() + layoutManager.findFirstVisibleItemPosition()) {
              if (githubPaging.hasMore() && allowLoadMore) {
                allowLoadMore = false;
                pullRequestViewModel.getPullRequests(repositoryOwnerName, repositoryName, "all",
                    githubPaging.nextPage());
              }
            }
          }
        });

    pullRequestBinding.refreshList.setOnRefreshListener(
        () -> {
          adapter.clearItems();
          pullRequestViewModel.getPullRequests(repositoryOwnerName, repositoryName, "all", 1);
        });
  }

  @Override protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    pullRequestViewModel.getPullRequests(repositoryOwnerName, repositoryName, "all", 1);
  }

  @Override public void onItemClick(PullRequest pullRequest) {
    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setData(Uri.parse(pullRequest.getHtmlUrl()));
    startActivity(intent);
  }
}
