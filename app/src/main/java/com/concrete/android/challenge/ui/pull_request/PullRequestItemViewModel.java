package com.concrete.android.challenge.ui.pull_request;

import androidx.databinding.ObservableField;
import com.concrete.android.challenge.data.model.PullRequest;

/**
 * @author Thiago Corredo
 * @since 2019-07-12
 */
public class PullRequestItemViewModel {

  public ObservableField<String> title;
  public ObservableField<String> content;
  public ObservableField<String> imageUrl;
  public ObservableField<String> userName;

  private PullRequest pullRequest;
  private PullRequestItemViewModelListener listener;

  public PullRequestItemViewModel(PullRequest pullRequest, PullRequestItemViewModelListener listener) {
    this.pullRequest = pullRequest;
    this.listener = listener;
    title = new ObservableField<>(pullRequest.getTitle());
    content = new ObservableField<>(pullRequest.getBody());
    imageUrl = new ObservableField<>(pullRequest.getUser().getAvatarUrl());
    userName = new ObservableField<>(pullRequest.getUser().getLogin());
  }

  public void onItemClick() {
    listener.onItemClick(pullRequest);
  }

  public interface PullRequestItemViewModelListener {
    void onItemClick(PullRequest pullRequest);
  }
}
